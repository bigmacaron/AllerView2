package kr.kro.fatcats.allerview.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.kro.fatcats.allerview.model.event.Request
import kr.kro.fatcats.allerview.model.local.FragmentSet
import kr.kro.fatcats.allerview.repository.ProductRepository
import kr.kro.fatcats.allerview.util.LogUtil
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    /*
    * Properties
    * */
    // 바코드
    private val _barcode = MutableStateFlow("")
    val barcode = _barcode.asStateFlow()

    // 품목 번호
    private val _itemCode = MutableStateFlow(0)
    val itemCode = _itemCode.asStateFlow()

    private val _requestEvent = MutableSharedFlow<Request>()
    val requestEvent = _requestEvent.asSharedFlow()

    /*
    * Methods
    * */
    fun moveMainFragment() {
        setFragment(FragmentSet.MainFragment)
    }

    fun moveBarcodeResultFragment() {
        setFragment(FragmentSet.BarcodeResultFragment)
    }

    fun setBarcode(barcodeNumber: String) {
        _barcode.value = barcodeNumber
    }

    fun setRequestInfoEvent(request: Request) {
        viewModelScope.launch {
            _requestEvent.emit(request)
        }
    }

    suspend fun getBarcodeLinkedProductInfo(url: String) {
        LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getBarcodeLinkedProductInfo url: $url")
        val barcodeInfo = productRepository.getBarcodeLinkedProductInfoAsync(url).await()
        LogUtil.e(LogUtil.DEBUG_LEVEL_2,"getBarcodeLinkedProductInfo barcodeInfo -> $barcodeInfo")
//        barcodeInfo.C005.row?.let {
//            LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getBarcodeLinkedProductInfo : $it")
//        }
    }

    suspend fun getFoodItemRawMaterialInfo(url: String) {
        LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getFoodItemRawMaterialInfo url: $url")
        val foodCodeInfo = productRepository.getFoodItemRawMaterialInfoAsync(url).await()
        foodCodeInfo.C002.row?.let {
            LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getFoodItemRawMaterialInfo : $it")
        }
    }

}