package com.example.details_presentation.deeplink

import android.content.Context
import android.content.Intent
import com.example.commons.utils.DeeplinkProcessor
import com.example.details_presentation.RecipeDetailsActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailsDeeplinkProcessor @Inject constructor(private val context: Context) :
    DeeplinkProcessor {
    override fun matches(deeplink: String): Boolean {
        return deeplink.contains("/details")
    }

    override fun execute(deeplink: String) {
        val extraData = deeplink.split("/details/").getOrNull(1)
        //deeplink.removePrefix("recipe://multi.module.app/details/").split('/').getOrNull(0)
       // val id = extraData?.let { RecipeId(it) }
        val intent = Intent(context, RecipeDetailsActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(DeeplinkProcessor.EXTRA_KEY, extraData)
        }
        context.startActivity(intent)
    }
}
/*@Parcelize
data class RecipeId(
    val id:String
): Parcelable*/
