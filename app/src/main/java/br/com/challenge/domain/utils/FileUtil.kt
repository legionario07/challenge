package br.com.challenge.domain.utils

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.lang.reflect.Type

object FileUtil {

    @JvmStatic
    fun readFile(file: String): Reader {
        val inputStream = this.javaClass.classLoader?.getResourceAsStream(file)

        return BufferedReader(InputStreamReader(inputStream))
    }

    @JvmStatic
    fun getFromReader(file: Reader, type: Type): Any {
        return Gson().fromJson<Any>(file, type)
    }
}