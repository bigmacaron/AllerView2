package kr.kro.fatcats.allerview.model.barcodeInfo

import kr.kro.fatcats.allerview.model.Result

data class C005(
    val RESULT: Result,
    val row: List<Row>?,
    val total_count: String
)