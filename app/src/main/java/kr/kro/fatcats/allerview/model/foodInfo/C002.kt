package kr.kro.fatcats.allerview.model.foodInfo

import kr.kro.fatcats.allerview.model.Result

data class C002(
    val RESULT: Result,
    val row: List<Row>?,
    val total_count: String
)