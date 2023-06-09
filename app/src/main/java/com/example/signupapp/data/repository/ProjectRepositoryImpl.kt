package com.example.signupapp.data.repository

import com.example.signupapp.ui.model.SignUpFormModel
import com.example.signupapp.domain.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProjectRepositoryImpl @Inject constructor() : ProjectRepository {

    override suspend fun sendDataToImagination(model: SignUpFormModel): Flow<SignUpFormModel> {
        return flow {
            // mimic API call.
            kotlinx.coroutines.delay(2000)
            emit(model)
        }.flowOn(Dispatchers.IO)
    }

}