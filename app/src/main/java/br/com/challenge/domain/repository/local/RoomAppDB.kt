package br.com.challenge.domain.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.challenge.data.dto.RepositoryDTO

@Database(entities = [RepositoryDTO::class], version = 1)
@TypeConverters(ListRepoConverter::class)
abstract class RoomAppDB : RoomDatabase() {

    abstract fun repositoryDAO(): RepositoryDao?
}