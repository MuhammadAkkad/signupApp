package com.example.signupapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.signupapp.databinding.ConfirmationScreenBinding
import com.example.signupapp.ui.model.SignUpFormModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmationScreenFragment : Fragment() {

    private var _binding: ConfirmationScreenBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<ConfirmationScreenFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConfirmationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setData(args.dataModel)
    }

    private fun setData(dataModel: SignUpFormModel) {
        binding.model = dataModel
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // important for memory leak/
        _binding = null
    }

}
