package kr.kro.fatcats.allerview.utils

import android.content.res.AssetManager

fun AssetManager.readJson(fileName: String): String {
    return open("json/$fileName").bufferedReader().readText()
}