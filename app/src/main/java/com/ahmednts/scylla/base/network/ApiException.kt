package com.ahmednts.scylla.base.network

/**
 * Created by shar2wy
 * on 10/29/18.
 * Description: description goes here
 */
data class ApiException(
  val errorCode: String?,
  val errorMessage: String?
) : Exception()
