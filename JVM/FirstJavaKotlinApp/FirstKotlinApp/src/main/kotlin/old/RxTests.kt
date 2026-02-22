package old

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class DefaultRxErrorConsumer : Consumer<Throwable> {
    override fun accept(t: Throwable) {
        println(t)
        Thread.currentThread()
            .apply {
                uncaughtExceptionHandler.uncaughtException(this, t)
            }
    }

    override fun toString(): String {
        return "EmptyConsumer"
    }
}

fun main(args: Array<String>) {

//    RxJavaPlugins.setErrorHandler(old.DefaultRxErrorConsumer())
    RxJavaPlugins.setErrorHandler { t ->
        var throwable = t
        if (throwable is UndeliverableException) {
            throwable.cause?.let { throwable = it }
        }

        println("RxJavaErrorHandler " + throwable.message.toString())
        Thread.currentThread()
            .apply { uncaughtExceptionHandler.uncaughtException(this, throwable) }
    }


    val single = Single<Int>.create {
        throw java.lang.NullPointerException()

        val shouldThrow = true
        if (shouldThrow) {
            try {
//                it.onSuccess(1)
                throw NullPointerException()
            } catch (e: Exception) {
                it.onError(e)
            }
        } else {
            it.onSuccess(1)
        }
    }

    val singleValue = single
        .doOnSubscribe {
            println("doOnSubscribe")
        }
        .doOnSuccess {
            println("doOnSuccess")
        }
        .doOnError {
            println("doOnError")
        }
        .map { 9 }

        .blockingGet()

    println(singleValue)
}