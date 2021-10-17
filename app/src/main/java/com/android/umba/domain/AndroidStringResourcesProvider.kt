package com.android.umba.domain

interface AndroidStringResourcesProvider {
    fun getString(resId: Int): String
    fun getString(
        resId: Int,
        vararg formatArgs: Any?
    ): String
}
