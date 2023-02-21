package kr.kro.fatcats.allerview.model.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kr.kro.fatcats.allerview.model.local.room.entity.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getAll(): List<Food>

    @Query("SELECT * FROM food WHERE barcode IN (:barcode)")
    fun importToBarcode(barcode: Long): Food?

    @Query("SELECT * FROM food WHERE PRDLST_NM LIKE '%' || :name || '%' AND " +
            "BSSH_NM LIKE '%' || :company || '%' LIMIT 20")
    fun findByNameAndCompany(name: String, company: String): Food?

    @Insert
    fun insertAll(vararg foods: Food)

    @Delete
    fun delete(food: Food)
}