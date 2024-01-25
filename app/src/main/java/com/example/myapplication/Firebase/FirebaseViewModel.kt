package com.example.myapplication.Firebase

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.model.Chat
import com.example.myapplication.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FirebaseViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    private var _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    private var _name = MutableLiveData<String>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    lateinit var profileRef: DocumentReference

    init {
        if (firebaseAuth.currentUser != null) {
            _currentUser.value = firebaseAuth.currentUser
            profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
        }
    }

    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>> get() = _messages

    private val _myChats = MutableLiveData<MutableList<Chat>>()
    val myChats: LiveData<MutableList<Chat>> get() = _myChats

    private val _currentChat = MutableLiveData<Chat>()

    val getCurrentChat: MutableLiveData<Chat>
        get() = _currentChat

    val getName: MutableLiveData<String>
        get() = _name

    fun setCurrentChat(id: Chat) {
        _currentChat.postValue(id)
    }

    fun setName(name : String) {
        _name.postValue(name)
    }

    fun fetchMyChats() {
        profileRef.addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myProfile = value.toObject(PersonData::class.java)
                val name = myProfile!!.name
                setName(name)
            }
        }
        profileRef.collection("groups").addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myChatsList = mutableListOf<Chat>()

                for (document in value.documents) {
                    val chat = Chat(
                        groupID = document.get("groupID") as? String ?: "",
                        groupName = document.get("groupName") as? String ?: "",
                        groupPic = document.get("groupPic") as? Int ?: 0
                    )

                    // Fetch messages within each group
                    val chatId = document.id
                    val chatCollectionRef = profileRef.collection("groups").document(chatId).collection("chats")

                    chatCollectionRef.addSnapshotListener { chatValue, chatError ->
                        if (chatError == null && chatValue != null) {
                            val messagesList = chatValue.documents.map { chatDocument ->
                                Message(
                                    text = chatDocument.get("text") as? String ?: "",
                                    from = chatDocument.get("from") as? String ?: "",
                                    timestamp = chatDocument.get("timestamp") as? String ?: "",
                                    send = chatDocument.get("send") as? Boolean ?: false
                                )
                            }
                            chat.messages = messagesList.toMutableList()

                            // Add the chat to the list after fetching messages
                            myChatsList.add(chat)
                            _myChats.postValue(myChatsList.toMutableList())
                        } else {
                            Log.e("FIREBASE", "Error fetching chats: $chatError")
                        }
                    }
                }
            } else {
                Log.e("FIREBASE", "Error fetching groups: $error")
            }
        }
    }

    fun addChatGroupToCollection(groupName: String, pic: Int) {
        val chatRef = profileRef.collection("groups").document()
        val groupId = chatRef.id
        chatRef.set(Chat(groupName = groupName, pic, groupID = groupId))
            .addOnSuccessListener {
                chatRef.collection("chats")
                    .add(
                        Message(text = "Willkommen", from = "Moderator", timestamp = getCurrentTime(), send = false)
                    )
                    .addOnSuccessListener {
                        Log.d("FIREBASE", "Chat and message added successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.e("FIREBASE", "Error adding message: $e")
                    }
            }
            .addOnFailureListener { e ->
                Log.e("FIREBASE", "Error adding Chat document: $e")
            }
    }
    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return currentTime.format(formatter)
    }

    fun addMessageToChat(groupId: String, text: String, senderName: String, time: String) {
        val message = Message(text = text, from = senderName, timestamp = time, send = true)

        profileRef.collection("groups").document(groupId).collection("chats")
            .add(message)
            .addOnSuccessListener {
                Log.d("FIREBASE", "Message added successfully")
            }
            .addOnFailureListener { e ->
                Log.e("FIREBASE", "Error adding message: $e")
            }
    }

    fun register(email: String, password: String, PersonData: PersonData) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    firebaseAuth.currentUser?.sendEmailVerification()

                    // Initialize profileRef here
                    profileRef =
                        fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
                    profileRef.set(PersonData)
                    _currentUser.value = firebaseAuth.currentUser
                } else {
                    Log.e("FIREBASE", "${authResult.exception}")
                }
            }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    if (firebaseAuth.currentUser!!.isEmailVerified) {
                        profileRef = fireStore.collection("profiles")
                            .document(firebaseAuth.currentUser!!.uid)
                        _currentUser.value = firebaseAuth.currentUser
                    } else {
                        Log.e("FIREBASE", "User not verified")
                        logout()
                    }

                } else {
                    Log.e("FIREBASE", "${authResult.exception}")
                }
            }
    }

    fun sendPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
    }

    // Funktion um User auszuloggen
    fun logout() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

    fun uploadImage(uri: Uri) {
        // Erstellen einer Referenz und des Upload Tasks
        val imageRef = storageRef.child("images/${firebaseAuth.currentUser!!.uid}/profilePic")
        val uploadTask = imageRef.putFile(uri)

        // Wenn UploadTask ausgeführt und erfolgreich ist, wird die Download-Url des Bildes an die setUserImage Funktion weitergegeben
        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    setUserImage(it.result)
                }
            }
        }
    }

    private fun setUserImage(uri: Uri) {
        profileRef.update("pic", uri.toString())
    }

}