package com.android.umba.data.configuration

import android.content.Context
import com.android.umba.domain.AndroidStringResourcesProvider
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
