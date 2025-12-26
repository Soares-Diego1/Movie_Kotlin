package org.example.projectt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform