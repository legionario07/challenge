package br.com.challenge.domain.local

import br.com.challenge.data.dto.RepositoryDTO
import br.com.challenge.domain.repository.local.IDao

class FakeRepoDao: IDao {

    var mapDataSource = HashMap<Int, RepositoryDTO>()

    override fun getRepository(id: Int): RepositoryDTO? {
        return mapDataSource[id]
    }

    override fun insert(repositoryDTO: RepositoryDTO) {
        mapDataSource[repositoryDTO.id] = repositoryDTO
    }
}