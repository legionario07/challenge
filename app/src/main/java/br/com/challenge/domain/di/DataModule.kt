package br.com.challenge.domain.di

import br.com.challenge.domain.repository.api.Api
import br.com.challenge.domain.repository.api.RepositoryApi
import br.com.challenge.domain.repository.local.IDao
import br.com.challenge.domain.repository.local.ListRepoRepositoryLocalImpl
import br.com.challenge.domain.repository.local.RepositoryDao
import br.com.challenge.domain.repository.remote.IListRepoRepository
import br.com.challenge.domain.repository.remote.ListRepoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LocalListRepository

@Qualifier
annotation class RemoteListRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @LocalListRepository
    @Singleton
    @Binds
    abstract fun bindRemoteListRepository(repositoryImpl: ListRepoRepositoryLocalImpl): IListRepoRepository

    @RemoteListRepository
    @Singleton
    @Binds
    abstract fun bindLocalListRepository(repositoryImpl: ListRepoRepositoryImpl): IListRepoRepository

    @Singleton
    @Binds
    abstract fun bindApi(api: RepositoryApi): Api

    @Singleton
    @Binds
    abstract fun bindDao(dao: RepositoryDao): IDao
}