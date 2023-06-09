package com.example.signupapp.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signupapp.domain.repository.ProjectRepository
import com.example.signupapp.ui.model.SignUpFormModel
import com.example.signupapp.ui.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpFragmentViewModel @Inject constructor(private val repository: ProjectRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UIState())

    val uiState: StateFlow<UIState> = _uiState

    fun sendDataToImagination(model: SignUpFormModel) {
        // to prevent concurrent clicks.
        if (!_uiState.value.isLoading) {
            // tell UI we are loading.
            _uiState.value = UIState(isLoading = true)
            viewModelScope.launch(Dispatchers.IO) {
                repository.sendDataToImagination(model).collect {
                    // give UI the result data.
                    _uiState.value = UIState(data = it, isLoading = false)
                }
            }
        }
    }
}
