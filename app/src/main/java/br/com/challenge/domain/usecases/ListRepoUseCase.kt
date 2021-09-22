package br.com.challenge.domain.usecases

import br.com.challenge.data.entity.RepositoryEntity
import br.com.challenge.domain.repository.remote.IListRepoRepository
import kotlinx.coroutines.flow.Flow

class ListRepoUseCase(private val repository: IListRepoRepository) {

    suspend fun getRepositories(
        language: String,
        sort: String
    ): Flow<List<RepositoryEntity>> {
        return repository.getRepositories(
            language,
            sort
        )
    }
}