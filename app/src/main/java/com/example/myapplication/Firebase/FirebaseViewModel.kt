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

    private val _currentChat = MutableLiveData<Int>()

    val getCurrentChat: MutableLiveData<Int>
        get() = _currentChat

    fun setCurrentChat(id: Int) {
        _currentChat.postValue(id)
    }

    fun fetchMyChats() {
        profileRef.collection("groups").addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val myChatsList = value.documents.map { document ->
                    Chat(
                        groupID = document.get("groupID") as? Int ?: 0,
                        groupName = document.get("groupName") as? String ?: "",
                        groupPic = document.get("groupPic") as? Int ?: 0
                    )
                }
                _myChats.postValue(myChatsList.toMutableList())
            }
        }
    }

    fun fetchMessages() {
        profileRef.collection("chats").addSnapshotListener { value, error ->
            if (error == null && value != null) {
                val messageList = value.documents.map { document ->
                    Message(
                        text = document.get("text") as? String ?: "",
                        from = document.get("from") as? String ?: "",
                        timestamp = document.get("timestamp") as? String ?: "", // Beachten Sie, dass timestamp wahrscheinlich ein Long ist
                        send = document.get("send") as? Boolean ?: false
                    )
                }
                _messages.postValue(messageList.toMutableList())
            }
        }
    }

    fun addChatGroupToCollection(groupId: Int, groupName: String, pic: Int) {
        profileRef.collection("groups").add(Chat(groupID = groupId, groupName, pic))
    }
    fun addMessagesToChatCollection(groupId: String, text: String, from: String, timestamp: String, send: Boolean) {
        val newMessage = Message(text, from, timestamp, send)
        profileRef.collection("groups").document(groupId).collection("messages").add(newMessage)
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