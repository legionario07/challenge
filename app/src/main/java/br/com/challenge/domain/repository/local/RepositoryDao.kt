package br.com.challenge.domain.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.challenge.data.dto.RepositoryDTO

@Dao
interface RepositoryDao :IDao {

    @Insert
    override fun insert(repositoryDTO: RepositoryDTO)

    @Query("SELECT * FROM repositories ORDER BY id DESC")
    override fun getRepository(): RepositoryDTO
}