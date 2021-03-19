package com.android.PACKAGE.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation


/**
 * Created by Nikolay Siliuk on 2/3/21.
 */

fun ImageView.loadWithCoil(
    url: String?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = 0,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {},
) {
    load(url) {
        crossfade(100)
        placeholder(placeholder)
        error(error)
        listener(
            onSuccess = { _, _ -> onSuccess.invoke() },
            onError = { _, _ -> onError.invoke() }
        )
    }
}

fun ImageView.loadCircleImageWithCoil(
    url: String?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = 0,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {},
) {
    load(url) {
        crossfade(100)
        placeholder(placeholder)
        error(error)
        transformations(CircleCropTransformation())
        listener(
            onSuccess = { _, _ -> onSuccess.invoke() },
            onError = { _, _ -> onError.invoke() }
        )
    }
}

fun ImageView.loadRoundedImageWithCoil(
    url: String?,
    cornerRadius: Float,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = 0,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {},
) {
    load(url) {
        crossfade(100)
        placeholder(placeholder)
        error(error)
        transformations(RoundedCornersTransformation(cornerRadius))
        listener(
            onSuccess = { _, _ -> onSuccess.invoke() },
            onError = { _, _ -> onError.invoke() }
        )
    }
}

fun ImageView.loadWithCoil(
    @DrawableRes resId: Int?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes error: Int = 0,
    onSuccess: () -> Unit = {},
    onError: () -> Unit = {},
) {
    load(resId ?: 0) {
        crossfade(100)
        placeholder(placeholder)
        error(error)
        listener(
            onSuccess = { _, _ -> onSuccess.invoke() },
            onError = { _, _ -> onError.invoke() }
        )
    }
}
