package com.example.myapplication.viewmodel

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

    // Firebase-Authentifizierungsinstanz
    private val firebaseAuth = FirebaseAuth.getInstance()
    // Firebase Firestore-Instanz
    private val fireStore = FirebaseFirestore.getInstance()
    // Firebase Storage-Instanz
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    // Mutable LiveData für den aktuellen Benutzer
    private var _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    // Mutable LiveData für den Namen
    private var _name = MutableLiveData<String>()
    val getName: MutableLiveData<String>
        get() = _name

    // Referenz zur Firestore-Dokumentenreferenz des aktuellen Benutzers
    lateinit var profileRef: DocumentReference

    // Initialisierung beim Erstellen der Instanz
    init {
        if (firebaseAuth.currentUser != null) {
            _currentUser.value = firebaseAuth.currentUser
            profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
        }
    }

    // Mutable LiveData für Nachrichten
    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>> get() = _messages

    // Mutable LiveData für den aktuellen Chat
    private val _myChats = MutableLiveData<MutableList<Chat>>()
    val myChats: LiveData<MutableList<Chat>> get() = _myChats

    // Mutable LiveData für den aktuellen Chat
    private val _currentChat = MutableLiveData<Chat>()

    val getCurrentChat: MutableLiveData<Chat>
        get() = _currentChat

    // Setzt den aktuellen Chat
    fun setCurrentChat(id: Chat) {
        _currentChat.postValue(id)
    }

    // Setzt den Namen
    fun setName(name : String) {
        _name.postValue(name)
    }

    // Holt meine Chats aus der Firestore-Datenbank
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

    // Fügt eine Chat-Gruppe zur Sammlung hinzu
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

    // Gibt die aktuelle Uhrzeit zurück
    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        return currentTime.format(formatter)
    }

    // Fügt eine Nachricht zu einem Chat hinzu
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

    // Registriert einen neuen Benutzer
    fun register(email: String, password: String, PersonData: PersonData) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { authResult ->
                if (authResult.isSuccessful) {
                    firebaseAuth.currentUser?.sendEmailVerification()
                    profileRef =
                        fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
                    profileRef.set(PersonData)
                    _currentUser.value = firebaseAuth.currentUser
                } else {
                    Log.e("FIREBASE", "${authResult.exception}")
                }
            }
    }

    // Loggt einen Benutzer ein
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

    // Sendet eine E-Mail zur Zurücksetzung des Passworts
    fun sendPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
    }

    // Loggt den Benutzer aus
    fun logout() {
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

    // Läd Bild in den Storage
    fun uploadImage(uri: Uri) {
        val imageRef = storageRef.child("images/${firebaseAuth.currentUser!!.uid}/profilePic")
        val uploadTask = imageRef.putFile(uri)
        uploadTask.addOnCompleteListener {
            imageRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    setUserImage(it.result)
                }
            }
        }
    }

    // Updated das Bild
    private fun setUserImage(uri: Uri) {
        profileRef.update("pic", uri.toString())
    }

}