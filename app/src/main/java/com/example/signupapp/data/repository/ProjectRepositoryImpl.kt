package com.example.signupapp.data.repository

import com.example.signupapp.domain.repository.ProjectRepository
import com.example.signupapp.ui.model.SignUpFormModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProjectRepositoryImpl @Inject constructor() : ProjectRepository {

    override suspend fun sendDataToImagination(model: SignUpFormModel): Flow<SignUpFormModel> {
        return flow {
            // mimic API call.
            delay(1000)
            emit(model)
        }.flowOn(Dispatchers.IO)
    }

}