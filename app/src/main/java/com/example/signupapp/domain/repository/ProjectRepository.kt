package com.example.signupapp.domain.repository


import com.example.signupapp.ui.model.SignUpFormModel
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun sendDataToImagination(model: SignUpFormModel): Flow<SignUpFormModel>
}