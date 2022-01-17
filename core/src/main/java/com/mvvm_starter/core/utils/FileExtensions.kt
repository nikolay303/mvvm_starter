package com.mvvm_starter.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.*
import java.util.*
import android.provider.OpenableColumns


/**
 * Created by Nikolay Siliuk on 12/21/20.
 */

fun File.getMimeType(): String {
    val extension = MimeTypeMap.getFileExtensionFromUrl(path)
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).orEmpty()
}

fun File.isImageMimeType(): Boolean {
    return getMimeType().split("/").firstOrNull() == "image"
}

@SuppressLint("Range")
fun Context.createTempFile(uri: Uri): File? {
    try {
        var fileName = "temp_${UUID.randomUUID()}"
        if (uri.scheme.equals("content")) {
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
            cursor?.close()
        }
        val destination = File(cacheDir, fileName)
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor ?: return null
        val src = FileInputStream(fileDescriptor).channel
        val dst = FileOutputStream(destination).channel
        dst.transferFrom(src, 0, src.size())
        src.close()
        dst.close()
        return destination
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

fun Bitmap.toFile(context: Context): File? {
    try {
        val destination = File(context.cacheDir, "temp_image_${UUID.randomUUID()}.png")
        destination.createNewFile()
        val bos = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.PNG, 0/*ignored for PNG*/, bos)
        val data = bos.toByteArray()
        val fos = FileOutputStream(destination)
        fos.write(data)
        fos.flush()
        fos.close()
        return destination
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    return null
}