package com.example.presentation.deeplink

import android.content.Context
import android.content.Intent
import com.example.commons.utils.DeeplinkProcessor
import com.example.presentation.RecipeListActivity
import javax.inject.Inject

class HomeDeeplinkProcessor @Inject constructor(
    private val context: Context
) : DeeplinkProcessor {
    override fun matches(deeplink: String): Boolean {
        return deeplink.contains("/home")
    }

    override fun execute(deeplink: String) {
        val intent = Intent(context, RecipeListActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}