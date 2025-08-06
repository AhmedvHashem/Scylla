package com.hashem.mediapicker

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity

/**
 * This is a sample Activity demonstrating how to use the MediaPicker module.
 * This class is for reference only and is not part of the module itself.
 */
class MediaPickerUsageExample : ComponentActivity() {
    
    private lateinit var mediaPicker: MediaPicker
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize MediaPicker with the Activity
        mediaPicker = MediaPicker.with(this)
            .setOnImagePickedListener(object : MediaPicker.OnImagePickedListener {
                override fun onImagePicked(uri: Uri) {
                    // Handle the picked image URI
                    Toast.makeText(this@MediaPickerUsageExample, 
                        "Image picked: $uri", Toast.LENGTH_SHORT).show()
                    
                    // Example: Load the image into an ImageView
                    // imageView.setImageURI(uri)
                }
            })
            .setOnMultipleImagesPickedListener(object : MediaPicker.OnMultipleImagesPickedListener {
                override fun onMultipleImagesPicked(uris: List<Uri>) {
                    // Handle the picked image URIs
                    Toast.makeText(this@MediaPickerUsageExample, 
                        "Picked ${uris.size} images", Toast.LENGTH_SHORT).show()
                    
                    // Example: Process each URI
                    // uris.forEach { uri -> processImage(uri) }
                }
            })
            .setOnDocumentPickedListener(object : MediaPicker.OnDocumentPickedListener {
                override fun onDocumentPicked(uri: Uri) {
                    // Handle the picked document URI
                    Toast.makeText(this@MediaPickerUsageExample, 
                        "Document picked: $uri", Toast.LENGTH_SHORT).show()
                    
                    // Example: Process the document
                    // processDocument(uri)
                }
            })
            .setOnMultipleDocumentsPickedListener(object : MediaPicker.OnMultipleDocumentsPickedListener {
                override fun onMultipleDocumentsPicked(uris: List<Uri>) {
                    // Handle the picked document URIs
                    Toast.makeText(this@MediaPickerUsageExample, 
                        "Picked ${uris.size} documents", Toast.LENGTH_SHORT).show()
                    
                    // Example: Process each document
                    // uris.forEach { uri -> processDocument(uri) }
                }
            })
            .setOnPermissionDeniedListener(object : MediaPicker.OnPermissionDeniedListener {
                override fun onPermissionDenied() {
                    // Handle permission denied
                    Toast.makeText(this@MediaPickerUsageExample, 
                        "Permission denied", Toast.LENGTH_SHORT).show()
                }
            })
        
        // Example button click listeners to trigger different picker actions
        
        // Pick a single image from gallery
//        findViewById<Button>(R.id.btnPickImage).setOnClickListener {
//            mediaPicker.pickImage()
//        }
//
//        // Pick multiple images from gallery
//        findViewById<Button>(R.id.btnPickMultipleImages).setOnClickListener {
//            mediaPicker.pickMultipleImages()
//        }
//
//        // Capture image from camera
//        findViewById<Button>(R.id.btnCaptureImage).setOnClickListener {
//            mediaPicker.captureImage()
//        }
//
//        // Pick a single document
//        findViewById<Button>(R.id.btnPickDocument).setOnClickListener {
//            mediaPicker.pickDocument()
//        }
//
//        // Pick multiple documents
//        findViewById<Button>(R.id.btnPickMultipleDocuments).setOnClickListener {
//            mediaPicker.pickMultipleDocuments()
//        }
    }
}

/**
 * Example of using MediaPicker in a Fragment:
 *
 * class MyFragment : Fragment() {
 *     private lateinit var mediaPicker: MediaPicker
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *
 *         // Initialize MediaPicker with the Fragment
 *         mediaPicker = MediaPicker.with(this)
 *             .setOnImagePickedListener(object : MediaPicker.OnImagePickedListener {
 *                 override fun onImagePicked(uri: Uri) {
 *                     // Handle the picked image URI
 *                 }
 *             })
 *             // Set other listeners as needed
 *
 *         // Example button click listener
 *         view.findViewById<Button>(R.id.btnPickImage).setOnClickListener {
 *             mediaPicker.pickImage()
 *         }
 *     }
 * }
 */
