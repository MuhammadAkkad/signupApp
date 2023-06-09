package com.example.signupapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.signupapp.databinding.SignUpScreenBinding

class FragmentSignUpScreen : Fragment() {

    private lateinit var binding: SignUpScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpScreenBinding.inflate(inflater, container, false)
        binding.BtnSubmit.setOnClickListener {
            navigateToScreenConfirmation()
        }

        return binding.root
    }

    private fun navigateToScreenConfirmation() {
        findNavController(requireParentFragment()).navigate(FragmentSignUpScreenDirections.actionSignUpToConfirmation())
    }

}
