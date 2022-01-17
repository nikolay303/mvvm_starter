package com.mvvm_starter.core.utils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import com.mvvm_starter.core.R


class DownloadHelper(private val context: Context) {

    fun startDownload(
        url: String?,
        myFileName: String? = null,
        onSuccess: (fileName: String, path: String) -> Unit,
        onError: (Throwable) -> Unit = {},
    ) {
        val fileExtension = url?.substringAfterLast(delimiter = ".") ?: return
        val fileName = myFileName?.let { "$it.$fileExtension" }
            ?: url.substringAfterLast(delimiter = "/")
        if (url.isNullOrBlank()) {
            onError.invoke(Exception("Wrong url").also(Throwable::printStackTrace))
            return
        }
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setDescription(context.getString(R.string.label_loading))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
        val downloadId = downloadManager?.enqueue(request)

        val downloadCompleteReceiver = downloadCompleteReceiver(downloadId, fileName, onSuccess = {
            "${context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath}/$it"
                .also { path -> onSuccess.invoke(it, path) }
        })
        context.registerReceiver(
            downloadCompleteReceiver,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    private fun downloadCompleteReceiver(
        id: Long?,
        fileName: String,
        onSuccess: (String) -> Unit,
    ): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {
                val downloadId = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (downloadId != null && downloadId == id) {
                    onSuccess.invoke(fileName)
                    context?.unregisterReceiver(this)
                }
            }
        }
    }
}