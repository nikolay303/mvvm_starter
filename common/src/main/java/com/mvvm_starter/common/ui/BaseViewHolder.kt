package com.mvvm_starter.common.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val context: Context = itemView.context
}