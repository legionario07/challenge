package br.com.challenge.domain.mapper

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.data.entity.RepositoryEntity

object RepositoryMapper {

    @JvmStatic
    fun transformerRepository(repositoryDTO: RepositoryDTO): List<RepositoryEntity> {
        val listRepositoryEntity = ArrayList<RepositoryEntity>()
        for (item in repositoryDTO.items) {
            val repositoryEntity = item.ownerDTO?.avatarUrl?.let { avatarUrl ->
                item.ownerDTO?.login?.let { nameAuthor ->
                    RepositoryEntity(
                        item.nameRepository,
                        item.countStars,
                        item.countForks,
                        avatarUrl,
                        nameAuthor
                    )
                }
            }

            repositoryEntity?.let { listRepositoryEntity.add(repositoryEntity) }
        }

        return listRepositoryEntity
    }
}