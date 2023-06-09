package com.example.signupapp.data.di

import com.example.signupapp.data.repository.ProjectRepositoryImpl
import com.example.signupapp.domain.repository.ProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repository: ProjectRepositoryImpl
    ): ProjectRepository

}