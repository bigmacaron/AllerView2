package kr.kro.fatcats.allerview.junit.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kr.kro.fatcats.allerview.model.local.room.AppDataBase
import kr.kro.fatcats.allerview.model.local.room.dao.FoodListDao
import kr.kro.fatcats.allerview.model.local.room.dao.MyFoodDao
import kr.kro.fatcats.allerview.model.local.room.entity.FoodData


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RoomRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var db : AppDataBase
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var foodListDao : FoodListDao
    private lateinit var myFoodDao: MyFoodDao
    private lateinit var beInsertFood : FoodData
    private lateinit var initFood : FoodData

    private val mocBarcode = 123456780L
    private val companyName = "테스트제조사"
    private val name = "테스트제품명"


    @Before
    fun setUp() {
        hiltRule.inject()
        foodListDao = db.foodListDao()
        myFoodDao = db.myFoodListDao()
        initFood = FoodData(
            barcode = 123456789L,reportNo= "77777777",reportDate="230221",
            licenseNo="88888888",name=name,company=companyName,category="테스트제품",materials="테스트재료"
        )
        beInsertFood = FoodData(
            barcode = mocBarcode,reportNo= "22222222",reportDate="230221",
            licenseNo="33333333",name="삽입제품명",company="삽입회사",category="삽입제품",materials="삽입재료"
        )
        foodListDao.insertAll(initFood)
    }

    @Test
    fun `findByNameAndCompanyTest`() = runTest(testDispatcher) {
        val result = foodListDao.findByNameAndCompany(name,companyName)
        assertEquals(result ,initFood)
    }

    @Test
    fun `basicValueSearchByBarcodeTest`() = runTest(testDispatcher) {
        val result = foodListDao.importToBarcode(123456789L)
        assertEquals(result ,initFood)
    }

    @Test
    fun `insertAndGetInsertedDataTest`() = runTest(testDispatcher){
        foodListDao.insertAll(beInsertFood)
        val result = foodListDao.importToBarcode(mocBarcode)
        assertEquals(result ,beInsertFood)
    }

    @After
    fun tearDown() {
        foodListDao.delete(beInsertFood)
        foodListDao.delete(initFood)
        db.close()
    }

}