package br.com.challenge.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryEntity(

    var id: Int = 0,
    var nameRepository: String = "",
    var countStars: Int = 0,
    var countForks: Int = 0,
    var photoUser: String = "",
    var nameAuthor: String = ""

) : Parcelable