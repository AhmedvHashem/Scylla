package com.ahmednts.scylla

import com.ahmednts.scylla.utils.AppLogger
import io.reactivex.Flowable
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {

  @Before
  fun setup() {
    AppLogger.setupForTests()
    RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
  }

  @Test
  fun additionIsCorrect() {
    Assert.assertEquals(4, 2 + 2.toLong())
  }

  @Test
  fun when_observer_throw_error_code_should_not_crash() {
    Flowable.fromCallable {
      throw Exception()
    }.doOnError {
      AppLogger.e(it)
    }.subscribe()
  }
}