package br.com.challenge.domain.utils

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.data.entity.RepositoryEntity
import com.google.gson.reflect.TypeToken

class ConstantsUtil {

    companion object {

        const val BASE_URL = "https://api.github.com/"
        const val BASE_URL_MOCK = "http://localhost/"

        const val BASE_PATH_JSONS = "jsons/"
        const val JSON_REPOSITORY_DTO = BASE_PATH_JSONS + "repositorydto.json"
        const val JSON_REPOSITORY_ENTITIES = BASE_PATH_JSONS + "repositories_entities.json"

        //Types
        val REPOSITORY_DTO_TYPE = object : TypeToken<RepositoryDTO>() {
        }.type
        val REPOSITORY_ENTITIES_TYPE = object : TypeToken<List<RepositoryEntity>>() {
        }.type
    }
}