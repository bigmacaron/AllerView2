package kr.kro.fatcats.allerview.repository

import kotlinx.coroutines.*
import kr.kro.fatcats.allerview.di.annotations.IoDispatcher
import kr.kro.fatcats.allerview.model.local.room.AppDataBase
import kr.kro.fatcats.allerview.model.local.room.entity.Food
import javax.inject.Inject

class RoomRepository @Inject constructor(
    db : AppDataBase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val dao = db.foodDao()

    suspend fun importToBarcode(barcode : Long) : Food? = withContext(ioDispatcher) {
        dao.loadByBarcode(barcode)
    }

    suspend fun findByNameAndCompany(name: String, company: String) : Food? = withContext(ioDispatcher) {
        dao.findByNameAndCompany(name,company)
    }

    suspend fun insertFood(food : Food) = withContext(ioDispatcher) {
        dao.insertAll(food)
    }

}