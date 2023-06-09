package com.example.commons.utils

interface DeeplinkProcessor {

    fun matches(deeplink: String): Boolean

    fun execute(deeplink: String)

    companion object {
        const val EXTRA_KEY = "deeplink_processor_extra"
    }
}