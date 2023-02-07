package com.example.noteskeep.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteskeep.model.UserAuthService

class LoginViewModelFactory(private val authService: UserAuthService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authService) as T
    }
}