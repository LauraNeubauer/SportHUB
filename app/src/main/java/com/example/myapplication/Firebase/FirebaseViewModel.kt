package com.example.myapplication.Firebase

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

class FirebaseViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()

    private var _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
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

    fun setCurrentChat(id: Chat) {
        _currentChat.postValue(id)
    }

    fun fetchMyChats() {
        profileRef.collection("groups").addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myChatsList = mutableListOf<Chat>()

                for (document in value.documents) {
                    val chat = Chat(
                        groupID = document.get("groupID") as? Int ?: 0,
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
                                    from = chatDocument.get("sender") as? String ?: "",
                                    timestamp = chatDocument.get("timestamp") as? String ?: "",
                                    send = chatDocument.get("isRead") as? Boolean ?: false
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


    fun addMessageToChat(
        groupId: String,
        messageText: String,
        sender: String,
        timestamp: String,
        send: Boolean
    ) {
        val chatCollectionRef =
            profileRef.collection("groups").document(groupId).collection("chats")

        val newMessage = Message(text = messageText, sender, timestamp = timestamp, send)
        chatCollectionRef.add(newMessage)
            .addOnSuccessListener {
                Log.d("FIREBASE", "Message added successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("FIREBASE", "Error adding message: $e")
            }
    }

    fun addChatGroupToCollection(groupId: Int, groupName: String, pic: Int) {
        profileRef.collection("groups").add(Chat(groupID = groupId, groupName = groupName, pic))
            .addOnSuccessListener { documentReference ->
                profileRef.collection("groups").document(documentReference.id).collection("chats")
                    .add(
                        Message(text = "hallo", "phil", timestamp = "12:09", false)
                    )
            }.addOnFailureListener { e ->
                Log.e("FIREBASE", "Error adding Chat document: $e")
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

}