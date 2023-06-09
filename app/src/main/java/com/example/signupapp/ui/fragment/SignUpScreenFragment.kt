package com.example.signupapp.ui.fragment

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.signupapp.R
import com.example.signupapp.ui.model.SignUpFormModel
import com.example.signupapp.databinding.SignUpScreenBinding
import com.example.signupapp.ui.util.hideKeyboard
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignUpScreenFragment : Fragment() {

    private var _binding: SignUpScreenBinding? = null

    private val binding get() = _binding!!

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>

    private var editTexts = arrayListOf<TextInputLayout>()

    private val viewModel: SignUpFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeObservers()
        registerForActivityResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupEditText()
        registerListeners()
    }

    private fun initializeObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect {
                showLoading(it.isLoading)
                if (!it.isLoading && it.data != null) {
                    navigateToScreenConfirmation(it.data as SignUpFormModel)
                }
            }
        }
    }

    private fun launchCamera() {
        // Start the Camera app.
        cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun setupEditText() {
        editTexts.add(binding.textInputLayoutFirstName)
        editTexts.add(binding.textInputLayoutPassword)
    }

    private fun registerListeners() {
        binding.textViewAvatar.avatarRootCv.setOnClickListener {
            launchCamera()
        }

        binding.BtnSubmit.setOnClickListener {
            hideKeyboard()
            if (checkMandatoryFieldsValid())
                sendRequest()
        }
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

    private fun gatherData(): SignUpFormModel {
        return SignUpFormModel().apply {
            this.name = binding.editTextFirstName.text.toString()
            this.email = binding.editTextEmailAddress.text.toString()
            this.password = binding.editTextPassword.text.toString()
            this.website = binding.editTextWebsite.text.toString()
            binding.textViewAvatar.avatarIv.drawable?.toBitmap()?.let {
                // if image available set it
                this.image = it
            }
        }
    }

    private fun sendRequest() {
        viewModel.sendDataToImagination(gatherData())
    }

    private fun registerForActivityResult() {
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

    private fun navigateToScreenConfirmation(model: SignUpFormModel) {
        findNavController(requireParentFragment()).navigate(
            SignUpScreenFragmentDirections.actionSignUpToConfirmation(
                model
            )
        )
    }

    private fun showLoading(loading: Boolean) {
        binding.progressHorizontal.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        // important for memory leak/
        _binding = null
    }

}
