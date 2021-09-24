package br.com.challenge.domain.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.challenge.data.dto.RepositoryDTO

@Dao
interface RepositoryDao :IDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(repositoryDTO: RepositoryDTO)

    @Query("SELECT * FROM repositories WHERE id = :id")
    override fun getRepository(id: Int): RepositoryDTO
}