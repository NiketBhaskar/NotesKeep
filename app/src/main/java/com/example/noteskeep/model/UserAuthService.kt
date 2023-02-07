package com.example.noteskeep.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class UserAuthService {
    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private var firbaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storageReference: FirebaseStorage = FirebaseStorage.getInstance()

    fun userLogin(user: User, listener : (AuthListener) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(user.eMail, user.pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listener(AuthListener(true, "Login Successful"))
                } else {
                    listener(AuthListener(false, "Login Failed"))
                }
            }
    }

    fun userRegister(user: User, listner: (AuthListener) -> Unit) {
        val uid = firebaseAuth.currentUser?.uid.toString()

        firebaseAuth.createUserWithEmailAndPassword(user.eMail, user.pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val docRef = firbaseFirestore.collection("User").document(uid)
                    var userDetail: HashMap<String, String> = HashMap<String, String>()

                    userDetail["first_Name"] = user.first_Name
                    userDetail["last_Name"] = user.last_Name
                    userDetail["eMail"] = user.eMail
                    userDetail["pass"] = user.pass
                    userDetail["userId"] = user.userId
                    userDetail["profilePicture"] = user.profilePicture

                    docRef.set(userDetail)
                    listner(AuthListener(true, "Register Successfully"))
                } else {
                    listner(AuthListener(false, "Register Failed"))
                }
            }
    }

    fun userForgetPass(user : User, listner: (AuthListener) -> Unit){
        firebaseAuth.sendPasswordResetEmail(user.eMail).addOnCompleteListener {
            if(it.isSuccessful){
                listner(AuthListener(true, "Check Your Email"))
            }
            else{
                listner(AuthListener(false, "Something went wrong."))
            }
        }
    }
}