package br.com.challenge.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryDTO(
    var items: List<ItemDTO> = listOf()
) : Parcelable


