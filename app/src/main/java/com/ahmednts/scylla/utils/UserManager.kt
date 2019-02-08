package com.ahmednts.scylla.utils

import android.content.Context

object UserManager {
  private const val USER_MANAGER = "USER_MANAGER"

  private const val AUTHORIZATION_TOKEN = "AUTHORIZATION_TOKEN"

  fun setAuthorizationToken(token: String, context: Context): Boolean {
    val editor = context.getSharedPreferences(USER_MANAGER, Context.MODE_PRIVATE).edit()
    editor.putString(AUTHORIZATION_TOKEN, "Bearer $token")
    return editor.commit()
  }

  fun getAuthorizationToken(context: Context): String? {
    val savedSession = context.getSharedPreferences(USER_MANAGER, Context.MODE_PRIVATE)
    return savedSession.getString(AUTHORIZATION_TOKEN, null)
  }

  fun clear(context: Context) {
    val editor = context.getSharedPreferences(USER_MANAGER, Context.MODE_PRIVATE).edit()
    editor.clear()
    editor.apply()
  }
}