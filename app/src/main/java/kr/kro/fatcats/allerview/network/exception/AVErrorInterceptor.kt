package kr.kro.fatcats.allerview.network.exception

import okhttp3.Interceptor
import okhttp3.Response


/**************************************************************************************************
 * Title : 서버 에러 인터셉터
 * Description :
 * 서버 요청 - 응답 성공 시 읍답 전달,
 * 실패 시 [AVServerException] 발생
 *
 * @author startwo09@gmail.com
 * @since 2023/01/25 1:20 PM
 **************************************************************************************************/
class AVErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().let { request ->
            val response = chain.proceed(request)
            if (response.isSuccessful) {
                return response
            }
            throw  AVServerException(response.body?.string())
        }
    }
}