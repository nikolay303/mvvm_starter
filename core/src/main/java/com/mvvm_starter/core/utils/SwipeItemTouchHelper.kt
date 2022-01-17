package com.mvvm_starter.core.utils

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


abstract class SwipeItemTouchHelper(
    swipeDirs: Int = ItemTouchHelper.LEFT,
) : ItemTouchHelper.SimpleCallback(0, swipeDirs) {

    private var swipeBack = false
    private var onSwiped: (Int) -> Unit = {}
    private var swipedItemPosition: Int? = null

    var swipeAvailable: Boolean = true

    abstract fun getForegroundView(viewHolder: RecyclerView.ViewHolder): View?

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return swipeAvailable
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipedItemPosition?.let(onSwiped::invoke)
            swipeBack = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            setTouchListener(recyclerView)
        }
        swipedItemPosition =
            if (abs(dX) > getMinDxMovingForAction()) viewHolder.adapterPosition else null
        getForegroundView(viewHolder)?.let { view ->
            getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        getForegroundView(viewHolder)?.let { view ->
            getDefaultUIUtil().clearView(view)
        }
        swipedItemPosition = null
    }

    open fun getMinDxMovingForAction(): Int {
        return 200
    }

    fun attach(recyclerView: RecyclerView, onSwiped: (Int) -> Unit) {
        this.onSwiped = onSwiped
        ItemTouchHelper(this).attachToRecyclerView(recyclerView)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(recyclerView: RecyclerView) {
        recyclerView.setOnTouchListener { _, event ->
            swipeBack = event.action == MotionEvent.ACTION_CANCEL
                    || event.action == MotionEvent.ACTION_UP
            return@setOnTouchListener false
        }
    }
}