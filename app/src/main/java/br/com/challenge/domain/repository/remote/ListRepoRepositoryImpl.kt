package br.com.challenge.domain.repository.remote

import br.com.challenge.data.dto.RepositoryDTO
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
    ): Flow<RepositoryDTO?> {
        return flow {
            val responseData =
                apiDataSource.getRepositories(language, sort, page)

            emit(responseData)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveRepository(repositoryDTO: RepositoryDTO) {
        //Do Nothing
    }
}