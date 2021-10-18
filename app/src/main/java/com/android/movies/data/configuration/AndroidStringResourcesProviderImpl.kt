package com.android.movies.data.configuration

import android.content.Context
import com.android.movies.domain.AndroidStringResourcesProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidStringResourcesProviderImpl @Inject constructor(@ApplicationContext private val context: Context) :
    AndroidStringResourcesProvider {
    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(
        resId: Int,
        vararg formatArgs: Any?
    ): String = context.getString(resId, *formatArgs)

}
