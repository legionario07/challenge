package br.com.challenge.domain.repository.api

import br.com.challenge.data.dto.RepositoryDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryApi : Api {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    override suspend fun getRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): RepositoryDTO
}