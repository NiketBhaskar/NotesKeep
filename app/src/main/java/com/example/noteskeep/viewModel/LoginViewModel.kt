package com.example.noteskeep.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteskeep.model.AuthListener
import com.example.noteskeep.model.User
import com.example.noteskeep.model.UserAuthService

class LoginViewModel(private val authService: UserAuthService) : ViewModel() {
    private var _UserLoginStatus = MutableLiveData<AuthListener>()
    val userLoginStatus = _UserLoginStatus as LiveData<AuthListener>

    private var _UserForgotPassStatus = MutableLiveData<AuthListener>()
    val userForgotPassStatus = _UserForgotPassStatus as LiveData<AuthListener>

    fun userLogin(user : User){
        authService.userLogin(user){
            if(it.status){
                _UserLoginStatus.value = it
            }
        }
    }

    fun userForgetPass(user : User){
        authService.userForgetPass(user){
            if(it.status){
                _UserForgotPassStatus.value = it
            }
        }
    }

}