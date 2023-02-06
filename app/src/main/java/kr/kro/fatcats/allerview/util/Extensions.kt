package kr.kro.fatcats.allerview.util

import kr.kro.fatcats.allerview.model.local.SearchType

fun String.checkSearchType() : SearchType {
    if(this.isBlank()) return SearchType.NONE_TYPE // "" 또는 공백으로 들어 왔을때
    if(this.length < 12) return SearchType.STRING_TYPE // 바코드 최소 길이 충족 안될때
    val barcode = this.toLongOrNull()  // 숫자인지 체크
    println(barcode)
    return if(barcode == null){
         SearchType.STRING_TYPE
    }else{
        SearchType.BARCODE_TYPE
    }
}