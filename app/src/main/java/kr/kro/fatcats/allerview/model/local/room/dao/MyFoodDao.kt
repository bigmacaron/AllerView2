package kr.kro.fatcats.allerview.model.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kr.kro.fatcats.allerview.model.local.room.entity.MyFoodData

@Dao
interface MyFoodDao {

    @Query("SELECT * FROM myfooddata")
    fun getAll(): List<MyFoodData>

    @Query("SELECT * FROM MyFoodData WHERE englishName IN (:englishName)")
    fun getEnglishNameData(englishName: String): MyFoodData?

    @Query("SELECT * FROM MyFoodData WHERE englishName IN (:checked)")
    fun findCheckData(checked : Boolean): List<MyFoodData?>

    @Query("UPDATE myfooddata SET IS_CHECKED = 'false' WHERE IS_CHECKED is 'true' ")
    fun resetCheckedData()

    @Update
    fun updateMyFood(food: MyFoodData)

    @Insert
    fun insertMyFood(vararg foods: MyFoodData)

    @Delete
    fun delete(food: MyFoodData)
}