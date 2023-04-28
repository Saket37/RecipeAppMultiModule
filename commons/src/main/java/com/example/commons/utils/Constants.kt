package com.example.commons.utils

import com.example.commons.BuildConfig

object Constants {
    const val TASTY_API_BASE_URL = "https://tasty.p.rapidapi.com"
    const val PAGE_SIZE = 20
    const val RAPID_API_KEY_HEADER ="X-RapidAPI-Key"
    const val RAPID_API_KEY = BuildConfig.RapidApiKey
    const val RAPID_HOST_KEY_HEADER="X-RapidAPI-Host"
    const val RAPID_HOST_KEY = BuildConfig.RapidApiHost
}