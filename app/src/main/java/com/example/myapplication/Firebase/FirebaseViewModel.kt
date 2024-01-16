package com.example.myapplication.Firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.PersonApi.model.PersonData
import com.example.myapplication.model.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseViewModel: ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val fireStore = FirebaseFirestore.getInstance()

    private var _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    lateinit var profileRef: DocumentReference

    init {
        profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
    }

    fun addChatGroupToCollection(groupId: Int, groupName: String, pic: Int) {
        profileRef.collection("groups").add(Chat(groupID = groupId, groupName, pic))
    }

    fun register(email: String, password: String, PersonData: PersonData) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { authResult ->
            if (authResult.isSuccessful) {
                firebaseAuth.currentUser?.sendEmailVerification()

                // Initialize profileRef here
                profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
                profileRef.set(PersonData)
                _currentUser.value = firebaseAuth.currentUser
            } else {
                Log.e("FIREBASE", "${authResult.exception}")
            }
        }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { authResult ->
            if (authResult.isSuccessful) {
                if (firebaseAuth.currentUser!!.isEmailVerified) {
                    profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
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