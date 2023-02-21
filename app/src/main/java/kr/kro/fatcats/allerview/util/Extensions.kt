package kr.kro.fatcats.allerview.util

import kr.kro.fatcats.allerview.model.event.Request

/**
 * [this] Search Type 을 구분한다.
 * @return Request Event Type 반환
* */

fun String.checkSearchType() : Request {
    if(this.isBlank()) return Request.NoneLiked // "" 또는 공백으로 들어 왔을때
    val barcode = this.toLongOrNull()  // 숫자인지 체크
    if(this.length < 12 && barcode != null) return Request.BarcodeLiked(this) // 바코드 최소 길이 충족 안될때
    return Request.FoodRawLiked(this)
}
