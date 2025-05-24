package com.example.safeapplicationproject.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.safeapplicationproject.R
import com.example.safeapplicationproject.activity.MainActivity
import com.example.safeapplicationproject.databinding.FragmentLoginBinding

class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
            if (binding.tvUsername.text.toString() == "Aldrin" && binding.tvPassword.text.toString() == "Aldrin123"){
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_visitorRegisterFragment)
        }

    }

}