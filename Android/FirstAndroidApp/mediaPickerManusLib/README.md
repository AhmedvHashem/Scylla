# MediaPickerModule Documentation

This document provides instructions on how to integrate and use the `MediaPickerModule`, an Android library for picking images/documents from the gallery, capturing images from the camera, and handling related permissions.

## Overview

The module provides the following functionalities:

*   Pick a single image from the gallery.
*   Pick multiple images from the gallery.
*   Capture an image using the device camera.
*   Pick a single document (any file type).
*   Pick multiple documents (any file type).

It uses modern Android APIs (`ActivityResultContracts`, `FileProvider`, `PickVisualMedia`) for robust and compliant media handling. It supports Android API levels 24 through 35 and adheres to Google Play Store's photo and video permission policies.

The primary UI component is `MediaPickerModalFragment`, a bottom sheet dialog that presents the available actions to the user.

## Integration

1.  **Add the AAR file:**
    *   Place the generated `mediapicker.aar` file into the `libs` directory of your Android application module (create the `libs` directory if it doesn't exist).
    *   Open your app-level `build.gradle` or `build.gradle.kts` file.
    *   Add the following dependency:

    ```groovy
    // build.gradle (Groovy)
    dependencies {
        implementation files('libs/mediapicker.aar')
        // Ensure you also have these core dependencies (versions might vary)
        implementation 'androidx.core:core-ktx:1.13.1'
        implementation 'androidx.appcompat:appcompat:1.7.0'
        implementation 'com.google.android.material:material:1.12.0'
        implementation 'androidx.activity:activity-ktx:1.9.0'
        implementation 'androidx.fragment:fragment-ktx:1.8.0'
    }
    ```

    ```kotlin
    // build.gradle.kts (Kotlin)
    dependencies {
        implementation(files("libs/mediapicker.aar"))
        // Ensure you also have these core dependencies (versions might vary)
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.activity:activity-ktx:1.9.0")
        implementation("androidx.fragment:fragment-ktx:1.8.0")
    }
    ```

2.  **Configure FileProvider:**
    *   The library includes a `FileProvider` declaration in its manifest. However, the `authorities` attribute uses `${applicationId}.fileprovider`. You **must** define the `file_paths.xml` resource in your **main application** to specify which paths the provider can access for camera captures.
    *   Create an XML resource directory (`res/xml`) if you don't have one.
    *   Create a file named `file_paths.xml` inside `res/xml` with the following content (or merge if you already have one):

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <paths xmlns:android="http://schemas.android.com/apk/res/android">
        <!-- Path for temporary camera images stored in cache -->
        <cache-path name="captured_media" path="." />
        <!-- Add other paths if needed by your app -->
    </paths>
    ```
    *   Ensure your application's `AndroidManifest.xml` **does not** declare another provider with the same authority (`${applicationId}.fileprovider`). The library's manifest provides this.

3.  **Permissions:**
    *   The library's `AndroidManifest.xml` declares the necessary permissions:
        *   `android.permission.READ_MEDIA_IMAGES` (for API 33+)
        *   `android.permission.READ_MEDIA_VISUAL_USER_SELECTED` (for API 34+ partial access)
        *   `android.permission.READ_EXTERNAL_STORAGE` (maxSdkVersion 32)
        *   `android.permission.CAMERA`
    *   It also declares the camera feature as optional (`android.hardware.camera` with `required="false"`).
    *   These permissions will be merged into your app's manifest. The library handles requesting these permissions at runtime when needed.

## Usage

There are two main ways to use the module:

### 1. Using `MediaPickerModalFragment` (Recommended)

This is the simplest way to integrate the picker with a user interface.

* **Implement the Listener:** Your `Activity` or `Fragment` that shows the modal needs to implement the `MediaPickerModalFragment.MediaPickerListener` interface.

  ```kotlin
  import androidx.appcompat.app.AppCompatActivity
  import android.os.Bundle
  import android.util.Log
  import android.widget.Button
  import android.widget.TextView
  import com.hashem.mediapicker.MediaPickerManager
  import com.hashem.mediapicker.MediaPickerModalFragment
  import com.hashem.mediapicker.MediaPickerResult

  class MainActivity : AppCompatActivity(), MediaPickerModalFragment.MediaPickerListener {

      private lateinit var mediaPickerManager: MediaPickerManager
      private lateinit var resultTextView: TextView

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main) // Your activity layout

          // Initialize the manager (needed to handle the actions selected in the modal)
          mediaPickerManager = MediaPickerManager(this) // Pass Activity or Fragment

          resultTextView = findViewById(R.id.result_text_view) // Add a TextView to your layout
          val showPickerButton: Button = findViewById(R.id.show_picker_button) // Add a Button

          showPickerButton.setOnClickListener {
              // Show the bottom sheet modal
              MediaPickerModalFragment.show(supportFragmentManager)
          }
      }

      // --- MediaPickerListener Implementation ---

      override fun onPickSingleImageSelected() {
          mediaPickerManager.launch(MediaPickerManager.Action.PICK_SINGLE_IMAGE) { handleResult(it) }
      }

      override fun onPickMultipleImagesSelected() {
          mediaPickerManager.launch(MediaPickerManager.Action.PICK_MULTIPLE_IMAGES) { handleResult(it) }
      }

      override fun onCaptureImageSelected() {
          mediaPickerManager.launch(MediaPickerManager.Action.CAPTURE_IMAGE) { handleResult(it) }
      }

      override fun onPickSingleDocumentSelected() {
          mediaPickerManager.launch(MediaPickerManager.Action.PICK_SINGLE_DOCUMENT) { handleResult(it) }
      }

      override fun onPickMultipleDocumentsSelected() {
          mediaPickerManager.launch(MediaPickerManager.Action.PICK_MULTIPLE_DOCUMENTS) { handleResult(it) }
      }

      override fun onCancelled() {
          Log.d("MainActivity", "Media Picker Modal Cancelled by user")
          resultTextView.text = "Picker cancelled"
      }

      // --- Handle the Result --- 
      private fun handleResult(result: MediaPickerResult) {
          when (result) {
              is MediaPickerResult.SuccessSingle -> {
                  Log.i("MainActivity", "Success (Single): ${result.uri}")
                  resultTextView.text = "Selected Single: ${result.uri}"
                  // TODO: Use the URI (e.g., load image, upload file)
              }
              is MediaPickerResult.SuccessMultiple -> {
                  Log.i("MainActivity", "Success (Multiple): ${result.uris.size} items")
                  resultTextView.text = "Selected Multiple: ${result.uris.joinToString { it.toString() + "\n" }}"
                  // TODO: Use the list of URIs
              }
              is MediaPickerResult.Cancelled -> {
                  Log.i("MainActivity", "Operation Cancelled")
                  resultTextView.text = "Operation Cancelled"
              }
              is MediaPickerResult.Error -> {
                  Log.e("MainActivity", "Error: ${result.message}", result.exception)
                  resultTextView.text = "Error: ${result.message}"
                  // TODO: Show error message to user
              }
          }
      }
  }
  ```

*   **Show the Modal:** Call `MediaPickerModalFragment.show(supportFragmentManager)` when you want to present the options to the user.
*   **Initialize `MediaPickerManager`:** You still need an instance of `MediaPickerManager` in your Activity/Fragment to actually launch the intents when an option is selected in the modal. Pass the Activity or Fragment instance to its constructor.
*   **Handle Results:** Implement the `handleResult` function (or similar) to process the `MediaPickerResult` delivered by the `MediaPickerManager`'s callback.

### 2. Using `MediaPickerManager` Directly

You can bypass the modal UI and trigger specific actions directly using `MediaPickerManager`.

*   **Initialization:** Create an instance of `MediaPickerManager`, passing the `FragmentActivity` or `Fragment` that will host the result launchers.

    ```kotlin
    // In your Activity or Fragment
    private lateinit var mediaPickerManager: MediaPickerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ...
        mediaPickerManager = MediaPickerManager(this) // or 'this' if in Activity
    }
    ```

*   **Launch an Action:** Call the `launch` method with the desired `MediaPickerManager.Action` and a callback lambda to receive the `MediaPickerResult`.

    ```kotlin
    // Example: Launch single image picker directly
    buttonPickSingle.setOnClickListener {
        mediaPickerManager.launch(MediaPickerManager.Action.PICK_SINGLE_IMAGE) { result ->
            // Handle the result (same as handleResult function above)
            when (result) {
                is MediaPickerResult.SuccessSingle -> { /* ... */ }
                is MediaPickerResult.SuccessMultiple -> { /* ... */ } // Not expected for single pick
                is MediaPickerResult.Cancelled -> { /* ... */ }
                is MediaPickerResult.Error -> { /* ... */ }
            }
        }
    }

    // Example: Launch camera directly
    buttonCapture.setOnClickListener {
        mediaPickerManager.launch(MediaPickerManager.Action.CAPTURE_IMAGE) { result ->
            // Handle the result
        }
    }
    ```

## Result Handling (`MediaPickerResult`)

The result of any operation is delivered via the callback as a `MediaPickerResult`, which is a sealed class:

*   `MediaPickerResult.SuccessSingle(uri: Uri)`: Indicates success for single item selection/capture. Contains the content `Uri` of the item.
*   `MediaPickerResult.SuccessMultiple(uris: List<Uri>)`: Indicates success for multiple item selection. Contains a list of content `Uri`s.
*   `MediaPickerResult.Cancelled`: Indicates the user cancelled the operation (e.g., pressed back or cancelled the system picker).
*   `MediaPickerResult.Error(message: String, exception: Throwable?)`: Indicates an error occurred (e.g., permission denied, no camera app, file creation error). Contains an error message and an optional exception.

**Important:** The returned `Uri`s are typically content URIs (`content://...`). You have temporary access granted to these URIs. If you need persistent access, you should copy the file content to your app's internal or external storage immediately after receiving the URI.

## Compatibility

*   **Android Versions:** API Level 24 (Android 7.0) to API Level 35.
*   **Permissions:** Automatically handles permission requests based on the Android version, including granular media permissions (API 33+) and partial access (API 34+).
*   **Pickers:** Uses `ActivityResultContracts.PickVisualMedia` / `PickMultipleVisualMedia` when available (preferred, modern photo picker), falling back gracefully where necessary (though the contracts themselves handle compatibility).
*   **Camera:** Uses `ActivityResultContracts.TakePicture` with `FileProvider` for secure camera output.
*   **Documents:** Uses `ActivityResultContracts.OpenDocument` / `OpenMultipleDocuments`.

## Play Store Policy Compliance

The module is designed with Google Play's permission policies in mind:

*   **Requests permissions contextually:** Permissions (like Camera or Storage) are requested only when the user initiates an action that requires them.
*   **Uses modern APIs:** Leverages `ActivityResultContracts` and the Photo Picker, which are the recommended ways to access media, often reducing the need for broad storage permissions.
*   **Scoped Storage:** Uses `FileProvider` for camera output, aligning with Scoped Storage best practices.
*   **Granular Permissions:** Correctly requests `READ_MEDIA_IMAGES` on API 33+ instead of broad storage permissions.
*   **Partial Access:** Supports `READ_MEDIA_VISUAL_USER_SELECTED` on API 34+ via the Photo Picker contracts.

Remember to clearly explain in your app's privacy policy why you need access to photos, videos, or the camera if you use this module.

