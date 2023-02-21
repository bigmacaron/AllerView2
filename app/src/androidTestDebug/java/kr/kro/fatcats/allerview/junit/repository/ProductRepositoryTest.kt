package kr.kro.fatcats.allerview.junit.repository

import android.content.res.AssetManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kr.kro.fatcats.allerview.BuildConfig
import kr.kro.fatcats.allerview.di.NetworkModule
import kr.kro.fatcats.allerview.model.barcodeInfo.BarcodeInfo
import kr.kro.fatcats.allerview.model.foodInfo.FoodInfo
import kr.kro.fatcats.allerview.repository.ProductRepository
import kr.kro.fatcats.allerview.utils.fromString
import kr.kro.fatcats.allerview.utils.readJson
import kr.kro.fatcats.allerview.viewmodel.MainViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ProductRepositoryTest {

    private val assets: AssetManager =
        InstrumentationRegistry.getInstrumentation().context.assets

    @Inject
    lateinit var productRepository: ProductRepository
    private lateinit var testScope: TestScope
    private val testDispatcher = UnconfinedTestDispatcher()
    private val productBarcodeNumber = 8801791947312  // 매일맛있는신태양고추장 바코드
    private val foodName = "매일맛있는신태양초고추장"        // 매일맛있는신태양초고추장 이름
    private val foodNumber = 1978061400972

    private val productBarcodeUrl = BuildConfig.BASE_URL+NetworkModule.C005+MainViewModel.BAR_CODE+"=$productBarcodeNumber"
    private val foodNameLikedRawInfoUrl = BuildConfig.BASE_URL+NetworkModule.C002+"${MainViewModel.PRDLST_NM}=${foodName}"
    private val foodNoLikedRawInfoUrl = BuildConfig.BASE_URL+NetworkModule.C002+"${MainViewModel.PRDLST_REPORT_NO}=${foodNumber}"

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        testScope = TestScope()
    }

    @Test
    fun `getBarcodeLinkedProductInfo`() = runTest(testDispatcher) {
        val expected = assets.readJson("productInfomation.json").fromString<BarcodeInfo>()
        val result = productRepository.getBarcodeLinkedProductInfoAsync(productBarcodeUrl).await()
        assertEquals(expected, result)
    }

    @Test
    fun `getFoodNameLinkedInfo`() = runTest(testDispatcher) {
        val expected = assets.readJson("foodInfomationByName.json").fromString<FoodInfo>()
        val result = productRepository.getFoodItemRawMaterialInfoAsync(foodNameLikedRawInfoUrl).await()
        assertEquals(expected, result)
    }

    @After
    fun tearDown() {
        testScope.cancel()
        testDispatcher.cancel()
    }

}