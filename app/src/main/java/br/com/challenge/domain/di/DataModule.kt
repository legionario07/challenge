package br.com.challenge.domain.di

import br.com.challenge.domain.repository.api.Api
import br.com.challenge.domain.repository.api.RepositoryApi
import br.com.challenge.domain.repository.remote.IListRepoRepository
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindListRepository(repositoryImpl: ListRepoRepositoryImpl): IListRepoRepository

    @Singleton
    @Binds
    abstract fun bindApi(api: RepositoryApi): Api

}