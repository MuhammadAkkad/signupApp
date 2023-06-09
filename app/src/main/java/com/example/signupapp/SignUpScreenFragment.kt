package com.example.signupapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.signupapp.databinding.SignUpScreenBinding
import com.google.android.material.textfield.TextInputLayout


class SignUpScreenFragment : Fragment() {


    private var _binding: SignUpScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private var editTexts = arrayListOf<TextInputLayout>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpScreenBinding.inflate(inflater, container, false)
        setupEditText()
        registerListener()
        return binding.root
    }

    private fun launchCamera() {
        // Start the Camera app.
        cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun setupEditText() {
        editTexts.add(binding.textInputLayoutFirstName)
        editTexts.add(binding.textInputLayoutPassword)
    }

    private fun registerListener() {
        binding.textViewAvatar.avatarRootCv.setOnClickListener {
            launchCamera()
        }

        binding.BtnSubmit.setOnClickListener {
            if (checkMandatoryFieldsValid())
                gatherData()
        }

        // Create an ActivityResultLauncher for the Camera app.
        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                // Get the image data from the Camera app.
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                // Set the image data to the ImageView.
                binding.textViewAvatar.avatarIv.setImageBitmap(imageBitmap)
            }
        }
    }

    private fun gatherData() {
        val model = SignUpFormModel().apply {
            binding.textViewAvatar.avatarIv.drawable?.toBitmap()?.let {
                this.image = it
            }
            this.name = binding.editTextFirstName.text.toString()
            this.email = binding.editTextEmailAddress.text.toString()
            this.password = binding.editTextPassword.text.toString()
            this.website = binding.editTextWebsite.text.toString()
        }
        navigateToScreenConfirmation(model)
    }

    private fun checkMandatoryFieldsValid(): Boolean {
        for (view in editTexts) {
            if (view.editText?.text?.isEmpty() == true) {
                view.error = getString(R.string.mandatory_field)
                view.requestFocus()
                return false
            }
        }
        return true
    }

    private fun navigateToScreenConfirmation(model: SignUpFormModel) {
        findNavController(requireParentFragment()).navigate(
            SignUpScreenFragmentDirections.actionSignUpToConfirmation(
                model
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // important for memory leak/
        _binding = null
    }

}

