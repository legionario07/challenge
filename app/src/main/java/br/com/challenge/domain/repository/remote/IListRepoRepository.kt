package br.com.challenge.domain.repository.remote

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.data.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow

interface IListRepoRepository {
    suspend fun getRepositories(language: String, sort: String, page: Int): Flow<RepositoryDTO?>
    suspend fun saveRepository(repositoryDTO: RepositoryDTO)
}