package kr.kro.fatcats.allerview.model.local.room

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
    fun loadByBarcode(barcode: Long): Food

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): Food

    @Insert
    fun insertAll(vararg foods: Food)

    @Delete
    fun delete(user: Food)
}