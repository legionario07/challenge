package br.com.challenge.domain.repository.remote

import br.com.challenge.data.entity.RepositoryEntity
import br.com.challenge.domain.mapper.RepositoryMapper
import br.com.challenge.domain.repository.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ListRepoRepositoryImpl @Inject constructor(private val apiDataSource: Api) : IListRepoRepository {

    override suspend fun getRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<RepositoryEntity>> {
        return flow {
            val responseData =
                apiDataSource.getRepositories(language, sort, page)

            val repositoriesEntity = RepositoryMapper.transformerRepository(responseData)

            emit(repositoriesEntity)
        }.flowOn(Dispatchers.IO)
    }
}