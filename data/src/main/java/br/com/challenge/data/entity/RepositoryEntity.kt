package br.com.challenge.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryEntity(

    @SerializedName("name") var nameRepository: String = "",
    @SerializedName("stargazers_count")var countStars: Int = 0,
    @SerializedName("forks_count")var countForks: Int = 0,
    @SerializedName("avatar_url")var photoUser: String = "",
    @SerializedName("login")var nameAuthor: String = ""
) : Parcelable