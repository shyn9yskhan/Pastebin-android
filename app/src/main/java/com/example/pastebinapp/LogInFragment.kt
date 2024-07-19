package com.example.pastebinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.pastebinapp.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.logInButton.setOnClickListener {

            val username = binding.usernameEditText.getText()
            if (!username.isNullOrBlank()) {
                val usernamePattern = "^(?![_.])[a-z0-9._]{4,20}(?<![_.])\$"
                if (username.matches(usernamePattern.toRegex())) {
                    binding.usernameInputLayout.setError(null)
                    Toast.makeText(requireContext(), "username is correct", Toast.LENGTH_SHORT).show()
                } else {
                    binding.usernameInputLayout.setError("invalid username")
                }
            } else {
                binding.usernameInputLayout.setError("username cannot be empty")
            }

            val password = binding.passwordEditText.getText()
            if (!password.isNullOrBlank()) {
                val passwordPattern = "^\\S*$"
                if (password.matches(passwordPattern.toRegex())) {
                    binding.passwordInputLayout.setError(null)
                    Toast.makeText(requireContext(), "password is correct", Toast.LENGTH_LONG).show()
                } else {
                    binding.passwordInputLayout.setError("invalid password")
                }
            }
            else {
                binding.passwordInputLayout.setError("password cannot be empty")
            }
        }

        binding.goToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }



        return view
    }
}