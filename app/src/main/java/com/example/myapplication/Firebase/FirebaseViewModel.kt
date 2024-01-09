package com.example.myapplication.Firebase

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.PersonApi.model.PersonData
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


    private fun setUserImage(uri: Uri) {
        profileRef.update("profilePicture", uri.toString())
    }

    // Funktion um neuen User zu erstellen
    fun register(email: String, password: String, PersonData: PersonData) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { authResult ->
            if (authResult.isSuccessful) {
                firebaseAuth.currentUser?.sendEmailVerification()
                profileRef = fireStore.collection("profiles").document(firebaseAuth.currentUser!!.uid)
                profileRef.set(PersonData)
                createChatCollection()
                _currentUser.value = firebaseAuth.currentUser
            } else {
                Log.e("FIREBASE", "${authResult.exception}")
            }
        }
    }

    private fun createChatCollection() {
        // Hier wird eine Referenz zur Chat-Sammlung innerhalb des Benutzerprofils erstellt
        val chatCollectionRef = profileRef.collection("chats")
        chatCollectionRef.add(mapOf("initialized" to true))
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