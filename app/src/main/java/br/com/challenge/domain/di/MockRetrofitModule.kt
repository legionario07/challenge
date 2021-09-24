package br.com.challenge.domain.di

import br.com.challenge.domain.repository.api.ApiManager
import br.com.challenge.domain.repository.api.RepositoryApi
import br.com.challenge.domain.utils.ConstantsUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RetrofitModule::class]
)
class MockRetrofitModule {

    @Singleton
    @Provides
    fun providesRepoApi(): RepositoryApi {
        return ApiManager.repositoryApi(ConstantsUtil.BASE_URL_MOCK)
    }
}