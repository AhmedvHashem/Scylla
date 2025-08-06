package com.eventtus.android.imagepicker

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
//import androidx.lifecycle.LifecycleOwner
//import com.eventtus.android.imagepicker.ImagePickerBottomSheetFragment.Companion.CHOICE_REQUEST_KEY
//import com.eventtus.android.imagepicker.ImagePickerBottomSheetFragment.Companion.CHOICE_RESULT_KEY
import java.io.File
import java.io.IOException

class ImagePicker private constructor(
    private val fragment: Fragment?,
    private val activity: Activity?,
    private val enableCamera: Boolean,
    private val enableGallery: Boolean
) {

    private var currentPhotoPath: String? = null
    private var sourceToOpen: Source? = null

    data class Result(val source: Source, val uri: Uri, val path: String?)

    enum class Source {
        GALLERY,
        CAMERA
    }

    class Builder {

        private var fragment: Fragment? = null

        private var activity: Activity? = null

        private var enableGallery: Boolean = false

        private var enableCamera: Boolean = false

        fun withActivity(activity: Activity): Builder {
            this.activity = activity
            return this
        }

        fun withFragment(fragment: Fragment): Builder {
            this.fragment = fragment
            return this
        }

        fun enableGallery(enable: Boolean): Builder {
            enableGallery = enable
            return this
        }

        fun enableCamera(enable: Boolean): Builder {
            enableCamera = enable
            return this
        }

        fun build(): ImagePicker {
            if (fragment == null && activity == null) {
                throw  IllegalArgumentException("You must provide a fragment or activity.")
            }

            if (fragment != null && activity != null) {
                throw  IllegalArgumentException("You must provide one fragment or one activity not both.")
            }

            return ImagePicker(
                fragment, activity,
                enableCamera = enableCamera, enableGallery = enableGallery
            )
        }
    }

    fun show() {
        if (enableCamera && !enableGallery) {
            openCamera()
            return
        }

        if (enableGallery && !enableCamera) {
            openGallery()
            return
        }

        showImagePickerDialog()
    }

    fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        result: (Result) -> Unit
    ) {

        when (resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    OPEN_GALLERY_REQUEST_CODE -> handleGallery(data?.data, result)
                    OPEN_CAMERA_REQUEST_CODE -> handleCamera(result)
                }
            }
            Activity.RESULT_CANCELED -> {
                when (requestCode) {
                    RC_SETTINGS_SCREEN_PERM -> {
                        val permissions =
                            listOf(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        val permissionGranted = permissions.filter {
                            getContext().checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
                        }.size == permissions.size

                        if (permissionGranted) {
                            show()
                        }
                    }
                }
            }
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        @Suppress("UNUSED_PARAMETER") permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            GALLERY_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkSourceToOpen()
                } else {
                    openSettingsForAllowingPermissions()
                }
            }
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    checkSourceToOpen()
                } else {
                    openSettingsForAllowingPermissions()
                }
            }
        }
    }

    private fun openSettingsForAllowingPermissions() {
        when {
            activity != null -> {
                openSettingsDialog(activity)
            }
            fragment != null -> {
                openSettingsDialog(fragment.activity as Activity)
            }
        }
    }

    private fun openSettingsDialog(activity: Activity) {
//        AppSettingsDialog.Builder(activity)
//            .setThemeResId(android.R.style.Theme_Material_Dialog_NoActionBar)
//            .setTitle(getContext().getString(R.string.image_picker_access_required))
//            .setRationale(getContext().getString(R.string.image_picker_camera_permission))
//            .setPositiveButton(getContext().getString(R.string.image_picker_settings))
//            .setNegativeButton(getContext().getString(R.string.image_picker_cancel))
//            .setRequestCode(RC_SETTINGS_SCREEN_PERM)
//            .build()
//            .show()
    }

    private fun checkPermissions(permissions: List<String>, requestCode: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionGranted = permissions.filter {
                getContext().checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
            }.size == permissions.size

            if (permissionGranted) {
                true
            } else {
                when {
                    activity != null -> {
                        ActivityCompat.requestPermissions(
                            activity,
                            permissions.toTypedArray(),
                            requestCode
                        )
                    }
                    fragment != null -> {
                        fragment.requestPermissions(permissions.toTypedArray(), requestCode)
                    }
                    else -> {
                        throw IllegalArgumentException("You must provide a fragment or activity.")
                    }
                }
                false
            }
        } else {
            true
        }
    }

    private fun handleGallery(uri: Uri?, result: (Result) -> Unit) {
        uri?.let {
            result.invoke(
                Result(
                    source = Source.GALLERY,
                    uri = it,
                    path = ImagePickerUtils.getFilePathFromURI(getContext(), uri)
                )
            )
        }
    }

    private fun handleCamera(result: (Result) -> Unit) {
        currentPhotoPath?.let {
            result.invoke(
                Result(
                    source = Source.CAMERA,
                    uri = Uri.fromFile(File(it)),
                    path = it
                )
            )
        }
    }

    private fun openGallery() {
        sourceToOpen = Source.GALLERY
        if (checkPermissions(
                listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                GALLERY_PERMISSION_REQUEST_CODE
            )
        ) {
            val gallery = Intent(Intent.ACTION_PICK)
            gallery.type = "image/*"
            gallery.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(gallery, OPEN_GALLERY_REQUEST_CODE)
        }
    }

    private fun openCamera() {
        sourceToOpen = Source.CAMERA
        val permissions =
            listOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (checkPermissions(permissions, CAMERA_PERMISSION_REQUEST_CODE)) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                // Ensure that there's a camera activity to handle the intent
                takePictureIntent.resolveActivity(getContext().packageManager)?.also {
                    // Create the File where the photo should go
                    val photoFile: File? = try {
                        ImagePickerUtils.createImageFile(getContext()).apply {
                            this?.let {
                                // Save a file: path for use with ACTION_VIEW intents
                                currentPhotoPath = absolutePath
                            }
                        }
                    } catch (ex: IOException) {
//                        Timber.e(ex.toString())
                        null
                    }
                    // Continue only if the File was successfully created
                    val authority = getContext().applicationContext.packageName + ".fileprovider"
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            getContext(),
                            authority,
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, OPEN_CAMERA_REQUEST_CODE)
                    }
                }
            }
        }
    }

    private fun checkSourceToOpen() {
        when (sourceToOpen) {
            Source.GALLERY -> openGallery()
            Source.CAMERA -> openCamera()
            else -> {}
        }
    }

    private fun startActivityForResult(intent: Intent, requestCode: Int) {
        when {
            activity != null -> activity.startActivityForResult(intent, requestCode)
            fragment != null -> fragment.startActivityForResult(intent, requestCode)
            else -> throw IllegalArgumentException("You must provide a fragment or activity.")
        }
    }

    private fun getContext(): Context {
        return activity ?: fragment?.requireContext()
        ?: throw IllegalArgumentException("You must provide a fragment or activity.")
    }

    private fun showImagePickerDialog() {
//        (getContext() as? AppCompatActivity)?.supportFragmentManager?.let { fm ->
//            fm.setFragmentResultListener(
//                CHOICE_REQUEST_KEY,
//                activity as LifecycleOwner
//            ) { key, result ->
//                val choice = result.getString(CHOICE_RESULT_KEY) ?: ""
//                when (ImagePickerItems.toEnum(choice)) {
//                    ImagePickerItems.UPLOAD -> {
//                        openGallery()
//                    }
//                    ImagePickerItems.TAKE_PHOTO -> {
//                        openCamera()
//                    }
//                }
//            }
//
//            val dialog = ImagePickerBottomSheetFragment.newInstance()
//            dialog.show(fm, ImagePickerBottomSheetFragment.javaClass.name)
//        }
    }

    companion object {
        private const val OPEN_GALLERY_REQUEST_CODE = 9875
        private const val OPEN_CAMERA_REQUEST_CODE = 9876

        private const val GALLERY_PERMISSION_REQUEST_CODE = 9877
        private const val CAMERA_PERMISSION_REQUEST_CODE = 9878

        private const val RC_SETTINGS_SCREEN_PERM = 123
    }

    enum class ImagePickerItems(val value: String) {
        UPLOAD("upload"),
        TAKE_PHOTO("take-photo");

        companion object {
            fun toEnum(value: String): ImagePickerItems {
                return when (value) {
                    UPLOAD.value -> UPLOAD
                    TAKE_PHOTO.value -> TAKE_PHOTO
                    else -> UPLOAD
                }
            }
        }
    }
}