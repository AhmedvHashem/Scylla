package com.hashem.firstandroidapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hashem.firstandroidapp.R
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

const val TAG = "MainComposeActivity"

class MainViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        System.setProperty("java.net.preferIPv4Stack", "true")

//        val single: Single<Int> = Single.create {
////            it.onSuccess(1)
////            throw UnknownHostException()
//
//            try {
////            testCallback("asd", object : TokenResponseCallback {
////                override fun onTokenRequestCompleted(ex: Exception?) {
////                    if (ex != null) {
////                        it.onError(ex)
////                    }
////                }
////            })
//
//                GlobalScope.launch {
//                    val response = performNetworkRequest()
//                    if (response != null) {
//                        // Process the response
//                        Log.d(TAG, "Response: $response")
//                        it.onSuccess(1)
//                    } else {
//                        // Handle the error
//                        Log.e(TAG, "Network request failed.")
//                        it.onError(Exception("Network request failed."))
//                    }
//                }
//            } catch (e: Throwable) {
//                it.onError(e)
//            }
//        }
//
//        val singleValue = single
//            .subscribeOn(Schedulers.io())
////            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe {
//                Log.e(TAG, "doOnSubscribe")
//            }
//            .doOnSuccess {
//                Log.e(TAG, "doOnSuccess")
//            }
//            .doOnError {
//                Log.e(TAG, "doOnError $it")
//            }
//            .onErrorReturn {
//                100
//            }.blockingGet()
//
//        Log.e(TAG, "$singleValue")
    }
}

suspend fun performNetworkRequest(): String? = withContext(Dispatchers.IO) {
    var connection: HttpURLConnection? = null
    var reader: BufferedReader? = null
    try {
        val url = URL("https://auth.buildinglink.com") // Replace with the exact URL if needed

        connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET" // Or "POST" if needed
        connection.connectTimeout = 5000 // 5 seconds
        connection.readTimeout = 5000 // 5 seconds

        // You might need to add headers here (e.g., Authorization, Content-Type)
        // connection.setRequestProperty("Authorization", "Bearer YOUR_TOKEN")

        val responseCode = connection.responseCode
        Log.d(TAG, "Response Code: $responseCode")

        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = BufferedReader(InputStreamReader(connection.inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }
            return@withContext stringBuilder.toString()
        } else {
            Log.e(TAG, "HTTP Error: $responseCode ${connection.responseMessage}")
            return@withContext null
        }
    } catch (e: IOException) {
        Log.e(TAG, "Network request failed: ${e.message}")
        return@withContext null
    } finally {
        reader?.close()
        connection?.disconnect()
    }
}