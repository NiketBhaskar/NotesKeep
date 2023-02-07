package com.example.noteskeep.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.noteskeep.R
import com.example.noteskeep.databinding.FragmentRegisterBinding
import com.example.noteskeep.model.User
import com.example.noteskeep.model.UserAuthService
import com.example.noteskeep.viewModel.RegisterViewModel
import com.example.noteskeep.viewModel.RegisterViewModelFactory

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory(UserAuthService())).get(RegisterViewModel::class.java)

        binding.btnLogin.setOnClickListener(){
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, LoginFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.btnReg.setOnClickListener(){
            userRegister()
        }
        return binding.root
    }

    private fun userRegister() {
        val mEmail = binding.etEmail.text.toString()
        val mPassword = binding.etPassword.text.toString()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()

        var user = User(first_Name = firstName,last_Name = lastName,eMail = mEmail,pass = mPassword)

        registerViewModel.userRegister(user)
        registerViewModel.registerStatus.observe(viewLifecycleOwner, Observer {
            if(it.status){
                Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, LoginFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
            else{
                Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}