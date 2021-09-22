package br.com.challenge.domain.repository.remote

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.data.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow

interface IListRepoRepository {
    suspend fun getRepositories(language: String, sort: String): Flow<List<RepositoryEntity>>
}