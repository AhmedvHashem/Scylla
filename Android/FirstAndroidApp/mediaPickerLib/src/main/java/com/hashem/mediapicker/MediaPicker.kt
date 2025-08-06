package com.hashem.mediapicker

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * MediaPicker - A utility class for handling media picking operations in Android.
 * Supports picking single/multiple images from gallery, capturing images from camera,
 * and picking single/multiple documents.
 *
 * Supports Android API levels 26 to 35 with proper permission handling.
 */
class MediaPicker private constructor(
    private val activity: ComponentActivity?,
    private val fragment: Fragment?
) {
    private val context: Context = activity ?: fragment!!.requireContext()
    private var currentPhotoPath: String = ""
    private var photoUri: Uri? = null

    // Callback interfaces
    interface OnImagePickedListener {
        fun onImagePicked(uri: Uri)
    }

    interface OnMultipleImagesPickedListener {
        fun onMultipleImagesPicked(uris: List<Uri>)
    }

    interface OnDocumentPickedListener {
        fun onDocumentPicked(uri: Uri)
    }

    interface OnMultipleDocumentsPickedListener {
        fun onMultipleDocumentsPicked(uris: List<Uri>)
    }

    interface OnPermissionDeniedListener {
        fun onPermissionDenied()
    }

    // Listeners
    private var onImagePickedListener: OnImagePickedListener? = null
    private var onMultipleImagesPickedListener: OnMultipleImagesPickedListener? = null
    private var onDocumentPickedListener: OnDocumentPickedListener? = null
    private var onMultipleDocumentsPickedListener: OnMultipleDocumentsPickedListener? = null
    private var onPermissionDeniedListener: OnPermissionDeniedListener? = null

    // ActivityResultLaunchers for different operations
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var pickMultipleImagesLauncher: ActivityResultLauncher<Intent>
    private lateinit var captureImageLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickDocumentLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var pickMultipleDocumentsLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    // Permission request codes
    private var currentPermissionRequest: Int = 0
    private val PERMISSION_REQUEST_GALLERY = 1
    private val PERMISSION_REQUEST_CAMERA = 2
    private val PERMISSION_REQUEST_DOCUMENT = 3

    init {
        registerActivityResultLaunchers()
    }

    /**
     * Register all ActivityResultLaunchers for handling different operations
     */
    private fun registerActivityResultLaunchers() {
        if (activity != null) {
            registerActivityResultLaunchersForActivity()
        } else {
            registerActivityResultLaunchersForFragment()
        }
    }

    private fun registerActivityResultLaunchersForActivity() {
        activity?.let {
            // Pick single image launcher
            pickImageLauncher = it.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let { uri ->
                        onImagePickedListener?.onImagePicked(uri)
                    }
                }
            }

            // Pick multiple images launcher
            pickMultipleImagesLauncher = it.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val uris = mutableListOf<Uri>()

                    // Handle multiple selection
                    result.data?.clipData?.let { clipData ->
                        for (i in 0 until clipData.itemCount) {
                            uris.add(clipData.getItemAt(i).uri)
                        }
                    } ?: run {
                        // Handle single selection (when EXTRA_ALLOW_MULTIPLE is true but user selects one item)
                        result.data?.data?.let { uri ->
                            uris.add(uri)
                        }
                    }

                    onMultipleImagesPickedListener?.onMultipleImagesPicked(uris)
                }
            }

            // Capture image launcher
            captureImageLauncher = it.registerForActivityResult(
                ActivityResultContracts.TakePicture()
            ) { success ->
                if (success) {
                    photoUri?.let { uri ->
                        onImagePickedListener?.onImagePicked(uri)
                    }
                }
            }

            // Pick document launcher
            pickDocumentLauncher = it.registerForActivityResult(
                ActivityResultContracts.OpenDocument()
            ) { uri ->
                uri?.let {
                    // Take persistent permission for the URI
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    onDocumentPickedListener?.onDocumentPicked(uri)
                }
            }

            // Pick multiple documents launcher
            pickMultipleDocumentsLauncher = it.registerForActivityResult(
                ActivityResultContracts.OpenMultipleDocuments()
            ) { uris ->
                if (uris.isNotEmpty()) {
                    // Take persistent permission for each URI
                    uris.forEach { uri ->
                        context.contentResolver.takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                    }
                    onMultipleDocumentsPickedListener?.onMultipleDocumentsPicked(uris)
                }
            }

            // Permission request launcher
            requestPermissionLauncher = it.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    when (currentPermissionRequest) {
                        PERMISSION_REQUEST_GALLERY -> launchGalleryPicker()
                        PERMISSION_REQUEST_CAMERA -> launchCameraPicker()
                        PERMISSION_REQUEST_DOCUMENT -> launchDocumentPicker()
                    }
                } else {
                    onPermissionDeniedListener?.onPermissionDenied()
                }
            }
        }
    }

    private fun registerActivityResultLaunchersForFragment() {
        fragment?.let {
            // Pick single image launcher
            pickImageLauncher = it.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let { uri ->
                        onImagePickedListener?.onImagePicked(uri)
                    }
                }
            }

            // Pick multiple images launcher
            pickMultipleImagesLauncher = it.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val uris = mutableListOf<Uri>()

                    // Handle multiple selection
                    result.data?.clipData?.let { clipData ->
                        for (i in 0 until clipData.itemCount) {
                            uris.add(clipData.getItemAt(i).uri)
                        }
                    } ?: run {
                        // Handle single selection (when EXTRA_ALLOW_MULTIPLE is true but user selects one item)
                        result.data?.data?.let { uri ->
                            uris.add(uri)
                        }
                    }

                    onMultipleImagesPickedListener?.onMultipleImagesPicked(uris)
                }
            }

            // Capture image launcher
            captureImageLauncher = it.registerForActivityResult(
                ActivityResultContracts.TakePicture()
            ) { success ->
                if (success) {
                    photoUri?.let { uri ->
                        onImagePickedListener?.onImagePicked(uri)
                    }
                }
            }

            // Pick document launcher
            pickDocumentLauncher = it.registerForActivityResult(
                ActivityResultContracts.OpenDocument()
            ) { uri ->
                uri?.let {
                    // Take persistent permission for the URI
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                    onDocumentPickedListener?.onDocumentPicked(uri)
                }
            }

            // Pick multiple documents launcher
            pickMultipleDocumentsLauncher = it.registerForActivityResult(
                ActivityResultContracts.OpenMultipleDocuments()
            ) { uris ->
                if (uris.isNotEmpty()) {
                    // Take persistent permission for each URI
                    uris.forEach { uri ->
                        context.contentResolver.takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                    }
                    onMultipleDocumentsPickedListener?.onMultipleDocumentsPicked(uris)
                }
            }

            // Permission request launcher
            requestPermissionLauncher = it.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    when (currentPermissionRequest) {
                        PERMISSION_REQUEST_GALLERY -> launchGalleryPicker()
                        PERMISSION_REQUEST_CAMERA -> launchCameraPicker()
                        PERMISSION_REQUEST_DOCUMENT -> launchDocumentPicker()
                    }
                } else {
                    onPermissionDeniedListener?.onPermissionDenied()
                }
            }
        }
    }

    /**
     * Check if the required permission is granted
     */
    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Request the required permission
     */
    private fun requestPermission(permission: String, requestCode: Int) {
        currentPermissionRequest = requestCode
        requestPermissionLauncher.launch(permission)
    }

    /**
     * Get the appropriate permission for accessing images based on API level
     */
    private fun getImagePermission(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    }

    /**
     * Create a temporary file for storing the captured image
     */
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    /**
     * Get a content URI for the image file using FileProvider
     */
    private fun getUriForFile(file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    /**
     * Launch the gallery picker for selecting a single image
     */
    private fun launchGalleryPicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    /**
     * Launch the gallery picker for selecting multiple images
     */
    private fun launchMultipleGalleryPicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        pickMultipleImagesLauncher.launch(intent)
    }

    /**
     * Launch the camera picker for capturing an image
     */
    private fun launchCameraPicker() {
        try {
            val photoFile = createImageFile()
            photoUri = getUriForFile(photoFile)
            captureImageLauncher.launch(photoUri!!)
        } catch (ex: Exception) {
            Log.e(TAG, "Error creating image file: ${ex.message}")
        }
    }

    /**
     * Launch the document picker for selecting a single document
     */
    private fun launchDocumentPicker() {
        pickDocumentLauncher.launch(arrayOf("*/*"))
    }

    /**
     * Launch the document picker for selecting multiple documents
     */
    private fun launchMultipleDocumentPicker() {
        pickMultipleDocumentsLauncher.launch(arrayOf("*/*"))
    }

    /**
     * Pick a single image from the gallery
     */
    fun pickImage() {
        val permission = getImagePermission()
        if (checkPermission(permission)) {
            launchGalleryPicker()
        } else {
            requestPermission(permission, PERMISSION_REQUEST_GALLERY)
        }
    }

    /**
     * Pick multiple images from the gallery
     */
    fun pickMultipleImages() {
        val permission = getImagePermission()
        if (checkPermission(permission)) {
            launchMultipleGalleryPicker()
        } else {
            requestPermission(permission, PERMISSION_REQUEST_GALLERY)
        }
    }

    /**
     * Take an image using the camera
     */
    fun takeImage() {
        if (checkPermission(Manifest.permission.CAMERA)) {
            launchCameraPicker()
        } else {
            requestPermission(Manifest.permission.CAMERA, PERMISSION_REQUEST_CAMERA)
        }
    }

    /**
     * Pick a single document
     */
    fun pickDocument() {
        val permission = getImagePermission()
        if (checkPermission(permission)) {
            launchDocumentPicker()
        } else {
            requestPermission(permission, PERMISSION_REQUEST_DOCUMENT)
        }
    }

    /**
     * Pick multiple documents
     */
    fun pickMultipleDocuments() {
        val permission = getImagePermission()
        if (checkPermission(permission)) {
            launchMultipleDocumentPicker()
        } else {
            requestPermission(permission, PERMISSION_REQUEST_DOCUMENT)
        }
    }

    /**
     * Set the listener for single image picking
     */
    fun setOnImagePickedListener(listener: OnImagePickedListener): MediaPicker {
        this.onImagePickedListener = listener
        return this
    }

    /**
     * Set the listener for multiple image picking
     */
    fun setOnMultipleImagesPickedListener(listener: OnMultipleImagesPickedListener): MediaPicker {
        this.onMultipleImagesPickedListener = listener
        return this
    }

    /**
     * Set the listener for single document picking
     */
    fun setOnDocumentPickedListener(listener: OnDocumentPickedListener): MediaPicker {
        this.onDocumentPickedListener = listener
        return this
    }

    /**
     * Set the listener for multiple document picking
     */
    fun setOnMultipleDocumentsPickedListener(listener: OnMultipleDocumentsPickedListener): MediaPicker {
        this.onMultipleDocumentsPickedListener = listener
        return this
    }

    /**
     * Set the listener for permission denied events
     */
    fun setOnPermissionDeniedListener(listener: OnPermissionDeniedListener): MediaPicker {
        this.onPermissionDeniedListener = listener
        return this
    }

    companion object {
        private const val TAG = "MediaPicker"

        /**
         * Create a new instance of MediaPicker for an Activity
         */
        fun with(activity: ComponentActivity): MediaPicker {
            return MediaPicker(activity, null)
        }

        /**
         * Create a new instance of MediaPicker for a Fragment
         */
        fun with(fragment: Fragment): MediaPicker {
            return MediaPicker(null, fragment)
        }
    }
}
