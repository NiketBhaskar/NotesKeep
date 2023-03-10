package com.example.noteskeep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, LoginFragment())
            .addToBackStack(null)
            .commit()
    }

}