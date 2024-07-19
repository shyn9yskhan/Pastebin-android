package com.example.pastebinapp

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.pastebinapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root


        var password : String = ""

        binding.signUpButton.setOnClickListener {


            val email = binding.emailEditText.getText()
            if (!email.isNullOrBlank()) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.emailInputLayout.setError(null)
                } else {
                    binding.emailInputLayout.setError("invalid email")
                }
            }
            else {
                binding.emailInputLayout.setError("email cannot be empty")
            }


            val signUpPassword = binding.signUpPasswordEditText.getText()
            if (signUpPassword.toString().length >= 8) {
                val passwordPattern = "^\\S*$"
                if (signUpPassword.toString().matches(passwordPattern.toRegex())) {
                    //correct password
                    binding.signUpPasswordLayout.setError(null)
                    password = signUpPassword.toString()
                } else {
                    binding.signUpPasswordLayout.setError("invalid password")
                }
            } else {
                binding.signUpPasswordLayout.setError("minimum 8 characters")
            }


            val confirmPassword = binding.confirmPasswordEditText.getText()
            if (confirmPassword.toString() == password) {
                binding.confirmPasswordLayout.setError(null)
            } else {
                binding.confirmPasswordLayout.setError("passwords do not match")
            }
        }
        return view
    }
}