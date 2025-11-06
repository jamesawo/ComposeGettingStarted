package com.james.composegettingstarted.model

object Fixtures {
    object Greeting {
        val name = "James"
        val saluate = "Hello"
        val longDescription =
            "Building beautiful and responsive user interfaces with Jetpack Compose."
    }

    object Image {
        val contentDescription = "Contact profile picture"
    }
}

data class Message(val author: String, val body: String)