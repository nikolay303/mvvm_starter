package com.mvvm_starter.core.utils

import android.content.*
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Html
import android.text.Spanned
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.*
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import com.mvvm_starter.core.R
import timber.log.Timber
import java.io.File


fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, this.resources.getText(resId), duration).show()
}

@ColorInt
fun Context.getAttrColor(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    val theme: Resources.Theme = this.theme
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}

fun Context.getPluralString(@PluralsRes resId: Int, quantity: Int): String =
    resources.getQuantityString(resId, quantity)

fun Context.getPluralFormatString(@PluralsRes resId: Int, quantity: Int): String =
    resources.getQuantityString(resId, quantity, quantity)

fun Context.getPluralFormatString(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg value: Any,
): String =
    resources.getQuantityString(resId, quantity, value)

fun Context.openUrl(url: String) {
    CustomTabsIntent.Builder()
        .build()
        .apply { intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        .launchUrl(this, Uri.parse(url))
}

fun Context.getFont(@FontRes resId: Int): Typeface? {
    return ResourcesCompat.getFont(this, resId)
}

fun Context.openFile(path: String, applicationId: String): Boolean {
    var opened = true
    val uri = FileProvider.getUriForFile(this, "$applicationId.fileprovider", File(path))
    val intent = Intent(Intent.ACTION_VIEW)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .setData(uri)
        .also { Intent.createChooser(it, "Choose an application to open with:") }
    startActivity(intent,
        onActivityNotFound = {
            Timber.e(Throwable("Programs not found for open file"))
            opened = false
        })
    return opened
}

fun Context.shareFile(file: File, applicationId: String) {
    val uri = FileProvider.getUriForFile(this, "$applicationId.fileprovider", file)
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "*/*"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    val chooserIntent = Intent.createChooser(intent, getString(R.string.label_send_file))
    startActivity(chooserIntent)
}

fun Context.openMarketWithAppPage() {
    startActivity(
        Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")),
        onActivityNotFound = { openUrl("https://play.google.com/store/apps/details?id=$packageName") })
}

fun Context.startActivity(intent: Intent, onActivityNotFound: () -> Unit) {
    try {
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    } catch (e: ActivityNotFoundException) {
        onActivityNotFound.invoke()
    }
}

fun Context.shareBitmap(bitmap: Bitmap, title: String = "Share image", text: String? = null) {
    val path =
        MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Image Description", null)
            ?: return
    val uri = Uri.parse(path)
    val intent = Intent(Intent.ACTION_SEND)
        .setType("image/jpeg")
        .putExtra(Intent.EXTRA_STREAM, uri)
    text?.let { intent.putExtra(Intent.EXTRA_TEXT, text) }
    startActivity(Intent.createChooser(intent, title))
}

fun Context.shareBitmap(
    packageName: String,
    bitmap: Bitmap,
    title: String = "Share image",
    text: String? = null,
) {
    val path =
        MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Image Description", null)
            ?: return
    val uri = Uri.parse(path)
    val intent = Intent(Intent.ACTION_SEND)
        .setPackage(packageName)
        .setType("image/jpeg")
        .putExtra(Intent.EXTRA_STREAM, uri)
    text?.let { intent.putExtra(Intent.EXTRA_TITLE, text) }
    startActivity(Intent.createChooser(intent, title))
}

fun Context.isAppInstalled(packageName: String): Boolean =
    try {
        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }

fun Context.getHtmlText(@StringRes resId: Int): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(getString(resId), Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(getString(resId))
    }

fun Context.saveToClipBoard(s: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    val clip = ClipData.newPlainText("Copied text", s)
    clipboard?.setPrimaryClip(clip)
}

fun Context.openViaCustomChromeTab(url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setColorScheme(CustomTabsIntent.COLOR_SCHEME_LIGHT)
        .build()
        .launchUrl(this, Uri.parse(url))
}