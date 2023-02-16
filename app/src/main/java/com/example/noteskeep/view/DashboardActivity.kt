package com.example.noteskeep.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteskeep.R
import com.example.noteskeep.databinding.ActivityAuthBinding
import com.example.noteskeep.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private var _binding: ActivityDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .addToBackStack(null)
            .commit()

    }
}