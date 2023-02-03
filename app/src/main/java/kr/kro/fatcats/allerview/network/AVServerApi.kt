package kr.kro.fatcats.allerview.network

import kotlinx.coroutines.Deferred
import kr.kro.fatcats.allerview.domain.model.barcodeInfo.BarcodeInfo
import kr.kro.fatcats.allerview.domain.model.foodInfo.FoodInfo
import retrofit2.http.GET
import retrofit2.http.Url

interface AVServerApi {

    // 바코드로 상품의 정보를 조회한다.
    // http://openapi.foodsafetykorea.go.kr/api/701987a6886f4ae4b0ea/C005/json/1/1/BAR_CD="8801791947312"
    @GET
    suspend fun getBarcodeInfo(
        @Url url: String?
    ) : Deferred<BarcodeInfo>

    // 품목 번호로 음식의 상세 정보를 조회한다.
    // http://openapi.foodsafetykorea.go.kr/api/701987a6886f4ae4b0ea/C002/json/1/1/PRDLST_REPORT_NO = 19550509001209
    @GET
    suspend fun getFoodInfo(
        @Url url: String?
    ) : Deferred<FoodInfo>

}