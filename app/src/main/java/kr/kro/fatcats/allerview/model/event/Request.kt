package kr.kro.fatcats.allerview.model.event

import androidx.annotation.StringRes

sealed class Request {

    // 바코드로 api 요청
    class BarcodeLiked(val barcode: String) : Request()
    /**
     * 음식 상세 정보 api 요청
     * @param [foodParameter] 음식의 이름 or 품번
     * @param [isFoodName] From 음식의 이름 입력 유, 무
     **/
    class FoodRawLiked(val foodParameter: String, val isFoodName: Boolean = false): Request()
    object NoneLiked: Request()
    class Toast(@StringRes val resId: Int?, val stringArgs: String): Request()

}
