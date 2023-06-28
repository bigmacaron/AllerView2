package kr.kro.fatcats.allerview.model.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kr.kro.fatcats.allerview.model.local.room.entity.FoodData

@Dao
interface FoodListDao {

    @Query("SELECT * FROM FoodData")
    fun getAll(): List<FoodData>

    @Query("SELECT * FROM FoodData WHERE barcode IN (:barcode)")
    fun importToBarcode(barcode: Long): FoodData?

    @Query("SELECT * FROM FoodData WHERE PRDLST_NM LIKE '%' || :name || '%' AND " +
            "BSSH_NM LIKE '%' || :company || '%' LIMIT 20")
    fun findByNameAndCompany(name: String, company: String): FoodData?

    @Insert
    fun insertAll(vararg foods: FoodData)

    @Delete
    fun delete(food: FoodData)
}