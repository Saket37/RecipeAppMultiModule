package com.example.commons.utils

interface DeeplinkHandler {
    fun process(deeplink: String) :Boolean
}