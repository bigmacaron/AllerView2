package kr.kro.fatcats.allerview.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.kro.fatcats.allerview.BuildConfig
import kr.kro.fatcats.allerview.di.NetworkModule
import kr.kro.fatcats.allerview.di.annotations.IoDispatcher
import kr.kro.fatcats.allerview.model.event.Request
import kr.kro.fatcats.allerview.model.local.FragmentSet
import kr.kro.fatcats.allerview.model.local.room.entity.Food
import kr.kro.fatcats.allerview.repository.ProductRepository
import kr.kro.fatcats.allerview.repository.RoomRepository
import kr.kro.fatcats.allerview.util.LogUtil
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productRepository: ProductRepository,
    private val roomRepository : RoomRepository
) : BaseViewModel() {

    /*
    * Properties
    * */
    // 바코드
    private val _barcode = MutableStateFlow("")
    val barcode = _barcode.asStateFlow()

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

    fun moveAllergyCheckFragment() {
        setFragment(FragmentSet.MyAllergyCheckFragment)
    }

    fun setBarcode(barcodeNumber: String) {
        _barcode.value = barcodeNumber
    }

    fun setRequestInfoEvent(request: Request) {
        viewModelScope.launch {
            _requestEvent.emit(request)
        }
    }

    /**  ------------------------Network--------------------------------- */
    // 바코드로 제품의 정보를 조회한다.
    suspend fun getBarcodeLinkedProductInfo(request: Request.BarcodeLiked) {
        val url = BuildConfig.BASE_URL+NetworkModule.C005+BAR_CODE+"=${request.barcode}"
        val barcodeInfo = productRepository.getBarcodeLinkedProductInfoAsync(url).await()
        barcodeInfo.C005.row?.let {
            LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getBarcodeLinkedProductInfo : $it")
            setRequestInfoEvent(Request.FoodRawLiked(it[0].PRDLST_REPORT_NO))
        }
    }

    // 음식의 상세 정보를 조회한다.
    suspend fun getFoodLikedRawInfo(request: Request.FoodRawLiked) {
        val url = BuildConfig.BASE_URL+NetworkModule.C002+"${if (request.isFoodName) PRDLST_NM else PRDLST_REPORT_NO}=${request.foodParameter}"
        val foodCodeInfo = productRepository.getFoodItemRawMaterialInfoAsync(url).await()
        foodCodeInfo.C002.row?.let {
            LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getFoodNameLikedRawInfo : $it")
        }
    }

    /**  ------------------------Room--------------------------------- */
    //바코드로 룸 정보 조회
    suspend fun importToBarcode(barcode : Long) : Food?{
        return roomRepository.importToBarcode(barcode)
    }
    //식품명과, 회사명으로 룸 정보 조회
    suspend fun findByNameAndCompany(name: String, company: String) : Food?{
        return roomRepository.findByNameAndCompany(name,company)
    }
    //룸에 정보 입력
    fun insertFood(food : Food) {
        viewModelScope.launch(ioDispatcher) {
            roomRepository.insertFood(food)
        }
    }

    /**  --------------------companion object---------------------- */
    companion object {
        const val BAR_CODE = "BAR_CD"
        const val PRDLST_NM = "PRDLST_NM"
        const val PRDLST_REPORT_NO = "PRDLST_REPORT_NO"
    }

}