package com.ahmednts.scylla.base

import android.util.Log
import com.ahmednts.scylla.base.network.ApiException
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Response

abstract class BaseRemoteRepository {
  private val gson = GsonBuilder().create()

  @Throws(ApiException::class)
  fun handleFailure(response: Response<*>) {
    if (response.isSuccessful) return

    if (response.code() == 404) throw ApiException("RemoteError", "Not found!")
    if (response.code() >= 500) throw ApiException("RemoteError", "Something went wrong!")

    val jsonObject = gson.fromJson(response.errorBody()?.string(), JsonObject::class.java)
    jsonObject?.let {
      val errorMessage = it.get("msg")?.toString()
      Log.e("BaseRemoteRepository", "handleFailure: $errorMessage")
      throw ApiException("RemoteError", errorMessage)
    }
  }
}