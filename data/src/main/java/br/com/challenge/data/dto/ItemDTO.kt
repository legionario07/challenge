package br.com.challenge.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemDTO(

    @SerializedName("stargazers_count") var countStars: Int = 0,
    @SerializedName("forks_count") var countForks: Int = 0,
    @SerializedName("name") var nameRepository: String = "",
    @SerializedName("owner") var ownerDTO: OwnerDTO? = null

) : Parcelable