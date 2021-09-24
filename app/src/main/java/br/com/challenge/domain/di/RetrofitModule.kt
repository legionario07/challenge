package br.com.challenge.domain.di

import android.content.Context
import androidx.room.Room
import br.com.challenge.domain.repository.api.ApiManager
import br.com.challenge.domain.repository.api.RepositoryApi
import br.com.challenge.domain.repository.local.RepositoryDao
import br.com.challenge.domain.repository.local.RoomAppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesRepoApi(): RepositoryApi {
        return ApiManager.repositoryApi
    }

    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context) : RoomAppDB = Room.databaseBuilder(
        context.applicationContext, RoomAppDB::class.java, "AppDBB"
    )
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    fun providesDao(db: RoomAppDB) : RepositoryDao {
      return db.repositoryDAO()!!
    }
}