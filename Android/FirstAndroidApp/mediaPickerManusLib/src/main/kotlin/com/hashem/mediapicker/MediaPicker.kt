package com.hashem.mediapicker

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Manages the logic for picking media/documents and handling permissions.
 * This class uses the Activity Result APIs.
 */
class MediaPicker(registryOwner: Any) {

    /**
     * Enum defining the possible actions.
     */
    enum class Action {
        PICK_SINGLE_IMAGE,
        PICK_MULTIPLE_IMAGES,
        TAKE_IMAGE,
        PICK_SINGLE_DOCUMENT,
        PICK_MULTIPLE_DOCUMENTS
    }

    private val context: Context
    private val activityResultRegistry: ActivityResultRegistry
    private val lifecycleOwner: LifecycleOwner

    init {
        when (registryOwner) {
            is ComponentActivity -> {
                context = registryOwner
                activityResultRegistry = registryOwner.activityResultRegistry
                lifecycleOwner = registryOwner
            }

            is Fragment -> {
                context = registryOwner.requireContext()
                activityResultRegistry = registryOwner.requireActivity().activityResultRegistry
                lifecycleOwner = registryOwner
            }

            else -> throw IllegalArgumentException("registryOwner must be a FragmentActivity or Fragment")
        }
        // Initialize launchers immediately
        initializeLaunchers()
    }

    // Permission Launcher
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    // Pick Image Launchers
    private lateinit var pickSingleVisualMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickMultipleVisualMediaLauncher: ActivityResultLauncher<PickVisualMediaRequest>

    // Take Image Launcher
    private lateinit var takeImageLauncher: ActivityResultLauncher<Uri>

    // Document Launchers
    private lateinit var pickSingleDocumentLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var pickMultipleDocumentsLauncher: ActivityResultLauncher<Array<String>>

    // --- State --- 
    private var pendingAction: Action? = null
    private var cameraImageUri: Uri? = null // To store URI for camera capture
    private var onResult: ((MediaPickerResult) -> Unit)? = null

    /**
     * Launches the specified media picker action after checking permissions.
     * @param action The desired action (e.g., PICK_SINGLE_IMAGE).
     * @param callback The function to call with the result (MediaPickerResult).
     */
    fun launch(action: Action, callback: (MediaPickerResult) -> Unit) {
        this.onResult = callback
        this.pendingAction = action
        checkPermissionsAndLaunch()
    }

    // --- Initialization (called in init block) ---
    private fun initializeLaunchers() {
        requestPermissionLauncher = activityResultRegistry.register(
            "requestPermissions",
            lifecycleOwner,
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionsResult ->
            handlePermissionResult(permissionsResult)
        }

        pickSingleVisualMediaLauncher = activityResultRegistry.register(
            "pickSingleVisualMedia", lifecycleOwner, ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            handleSingleMediaResult(uri)
        }

        pickMultipleVisualMediaLauncher = activityResultRegistry.register(
            "pickMultipleVisualMedia",
            lifecycleOwner,
            ActivityResultContracts.PickMultipleVisualMedia()
        ) { uris ->
            handleMultipleMediaResult(uris)
        }

        takeImageLauncher = activityResultRegistry.register(
            "captureImage", lifecycleOwner, ActivityResultContracts.TakePicture()
        ) { success ->
            handleCameraCaptureResult(success)
        }

        // Initialize Document launchers
        pickSingleDocumentLauncher = activityResultRegistry.register(
            "pickSingleDocument",
            lifecycleOwner,
            ActivityResultContracts.OpenDocument() // Contract for single document
        ) { uri ->
            handleSingleDocumentResult(uri)
        }

        pickMultipleDocumentsLauncher = activityResultRegistry.register(
            "pickMultipleDocuments",
            lifecycleOwner,
            ActivityResultContracts.OpenMultipleDocuments() // Contract for multiple documents
        ) { uris ->
            handleMultipleDocumentsResult(uris)
        }
    }

    // --- Permission Handling ---
    private fun checkPermissionsAndLaunch() {
        val action = pendingAction ?: return
        val requiredPermissions = getRequiredPermissions(action)

        if (requiredPermissions.isEmpty() || hasPermissions(requiredPermissions)) {
            Log.d(TAG, "Permissions check passed for action: $action. Launching...")
            proceedWithAction(action)
        } else {
            Log.d(
                TAG,
                "Requesting permissions for action: $action: ${requiredPermissions.joinToString()}"
            )
            requestPermissionLauncher.launch(requiredPermissions.toTypedArray())
        }
    }

    private fun handlePermissionResult(permissionsResult: Map<String, Boolean>) {
        val action = pendingAction ?: return
        val requiredPermissions = getRequiredPermissions(action)
        val allRequiredGranted = requiredPermissions.all { permissionsResult[it] == true }

        if (allRequiredGranted) {
            Log.d(TAG, "Permissions granted after request for action: $action. Launching...")
            proceedWithAction(action)
        } else {
            Log.w(TAG, "Required permissions denied for action: $action")
            onResult?.invoke(MediaPickerResult.Error(context.getString(R.string.permission_denied_message)))
            resetState()
        }
    }

    private fun getRequiredPermissions(action: Action): List<String> {
        return when (action) {
            Action.PICK_SINGLE_IMAGE, Action.PICK_MULTIPLE_IMAGES -> {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                } else {
                    emptyList() // Let PickVisualMedia handle its flow
                }
            }

            Action.TAKE_IMAGE -> listOf(Manifest.permission.CAMERA)
            // OpenDocument contracts do not require explicit storage permissions
            Action.PICK_SINGLE_DOCUMENT, Action.PICK_MULTIPLE_DOCUMENTS -> emptyList()
        }
    }

    private fun hasPermissions(permissions: List<String>): Boolean {
        if (permissions.isEmpty()) return true
        return permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    // --- Action Execution (called after permission check) ---
    private fun proceedWithAction(action: Action) {
        when (action) {
            Action.PICK_SINGLE_IMAGE -> launchPickSingleImage()
            Action.PICK_MULTIPLE_IMAGES -> launchPickMultipleImages()
            Action.TAKE_IMAGE -> launchTakeImage()
            Action.PICK_SINGLE_DOCUMENT -> launchPickSingleDocument()
            Action.PICK_MULTIPLE_DOCUMENTS -> launchPickMultipleDocuments()
        }
    }

    // --- Launch Methods ---
    private fun launchPickSingleImage() {
        Log.d(TAG, "Launching single image picker (PickVisualMedia)")
        pickSingleVisualMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun launchPickMultipleImages() {
        Log.d(TAG, "Launching multiple image picker (PickMultipleVisualMedia)")
        pickMultipleVisualMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun launchTakeImage() {
        Log.d(TAG, "Attempting to launch camera capture")
        cameraImageUri = createImageFileUri()
        if (cameraImageUri != null) {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(context.packageManager) != null) {
                Log.d(TAG, "Launching camera app with URI: $cameraImageUri")
                takeImageLauncher.launch(cameraImageUri!!)
            } else {
                Log.e(TAG, "No camera app found to handle intent")
                onResult?.invoke(MediaPickerResult.Error(context.getString(R.string.error_no_camera)))
                resetState()
            }
        } else {
            Log.e(TAG, "Failed to create URI for camera capture")
            resetState()
        }
    }

    private fun launchPickSingleDocument() {
        Log.d(TAG, "Launching single document picker (OpenDocument)")
        // Launch the standard document picker for a single file.
        // Using "*/*" allows picking any file type.
        // You could restrict this with specific MIME types like arrayOf("application/pdf", "text/plain")
        pickSingleDocumentLauncher.launch(arrayOf("*/*"))
    }

    private fun launchPickMultipleDocuments() {
        Log.d(TAG, "Launching multiple document picker (OpenMultipleDocuments)")
        // Launch the standard document picker for multiple files.
        pickMultipleDocumentsLauncher.launch(arrayOf("*/*"))
    }

    // --- Result Handling ---
    private fun handleSingleMediaResult(uri: Uri?) {
        if (uri != null) {
            Log.d(TAG, "Single media selected: $uri")
            onResult?.invoke(MediaPickerResult.SuccessSingle(uri))
        } else {
            Log.d(TAG, "Single media selection cancelled or failed.")
            onResult?.invoke(MediaPickerResult.Cancelled)
        }
        resetState()
    }

    private fun handleMultipleMediaResult(uris: List<Uri>) {
        if (uris.isNotEmpty()) {
            Log.d(TAG, "Multiple media selected: ${uris.size} items")
            onResult?.invoke(MediaPickerResult.SuccessMultiple(uris))
        } else {
            Log.d(TAG, "Multiple media selection cancelled or failed.")
            onResult?.invoke(MediaPickerResult.Cancelled)
        }
        resetState()
    }

    private fun handleCameraCaptureResult(success: Boolean) {
        if (success && cameraImageUri != null) {
            Log.d(TAG, "Camera capture successful. Image saved to: $cameraImageUri")
            onResult?.invoke(MediaPickerResult.SuccessSingle(cameraImageUri!!))
        } else {
            Log.w(
                TAG,
                "Camera capture failed or was cancelled. Success flag: $success, URI: $cameraImageUri"
            )
            if (cameraImageUri == null && pendingAction == Action.TAKE_IMAGE) {
                // Error likely occurred during URI creation, already reported
            } else {
                onResult?.invoke(MediaPickerResult.Error(context.getString(R.string.error_capture_failed)))
            }
        }
        resetState()
    }

    private fun handleSingleDocumentResult(uri: Uri?) {
        if (uri != null) {
            Log.d(TAG, "Single document selected: $uri")
            onResult?.invoke(MediaPickerResult.SuccessSingle(uri))
        } else {
            Log.d(TAG, "Single document selection cancelled or failed.")
            onResult?.invoke(MediaPickerResult.Cancelled)
        }
        resetState()
    }

    private fun handleMultipleDocumentsResult(uris: List<Uri>) {
        if (uris.isNotEmpty()) {
            Log.d(TAG, "Multiple documents selected: ${uris.size} items")
            onResult?.invoke(MediaPickerResult.SuccessMultiple(uris))
        } else {
            Log.d(TAG, "Multiple document selection cancelled or failed.")
            onResult?.invoke(MediaPickerResult.Cancelled)
        }
        resetState()
    }

    // --- Utility Functions ---
    private fun createImageFileUri(): Uri? {
        return try {
            val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(Date())
            val storageDir: File? = context.getExternalFilesDir(null)
            if (storageDir != null && !storageDir.exists()) {
                storageDir.mkdirs()
            }
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            val authority = "${context.packageName}.fileprovider"
            Log.d(
                TAG, "Creating file URI with authority: $authority for file: ${file.absolutePath}"
            )
            FileProvider.getUriForFile(context, authority, file)
        } catch (ex: Exception) {
            Log.e(TAG, "Error creating image file URI", ex)
            onResult?.invoke(
                MediaPickerResult.Error(
                    context.getString(R.string.error_creating_file), ex
                )
            )
            null
        }
    }

    private fun resetState() {
        // Reset pending action and temporary URI, keep listener
        pendingAction = null
        cameraImageUri = null
    }

    companion object {
        private const val TAG = "MediaPicker"
    }
}

