package br.com.challenge.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerDTO(
    var login: String = "",
    @SerializedName("avatar_url") var avatarUrl: String = "",
): Parcelable