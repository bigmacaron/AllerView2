package kr.kro.fatcats.allerview.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

internal inline fun <reified T> String.fromString(): T {
    val type = object : TypeToken<T>(){}.type
    return Gson().fromJson(this, type)
}

internal inline fun <reified T> JSONObject.fromJson(): T {
    return this.toString().fromString()
}
