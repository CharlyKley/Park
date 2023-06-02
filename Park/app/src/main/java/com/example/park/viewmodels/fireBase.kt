package com.example.park.viewmodels

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object fireBase {
    private lateinit var fauth: FirebaseAuth

    private lateinit var fstore: FirebaseFirestore

    fun iniciarFirebaseAuth() {
        fauth = FirebaseAuth.getInstance()

    }

    fun iniciarFirebaseFirestore() {
        fstore = FirebaseFirestore.getInstance()

    }

    fun getFirebaseAuth(): FirebaseAuth {
        return fauth
    }

    fun getFirestore(): FirebaseFirestore {
        return fstore
    }

}