package kr.kro.fatcats.allerview.network.exception

import okio.IOException
import org.json.JSONObject

class AServerException(
    val body: String?,
    val code: String = body?.let {
        JSONObject(it).getString("code")
    } ?: "00000000",
    val errorCode: String = code.let {
        code.removeRange(4 .. 6)
    }
) : IOException("response error: $code") {
}