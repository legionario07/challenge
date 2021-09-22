package br.com.challenge.domain.repository.api

import br.com.challenge.data.dto.RepositoryDTO

interface Api {
    suspend fun getRepositories(
        language: String,
        sort: String
    ): RepositoryDTO
}