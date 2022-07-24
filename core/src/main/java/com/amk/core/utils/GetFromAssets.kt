package com.amk.core.utils

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import java.io.IOException
import java.io.InputStream

fun getDrawableFromAssets(secId: String, context: Context): Drawable {
    val assetManager: AssetManager = context.assets
    val stream = try {
        assetManager.open("logos/$secId.png")
    } catch (e: IOException) {
        e.printStackTrace()
        assetManager.open("logos/nologo.png")
    }
    val drawable = Drawable.createFromStream(stream, null)
    stream.close()
    return drawable
}

fun getInfoFromAssets(secId: String, context: Context): String {
    val assetManager: AssetManager = context.assets
    lateinit var stream: InputStream
    return try {
        stream = assetManager.open("infos/$secId.txt")
        val size = stream.available()
        val buffer = ByteArray(size)
        stream.read(buffer)
        stream.close()
        String(buffer)
    } catch (e: IOException) {
        e.printStackTrace()
        "No info file"
    }
}