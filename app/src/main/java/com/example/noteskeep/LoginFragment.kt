package com.example.noteskeep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteskeep.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnRegister.setOnClickListener(){
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        return binding.root
    }

}