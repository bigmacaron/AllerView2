package kr.kro.fatcats.allerview.model.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyFoodData(
    @PrimaryKey val englishName: String,
    @ColumnInfo(name = "KOREAN_NAME") val koreanName: String,
    @ColumnInfo(name = "IS_CHECKED")val isChecked : Boolean,
    @ColumnInfo(name = "WORDS") val words : String
)
