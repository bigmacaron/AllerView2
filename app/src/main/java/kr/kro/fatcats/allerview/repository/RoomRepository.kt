package kr.kro.fatcats.allerview.repository

import kotlinx.coroutines.*
import kr.kro.fatcats.allerview.di.annotations.IoDispatcher
import kr.kro.fatcats.allerview.model.local.room.AppDataBase
import kr.kro.fatcats.allerview.model.local.room.entity.FoodData
import kr.kro.fatcats.allerview.model.local.room.entity.MyFoodData
import javax.inject.Inject

class RoomRepository @Inject constructor(
    db : AppDataBase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val foodDao = db.foodListDao()
    private val myFoodListDao = db.myFoodListDao()


    /**  foodDao ***/
    suspend fun importToBarcode(barcode : Long) : FoodData? = withContext(ioDispatcher) {
        foodDao.importToBarcode(barcode)
    }

    suspend fun findByNameAndCompany(name: String, company: String) : FoodData? = withContext(ioDispatcher) {
        foodDao.findByNameAndCompany(name,company)
    }

    suspend fun insertFood(food : FoodData) = withContext(ioDispatcher) {
        foodDao.insertAll(food)
    }
    suspend fun deleteFood(food : FoodData) = withContext(ioDispatcher){
        foodDao.delete(food)
    }

    /**  myFoodListDao ***/

    suspend fun getMyFood() : ArrayList<MyFoodData>{
        return myFoodListDao.getAll() as ArrayList<MyFoodData>
    }
    fun resetCheckedData(){
        myFoodListDao.resetCheckedData()
    }
    suspend fun myFoodFindCheckData() : List<MyFoodData?>{
        return myFoodListDao.findCheckData(true)
    }
    fun insertMyFood(myFoodData : MyFoodData) {
        myFoodListDao.insertMyFood(myFoodData)
    }
    fun updateMyFood(myFoodData : MyFoodData) {
        myFoodListDao.updateMyFood(myFoodData)
    }
    suspend fun deleteMyFood(){

    }

}