package com.android.movies.domain

import com.google.gson.Gson
import java.io.FileNotFoundException

object FileMapper {
    fun <T> toJson(
        fileName: String,
        clazz: Class<T>
    ): T {
        return toString(fileName).let {
            Gson().fromJson(it, clazz)
        } as T
    }

    fun toString(fileName: String): String =
        this::class.java.classLoader?.getResource(fileName)
            ?.readText() ?: throw FileNotFoundException()
}
