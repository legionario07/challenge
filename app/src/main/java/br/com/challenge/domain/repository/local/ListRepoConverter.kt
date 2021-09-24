package br.com.challenge.domain.repository.local

import androidx.room.TypeConverter
import br.com.challenge.data.dto.ItemDTO
import com.google.gson.Gson

class ListRepoConverter {

        @TypeConverter
        fun listToJson(value: List<ItemDTO>?): String = Gson().toJson(value)

        @TypeConverter
        fun jsonToList(value: String) = Gson().fromJson(value, Array<ItemDTO>::class.java).toList()
}