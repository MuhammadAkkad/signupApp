package com.example.signupapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.signupapp.databinding.ConfirmationScreenBinding

class ConfirmationScreenFragment : Fragment() {


    private var _binding: ConfirmationScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args by navArgs<ConfirmationScreenFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConfirmationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
