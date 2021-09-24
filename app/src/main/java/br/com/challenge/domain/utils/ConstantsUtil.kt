package br.com.challenge.domain.utils

import br.com.challenge.data.dto.RepositoryDTO
import com.google.gson.reflect.TypeToken

class ConstantsUtil {

    companion object {
        const val BASE_PATH_JSONS = "jsons/"
        const val JSON_REPOSITORY_DTO = BASE_PATH_JSONS + "repositorydto.json"

        //Types
        val REPOSITORY_DTO_TYPE = object : TypeToken<RepositoryDTO>() {
        }.type
    }
}