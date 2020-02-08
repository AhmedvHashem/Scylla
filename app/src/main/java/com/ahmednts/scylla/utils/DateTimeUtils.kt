package com.ahmednts.scylla.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by AhmedNTS on 2016-01-13.
 */

object DateTimeUtils {

  fun getUTCFromLocal(localISODate: String): String? {
    try {
      val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      dateFormat.timeZone = TimeZone.getDefault()
      val localDate = dateFormat.parse(localISODate)
      dateFormat.timeZone = TimeZone.getTimeZone("UTC")
      return dateFormat.format(localDate)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return null
  }

  fun getLocalFromUTC(utcISODate: String): String? {
    try {
      val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      dateFormat.timeZone = TimeZone.getTimeZone("UTC")
      val utcDate = dateFormat.parse(utcISODate)
      dateFormat.timeZone = TimeZone.getDefault()
      return dateFormat.format(utcDate)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return null
  }
}