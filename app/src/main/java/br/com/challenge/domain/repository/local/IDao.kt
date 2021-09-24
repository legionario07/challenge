package br.com.challenge.domain.repository.local

import br.com.challenge.data.dto.RepositoryDTO

interface IDao {

    fun insert(repositoryDTO: RepositoryDTO)

    fun getRepository(): RepositoryDTO
}