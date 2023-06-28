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
import kr.kro.fatcats.allerview.model.foodInfo.Row
import kr.kro.fatcats.allerview.model.local.FragmentSet
import kr.kro.fatcats.allerview.model.local.room.entity.FoodData
import kr.kro.fatcats.allerview.model.local.room.entity.MyFoodData
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

    var foodKoreanNames : Array<String> = arrayOf("")
    var foodENGNames : Array<String> = arrayOf("")

    /*
    * Properties
    * */
    // 바코드
    private val _barcode = MutableStateFlow("")
    val barcode = _barcode.asStateFlow()

    private val _requestEvent = MutableSharedFlow<Request>()
    val requestEvent = _requestEvent.asSharedFlow()

    // 알러지 안전 or 경고 라벨
    private val _isWaringLabel = MutableStateFlow<Boolean?>(null)
    val isWaringLabel = _isWaringLabel.asStateFlow()

    private val _foodInformation = MutableStateFlow<Row?>(null)
    val foodInformation = _foodInformation.asStateFlow()

    private val _myFoodData = MutableStateFlow<List<MyFoodData>?>(null)
    val myFoodData = _myFoodData.asStateFlow()
    /*
    * Methods
    * */
    fun moveMainFragment() {
        setFragment(FragmentSet.MainFragment)
    }

    private fun moveBarcodeResultFragment() {
        setFragment(FragmentSet.BarcodeResultFragment)
    }

    fun moveAllergyCheckFragment() {
        setFragment(FragmentSet.MyAllergyCheckFragment)
    }

    fun setBarcode(barcodeNumber: String) {
        _barcode.value = barcodeNumber
    }

    fun setRequestEvent(request: Request) {
        viewModelScope.launch {
            _requestEvent.emit(request)
        }
    }

    fun setAllergyLabel(isWarning: Boolean) {
        viewModelScope.launch {
            _isWaringLabel.emit(isWarning)
        }
    }

    /**  ------------------------Network--------------------------------- */
    // 바코드로 제품의 정보를 조회한다.
    suspend fun getBarcodeLinkedProductInfo(request: Request.BarcodeLiked) {
        val url = BuildConfig.BASE_URL+NetworkModule.C005+BAR_CODE+"=${request.barcode}"
        val barcodeInfo = productRepository.getBarcodeLinkedProductInfoAsync(url).await()
        barcodeInfo.C005.row?.let {
            LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getBarcodeLinkedProductInfo : $it")
            setRequestEvent(Request.FoodRawLiked(it[0].PRDLST_REPORT_NO)) // Todo 여러개일 경우의 처리도 필요
        } ?: run {
            // TODO 단순히 메시지만 보여주는게 아니라 INFO, ERROR 대한 분기 처리 고민해보고 어떤식으로 노출해줄지 추후 작성
            setRequestEvent(Request.Toast(resId = null, barcodeInfo.C005.RESULT.MSG))
        }
    }

    // 음식의 상세 정보를 조회한다.
    suspend fun getFoodLikedRawInfo(request: Request.FoodRawLiked) {
        LogUtil.d(LogUtil.DEBUG_LEVEL_2,"getFoodLikedRawInfo")
        val url = BuildConfig.BASE_URL+NetworkModule.C002+"${if (request.isFoodName) PRDLST_NM else PRDLST_REPORT_NO}=${request.foodParameter}"
        val foodCodeInfo = productRepository.getFoodItemRawMaterialInfoAsync(url).await()
        foodCodeInfo.C002.row?.let {
            LogUtil.d(LogUtil.DEBUG_LEVEL_2,"Request Event : $it")
            setFoodInformation(it[0])
            moveBarcodeResultFragment()
        } ?: run {
            setRequestEvent(Request.Toast(resId = null, foodCodeInfo.C002.RESULT.MSG))
        }
    }

    /**  ------------------------Result--------------------------------- */
    private suspend fun setFoodInformation(result: Row) {
        _foodInformation.emit(result)
    }

    /**  ------------------------Room--------------------------------- */
    /*음식 정보 */
    //바코드로 룸 정보 조회
    suspend fun importToBarcode(barcode : Long) : FoodData?{
        return roomRepository.importToBarcode(barcode)
    }
    //식품명과, 회사명으로 룸 정보 조회
    suspend fun findByNameAndCompany(name: String, company: String) : FoodData?{
        return roomRepository.findByNameAndCompany(name,company)
    }
    //룸에 정보 입력
    fun insertFood(food : FoodData) {
        viewModelScope.launch(ioDispatcher) {
            roomRepository.insertFood(food)
        }
    }
    /* *********************** myFood**************/

    fun resetCheckedData(){
        roomRepository.resetCheckedData()
    }
    suspend fun getMyFood() : ArrayList<MyFoodData>{
        return roomRepository.getMyFood()
    }
    fun insertMyFood(myFoodData : MyFoodData) {
        roomRepository.insertMyFood(myFoodData)
    }
    fun updateMyFood(myFoodData : MyFoodData) {
        roomRepository.updateMyFood(myFoodData)
    }

    /**  --------------------companion object---------------------- */
    companion object {
        const val BAR_CODE = "BAR_CD"                   // 바코드
        const val PRDLST_NM = "PRDLST_NM"               // 제품명
        const val PRDLST_REPORT_NO = "PRDLST_REPORT_NO" // 품목 제조 번호
    }

}