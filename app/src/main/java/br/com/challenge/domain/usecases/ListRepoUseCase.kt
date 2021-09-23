package br.com.challenge.domain.usecases

import br.com.challenge.data.entity.RepositoryEntity
import br.com.challenge.domain.repository.remote.IListRepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRepoUseCase @Inject constructor(private val repository: IListRepoRepository) {

    suspend fun getRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<RepositoryEntity>> {
        return repository.getRepositories(
            language,
            sort,
            page
        )
    }
}