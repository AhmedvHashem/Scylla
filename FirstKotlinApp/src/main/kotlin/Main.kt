import kotlin.properties.Delegates

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

class User {
    var name: String by
            Delegates.observable("<no name>") { prop, old, new -> println("$old -> $new") }
}

fun main(args: Array<String>) {
    lazyValue
    println("Hello World! Kotlin")

    val myName = "Hashem"
    mutableListOf<String>().forEach {
        val i = it.length
        println(i)
    }
}
