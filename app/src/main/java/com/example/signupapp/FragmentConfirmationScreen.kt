package com.example.signupapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.signupapp.databinding.ConfirmationScreenBinding

class FragmentConfirmationScreen : Fragment() {

    private lateinit var binding: ConfirmationScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConfirmationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}
