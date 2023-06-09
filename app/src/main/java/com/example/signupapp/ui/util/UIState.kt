package com.example.signupapp.ui.util

data class UIState(
    val data: Any? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null, // we can use it for error handling.
)