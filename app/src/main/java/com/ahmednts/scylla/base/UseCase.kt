package com.ahmednts.scylla.base

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase internal constructor(
  protected val workerThread: Scheduler, protected val uiThread: Scheduler
) {
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  fun addObserver(observer: Disposable) {
    compositeDisposable.add(observer)
  }

  fun dispose() {
    compositeDisposable.clear()
  }
}