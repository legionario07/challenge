package br.com.challenge.data.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "repositories")
@Parcelize
data class RepositoryDTO(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var items: List<ItemDTO> = listOf()
) : Parcelable


