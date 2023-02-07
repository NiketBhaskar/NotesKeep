package com.example.noteskeep.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteskeep.model.AuthListener
import com.example.noteskeep.model.User
import com.example.noteskeep.model.UserAuthService

class RegisterViewModel(private val authService: UserAuthService) : ViewModel() {
    private var _RegisterStatus = MutableLiveData<AuthListener>()
    val registerStatus = _RegisterStatus as LiveData<AuthListener>

    fun userRegister(user : User){
        authService.userRegister(user){
            if(it.status){
                _RegisterStatus.value = it
            }
        }
    }
}