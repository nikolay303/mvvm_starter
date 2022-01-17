package com.mvvm_starter.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import java.io.File


/**
 * Created by Nikolay Siliuk on 10.12.21.
 */

class FileChooseHelper private constructor(
    private val context: Context,
    private val registry: ActivityResultRegistry,
) : DefaultLifecycleObserver {
    companion object {
        private const val KEY_CHOOSE_IMAGE = "key_choose_image"
        private const val KEY_CHOOSE_IMAGES = "key_choose_images"
        private const val KEY_CHOOSE_FILE = "key_choose_file"
        private const val KEY_CHOOSE_FILES = "key_choose_files"
        private const val KEY_CAPTURE_PHOTO = "key_take_photo"

        private const val MIME_TYPE_IMAGE = "image/*"
        private val MIME_TYPES_DOCUMENTS = listOf(
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
            "application/vnd.ms-powerpoint",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
            "text/plain",
            "application/pdf",
            "application/zip"
        )

        fun create(
            activity: FragmentActivity,
            lifecycle: Lifecycle,
        ): FileChooseHelper {
            return FileChooseHelper(activity, activity.activityResultRegistry).apply {
                lifecycle.addObserver(this)
            }
        }
    }

    private lateinit var chooseImageLauncher: ActivityResultLauncher<String>
    private lateinit var chooseImagesLauncher: ActivityResultLauncher<String>
    private lateinit var chooseFileLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var chooseFilesLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var capturePhotoLauncher: ActivityResultLauncher<Intent>

    private var multipleSelectionLimit = Int.MAX_VALUE

    private var onImageSelected: (File) -> Unit = {}
    private var onImagesSelected: (List<File>) -> Unit = {}
    private var onFileSelected: (File) -> Unit = {}
    private var onFilesSelected: (List<File>) -> Unit = {}
    private var onPhotoTaken: (File) -> Unit = {}

    override fun onCreate(owner: LifecycleOwner) {
        chooseImageLauncher = registerImageChoose(owner)
        chooseImagesLauncher = registerImagesChoose(owner)
        chooseFileLauncher = registerFileChoose(owner)
        chooseFilesLauncher = registerFilesChoose(owner)
        capturePhotoLauncher = registerPhotoCapture(owner)
    }

    private fun registerImageChoose(owner: LifecycleOwner) = registry.register(
        KEY_CHOOSE_IMAGE,
        owner,
        ActivityResultContracts.GetContent()
    ) { result ->
        if (result != null) {
            context.createTempFile(result)?.let(onImageSelected)
        }
    }

    private fun registerImagesChoose(owner: LifecycleOwner) = registry.register(
        KEY_CHOOSE_IMAGES,
        owner,
        ActivityResultContracts.GetMultipleContents()
    ) { result ->
        result.take(multipleSelectionLimit)
            .mapNotNull { context.createTempFile(it) }
            .also(onImagesSelected)
    }

    private fun registerFileChoose(owner: LifecycleOwner) = registry.register(
        KEY_CHOOSE_FILE,
        owner,
        ActivityResultContracts.OpenDocument()
    ) { result ->
        if (result != null) {
            context.createTempFile(result)?.let(onFileSelected)
        }
    }

    private fun registerFilesChoose(owner: LifecycleOwner) = registry.register(
        KEY_CHOOSE_FILES,
        owner,
        ActivityResultContracts.OpenMultipleDocuments()
    ) { result ->
        result.take(multipleSelectionLimit)
            .mapNotNull { context.createTempFile(it) }
            .also(onFilesSelected)
    }

    private fun registerPhotoCapture(owner: LifecycleOwner) = registry.register(
        KEY_CAPTURE_PHOTO,
        owner,
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val bundle = result.data?.extras ?: return@register
            val bitmap = bundle.get("data") as Bitmap
            bitmap.toFile(context)?.let(onPhotoTaken)
        }
    }

    fun selectImage(onImageSelected: (File) -> Unit) {
        this.onImageSelected = onImageSelected
        chooseImageLauncher.launch(MIME_TYPE_IMAGE)
    }

    fun selectImages(limit: Int, onImagesSelected: (List<File>) -> Unit) {
        this.multipleSelectionLimit = limit
        this.onImagesSelected = onImagesSelected
        chooseImagesLauncher.launch(MIME_TYPE_IMAGE)
    }

    fun selectFile(onFileSelected: (File) -> Unit) {
        this.onFileSelected = onFileSelected
        chooseFileLauncher.launch(MIME_TYPES_DOCUMENTS.toTypedArray())
    }

    fun selectFiles(limit: Int, onFilesSelected: (List<File>) -> Unit) {
        this.multipleSelectionLimit = limit
        this.onFilesSelected = onFilesSelected
        chooseFilesLauncher.launch(MIME_TYPES_DOCUMENTS.toTypedArray())
    }

    fun takePhoto(activity: FragmentActivity, onPhotoTaken: (File) -> Unit) {
//      Todo need request permission Manifest.permission.CAMERA
        this.onPhotoTaken = onPhotoTaken
        val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        capturePhotoLauncher.launch(takePhotoIntent)
    }
}


