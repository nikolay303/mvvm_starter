package com.android.PACKAGE.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions


fun ImageView.setImageTint(color: Int) {
    imageTintList = ColorStateList.valueOf(color)
}

fun ImageView.setImageTintResId(@ColorRes colorRes: Int) {
    imageTintList = ColorStateList.valueOf(context.getColor(colorRes))
}

@SuppressLint("CheckResult")
fun ImageView.loadWithGlide(
    url: String?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = 0,
    onSuccess: () -> Unit = {},
    onFailed: () -> Unit = {},
    centerCrop: Boolean = true,
) {
    Glide.with(this.context)
        .load(url)
        .apply {
            if (centerCrop) {
                apply(RequestOptions().centerCrop())
            }
            placeholder(placeholder)
            error(error)
            listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        onFailed.invoke()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        onSuccess.invoke()
                        return false
                    }
                })
        }.into(this)
}

fun RequestManager.loadWithGlide(view: ImageView, url: String?, onSuccess: () -> Unit = {}) {
    load(url)
        .apply(RequestOptions.centerCropTransform())
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                onSuccess.invoke()
                return false
            }
        })
        .into(view)
}

fun Context.preloadWithGlide(url: String?) {
    Glide.with(this)
        .load(url)
        .preload()
}

fun ImageView.loadWithGlide(@DrawableRes resId: Int?, onSuccess: () -> Unit = {}) {
    Glide.with(this)
        .load(resId)
        .centerCrop()
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                onSuccess.invoke()
                return false
            }
        })
        .into(this)
}

fun ImageView.loadWithGlideError(url: String?, @DrawableRes errorDrawableId: Int = 0) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .apply(
            RequestOptions()
                .error(errorDrawableId)
                .placeholder(errorDrawableId)
        )
        .into(this)
}