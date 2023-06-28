package kr.kro.fatcats.allerview.model.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.kro.fatcats.allerview.model.local.room.dao.FoodListDao
import kr.kro.fatcats.allerview.model.local.room.dao.MyFoodDao
import kr.kro.fatcats.allerview.model.local.room.entity.FoodData
import kr.kro.fatcats.allerview.model.local.room.entity.MyFoodData

@Database(entities = [FoodData::class,MyFoodData::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun foodListDao() : FoodListDao
    abstract fun myFoodListDao() : MyFoodDao

//    companion object {
//        private var INSTANCE: AppDataBase? = null
//
//        @JvmStatic
//        fun getInstance(context: Context): AppDataBase? {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    if (INSTANCE == null) {
//                        INSTANCE = Room.databaseBuilder(context.applicationContext,
//                            AppDataBase::class.java, "app_database")
//                            .build()
//                    }
//                }
//            }
//            return INSTANCE
//        }
//    }
}