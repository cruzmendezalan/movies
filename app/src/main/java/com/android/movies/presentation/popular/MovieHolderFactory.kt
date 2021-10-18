package com.android.movies.presentation.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface MovieHolderFactory {
    fun buildMovieHolder(parent: ViewGroup): MovieHolder<*>
}

/**
 * In order to reduce boilerplate code required on [MovieHolderFactory] instantiation
 */
inline fun <reified Binding : ViewBinding> ViewGroup.createViewBinding(): Binding {
    val inflateMethod = Binding::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
    )
    return inflateMethod.invoke(null, LayoutInflater.from(context), this, false) as Binding
}

