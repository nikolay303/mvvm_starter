package com.mvvm_starter.core.utils


fun <T> Iterable<T>.copy(): Iterable<T> {
    val mutableList = mutableListOf<T>()
    mutableList.addAll(this)
    return mutableList
}

fun <T> MutableCollection<T>.rewrite(newCollection: Collection<T>) {
    clear()
    addAll(newCollection)
}