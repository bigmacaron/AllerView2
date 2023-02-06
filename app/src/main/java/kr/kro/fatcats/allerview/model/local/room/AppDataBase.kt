package kr.kro.fatcats.allerview.model.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.kro.fatcats.allerview.model.local.room.dao.FoodDao
import kr.kro.fatcats.allerview.model.local.room.entity.Food

@Database(entities = [Food::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun foodDao() : FoodDao

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