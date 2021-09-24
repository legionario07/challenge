package br.com.challenge.domain.di

import br.com.challenge.domain.repository.api.ApiManager
import br.com.challenge.domain.repository.api.RepositoryApi
import br.com.challenge.domain.utils.ConstantsUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class RetrofitModule {

    @Singleton
    @Provides
    fun providesRepoApi(): RepositoryApi {
        return ApiManager.repositoryApi(ConstantsUtil.BASE_URL)
    }
}