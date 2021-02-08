package com.mvvm_starter.common.ui.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtil<T> : DiffUtil.Callback() {

    private var oldList: List<T> = listOf()
    private var newList: List<T> = listOf()

    protected abstract fun getItemId(item: T): String

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return getItemId(oldItem) == getItemId(newItem)

    }
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition] == newList[newItemPosition]
    }

    fun setData(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }
}