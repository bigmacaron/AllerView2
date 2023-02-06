package kr.kro.fatcats.allerview.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withContext
import kr.kro.fatcats.allerview.di.annotations.IoDispatcher
import kr.kro.fatcats.allerview.model.barcodeInfo.BarcodeInfo
import kr.kro.fatcats.allerview.model.foodInfo.FoodInfo
import kr.kro.fatcats.allerview.network.AVServerApi
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: AVServerApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
){

    suspend fun getBarcodeLinkedProductInfoAsync(url: String): Deferred<BarcodeInfo> = withContext(ioDispatcher) {
            api.getBarcodeLinkedProductInfoAsync(url)
    }

    suspend fun getFoodItemRawMaterialInfoAsync(url: String): Deferred<FoodInfo> = withContext(ioDispatcher) {
        api.getFoodItemRawMaterialInfoAsync(url)
    }

}