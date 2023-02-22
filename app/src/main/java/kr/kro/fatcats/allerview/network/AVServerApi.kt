package kr.kro.fatcats.allerview.network

import kotlinx.coroutines.Deferred
import kr.kro.fatcats.allerview.model.barcodeInfo.BarcodeInfo
import kr.kro.fatcats.allerview.model.foodInfo.FoodInfo
import retrofit2.http.GET
import retrofit2.http.Url

interface AVServerApi {

    // Todo 오현석
    //  1. Response MSG JSONObject 를 생성해서 한번 담고
    //  2. CODE 를 파싱해서 INFO, ERROR 를 구분한다.
    //  3. 구분한 값으로 분기처리가 가능하도록 만들어야할 것 같아보임.

    // 바코드로 상품의 정보를 조회한다.
    @GET
    fun getBarcodeLinkedProductInfoAsync(
        @Url url: String?
    ) : Deferred<BarcodeInfo>

    // 품목 번호 or 제품 이름으로 음식의 상세 정보를 조회한다.
    @GET
    fun getFoodItemRawMaterialInfoAsync(
        @Url url: String?
    ) : Deferred<FoodInfo>

}