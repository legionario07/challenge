package br.com.challenge.domain.repository.local

import br.com.challenge.data.entity.RepositoryEntity
import br.com.challenge.domain.mapper.RepositoryMapper
import br.com.challenge.domain.repository.remote.IListRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ListRepoRepositoryLocalImpl @Inject constructor(private val repositoryDao: RepositoryDao) : IListRepoRepository{

    override suspend fun getRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<RepositoryEntity>> {
        return flow {
            val responseData =
                repositoryDao.getRepository()

            val repositoriesEntity = RepositoryMapper.transformerRepository(responseData)

            emit(repositoriesEntity)
        }.flowOn(Dispatchers.IO)
    }
}