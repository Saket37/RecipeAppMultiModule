package com.example.commons.deeplink

import com.example.commons.utils.DeeplinkHandler
import com.example.commons.utils.DeeplinkProcessor

class DefaultDeeplinkHandler constructor(
    private val processors: Set<@JvmSuppressWildcards DeeplinkProcessor>) :
    DeeplinkHandler {
    override fun process(deeplink: String): Boolean {
        processors.forEach {
            if (it.matches(deeplink)) {
                it.execute(deeplink)
                return true
            }
        }
        return false
    }
}