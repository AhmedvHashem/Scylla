package com.hashem.firstandroidapp.compose

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hashem.firstandroidapp.compose.theme.FirstAndroidAppTheme

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val mediaPickerManager = MediaPickerManager(this)
//        mediaPickerManager.launch(MediaPickerManager.Action.PICK_SINGLE_IMAGE) {
//            Log.e("mediaPickerManager", "onResult: $it")
//        }

//        Log.e("TAG", "filesDir: $filesDir")
//        Log.e("TAG", "cacheDir: $cacheDir")
//        Log.e("TAG", "externalFilesDir: ${getExternalFilesDir(null)}")
//        Log.e("TAG", "externalCacheDir: $externalCacheDir")
//        Log.e("TAG", "externalMediaDirs: ${externalMediaDirs.map {
//            it.absolutePath
//        }}")
//        Log.e("TAG", "Environment.getExternalStorageDirectory: ${
//            Environment.getExternalStorageDirectory().absolutePath
//        }}")
//
//        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
//        val storageDir = getExternalFilesDir(null)
//        val newFile =  if (storageDir != null) {
//            File.createTempFile(
//                "JPEG_" + timeStamp + "_",  /* prefix */
//                ".jpg",  /* suffix */
//                storageDir /* directory */
//            )
//        } else {
//            null
//        }
//
//        Log.e("TAG", "newFile: $newFile")

        setContent {
            FirstAndroidAppTheme {
                MainUI()
            }
        }
    }
}

@Composable
fun MainUI() {


    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            Log.e("TAG", "selectedImageUri: $selectedImageUri")
        })

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hello Android!"
            )
            Button(
                onClick = {


//                    singlePhotoPickerLauncher.launch(
//                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
////                        "*/*"
//                    )
                }
            ) {
                Text(
                    text = "Pick"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainUIPreview() {
    FirstAndroidAppTheme {
        MainUI()
    }
}
