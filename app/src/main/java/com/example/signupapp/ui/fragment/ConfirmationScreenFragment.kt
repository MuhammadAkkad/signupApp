package com.example.signupapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.signupapp.ui.model.SignUpFormModel
import com.example.signupapp.databinding.ConfirmationScreenBinding
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
        getData(args.dataModel)
    }

    private fun getData(dataModel: SignUpFormModel) {
        binding.model = dataModel
        dataModel.image?.let {
            binding.textViewAvatar.avatarIv.setImageBitmap(it)
        } ?: kotlin.run {
            binding.textViewAvatar.root.visibility = View.GONE
        }
        binding.executePendingBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // important for memory leak/
        _binding = null
    }
}
