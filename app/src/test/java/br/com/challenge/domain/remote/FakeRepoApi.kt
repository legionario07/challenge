package br.com.challenge.domain.remote

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.domain.repository.api.Api
import br.com.challenge.domain.utils.ConstantsUtil
import br.com.challenge.domain.utils.FileUtil

class FakeRepoApi : Api {
    override suspend fun getRepositories(language: String, sort: String, page: Int): RepositoryDTO {
        val file = FileUtil.readFile(ConstantsUtil.JSON_REPOSITORY_DTO)
        return FileUtil.getFromReader(file, ConstantsUtil.REPOSITORY_DTO_TYPE) as RepositoryDTO
    }
}