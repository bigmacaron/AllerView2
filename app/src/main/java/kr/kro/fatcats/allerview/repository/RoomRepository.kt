package kr.kro.fatcats.allerview.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.kro.fatcats.allerview.di.annotations.IoDispatcher
import kr.kro.fatcats.allerview.model.local.room.AppDataBase
import kr.kro.fatcats.allerview.model.local.room.entity.Food
import javax.inject.Inject

class RoomRepository @Inject constructor(
    db : AppDataBase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val externalScope: CoroutineScope,
) {

    private val dao = db.foodDao()

//    suspend fun importToBadrcode(barcode : Long) : Food?{
//        var data : Food? = null
//        externalScope.launch(ioDispatcher) {
//            data = dao.loadByBarcode(barcode)
//        }
//        return data
//    }

    suspend fun importToBarcode(barcode : Long) = withContext(ioDispatcher) {
        dao.loadByBarcode(barcode)
    }

}