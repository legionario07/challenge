package br.com.challenge.domain.usecases

import br.com.challenge.data.entity.RepositoryEntity
import br.com.challenge.domain.di.LocalListRepository
import br.com.challenge.domain.di.RemoteListRepository
import br.com.challenge.domain.mapper.RepositoryMapper
import br.com.challenge.domain.repository.remote.IListRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ListRepoUseCase @Inject constructor(
    @RemoteListRepository private val repositoryRemote: IListRepoRepository,
    @LocalListRepository private val repositoryLocal: IListRepoRepository
) {

    suspend fun getRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<RepositoryEntity>?> {

        var listRepository: List<RepositoryEntity>? = null

        val flowRepositoryLocal = repositoryLocal.getRepositories(
            language,
            sort,
            page
        )

        flowRepositoryLocal.collect {
            it?.let {
                listRepository = RepositoryMapper.transformerRepository(it)
            } ?: kotlin.run {
                val flowRepositoryRemote = repositoryRemote.getRepositories(language, sort, page)
                flowRepositoryRemote.collect { repositoryDTO ->
                    with(repositoryDTO) {
                        this?.id = page
                        this?.let { repo -> repositoryLocal.saveRepository(repo) }
                        this?.let {
                            listRepository = RepositoryMapper.transformerRepository(this)
                        }
                    }
                }
            }
        }

        return flow {
            emit(listRepository)
        }.flowOn(Dispatchers.IO)

    }
}