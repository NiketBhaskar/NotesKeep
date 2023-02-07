package com.example.noteskeep.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.noteskeep.R
import com.example.noteskeep.databinding.FragmentLoginBinding
import com.example.noteskeep.model.User
import com.example.noteskeep.model.UserAuthService
import com.example.noteskeep.viewModel.LoginViewModel
import com.example.noteskeep.viewModel.LoginViewModelFactory
import com.example.noteskeep.viewModel.RegisterViewModel
import com.example.noteskeep.viewModel.RegisterViewModelFactory

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(UserAuthService())).get(LoginViewModel::class.java)

        binding.btnRegister.setOnClickListener(){
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.btnLogin.setOnClickListener(){
            userLogin()
        }
        binding.tvForgotPass.setOnClickListener(){
            userForgetPass()
        }
        return binding.root
    }

    private fun userForgetPass() {
        val mEmail = binding.etEmail.text.toString()
        val mPassword = binding.etPassword.text.toString()

        var user = User(eMail = mEmail, pass = mPassword)

        loginViewModel.userForgetPass(user)
        loginViewModel.userForgotPassStatus.observe(viewLifecycleOwner, Observer {
            if(it.status){
                Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun userLogin() {
        val mEmail = binding.etEmail.text.toString()
        val mPassword = binding.etPassword.text.toString()

        var user = User(eMail = mEmail, pass = mPassword)

        loginViewModel.userLogin(user)
        loginViewModel.userLoginStatus.observe(viewLifecycleOwner, Observer {
            if(it.status){
                Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}