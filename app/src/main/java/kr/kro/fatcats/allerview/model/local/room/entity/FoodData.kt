package kr.kro.fatcats.allerview.model.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodData(
    @PrimaryKey val barcode: Long,
    @ColumnInfo(name = "PRDLST_REPORT_NO") val reportNo: String?, //PRDLST_REPORT_NO 품목제조번호
    @ColumnInfo(name = "PRMS_DT") val reportDate: String?, //PRMS_DT 보고일자
    @ColumnInfo(name = "LCNS_NO") val licenseNo: String?, //LCNS_NO 인허가번호
    @ColumnInfo(name = "PRDLST_NM") val name: String?, //PRDLST_NM 품목명
    @ColumnInfo(name = "BSSH_NM") val company: String?, //BSSH_NM 업소명
    @ColumnInfo(name = "PRDLST_DCNM") val category: String?, // PRDLST_DCNM 유형
    @ColumnInfo(name = "RAWMTRL_NM") val materials: String?, // RAWMTRL_NM 원재료명
)
