package kr.kro.fatcats.allerview.network

import retrofit2.http.GET
import retrofit2.http.Path

interface AVServerApi {

    // 바코드로 상품의 정보를 조회한다.
    // http://openapi.foodsafetykorea.go.kr/api/701987a6886f4ae4b0ea/C005/json/1/1/BAR_CD="8801791947312"
    @GET("/{dataType}")
    fun getFoodInfo(
        @Path("dataType") dataType: String?
    )
    
}