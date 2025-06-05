# Android Media Picker Module

A comprehensive Android module for handling media picking operations, including images and documents. This module provides a clean and easy-to-use API for common media operations while handling all the complexity of permissions, file providers, and compatibility across Android versions.

## Features

- Pick a single image from the gallery
- Pick multiple images from the gallery
- Capture an image from the camera
- Pick a single document
- Pick multiple documents
- Support for Android API levels 24 to 35
- Compliant with Google Play Store photo and video permission policies
- Modern permission handling with ActivityResultLauncher
- Support for both Activities and Fragments

## Requirements

- Android API level 24+ (Android 7.0 Nougat and above)
- AndroidX libraries

## Installation

### 1. Add the module to your project

Clone this repository and add the module to your project:

```groovy
// In your app's settings.gradle or settings.gradle.kts
include ':app', ':MediaPickerModule'
```

### 2. Add the dependency to your app's build.gradle

```groovy
dependencies {
    implementation project(':MediaPickerModule')
}
```

## Usage

### Initialize MediaPicker

In your Activity:

```kotlin
private lateinit var mediaPicker: MediaPicker

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    mediaPicker = MediaPicker.with(this)
        .setOnImagePickedListener(object : MediaPicker.OnImagePickedListener {
            override fun onImagePicked(uri: Uri) {
                // Handle the picked image URI
                imageView.setImageURI(uri)
            }
        })
        .setOnPermissionDeniedListener(object : MediaPicker.OnPermissionDeniedListener {
            override fun onPermissionDenied() {
                Toast.makeText(this@MainActivity, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        })
}
```

In your Fragment:

```kotlin
private lateinit var mediaPicker: MediaPicker

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    mediaPicker = MediaPicker.with(this)
        .setOnImagePickedListener(object : MediaPicker.OnImagePickedListener {
            override fun onImagePicked(uri: Uri) {
                // Handle the picked image URI
                imageView.setImageURI(uri)
            }
        })
}
```

### Pick a Single Image from Gallery

```kotlin
mediaPicker.pickImage()
```

### Pick Multiple Images from Gallery

```kotlin
mediaPicker
    .setOnMultipleImagesPickedListener(object : MediaPicker.OnMultipleImagesPickedListener {
        override fun onMultipleImagesPicked(uris: List<Uri>) {
            // Handle the picked image URIs
            Toast.makeText(context, "Picked ${uris.size} images", Toast.LENGTH_SHORT).show()
        }
    })
    .pickMultipleImages()
```

### Capture Image from Camera

```kotlin
mediaPicker.captureImage()
```

### Pick a Single Document

```kotlin
mediaPicker
    .setOnDocumentPickedListener(object : MediaPicker.OnDocumentPickedListener {
        override fun onDocumentPicked(uri: Uri) {
            // Handle the picked document URI
            processDocument(uri)
        }
    })
    .pickDocument()
```

### Pick Multiple Documents

```kotlin
mediaPicker
    .setOnMultipleDocumentsPickedListener(object : MediaPicker.OnMultipleDocumentsPickedListener {
        override fun onMultipleDocumentsPicked(uris: List<Uri>) {
            // Handle the picked document URIs
            Toast.makeText(context, "Picked ${uris.size} documents", Toast.LENGTH_SHORT).show()
        }
    })
    .pickMultipleDocuments()
```

## Permissions

The module handles all necessary permissions automatically:

- `READ_EXTERNAL_STORAGE` (for API level 32 and below)
- `READ_MEDIA_IMAGES` (for API level 33+)
- `READ_MEDIA_VIDEO` (for API level 33+)
- `CAMERA` (for capturing images)

The module will request permissions at runtime when needed and provide callbacks through the `OnPermissionDeniedListener` when permissions are denied.

## FileProvider Configuration

The module includes a FileProvider configuration for handling camera captures. The FileProvider is declared in the module's AndroidManifest.xml and uses the application ID as the authority.

## Google Play Store Compliance

This module is designed to comply with Google Play Store's photo and video permission policies:

- Uses the appropriate permissions based on the Android API level
- Requests only the permissions needed for the specific operation
- Uses scoped storage on Android 10+ (API level 29+)
- Properly handles permission requests and denials

## Complete Example

See the `MediaPickerUsageExample.kt` file in the module for a complete example of how to use all features of the MediaPicker module.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
