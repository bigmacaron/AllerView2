package kr.kro.fatcats.allerview.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.kro.fatcats.allerview.model.local.FragmentSet
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel(){

    private val _barcode = MutableStateFlow("")
    val barcode = _barcode.asStateFlow()

    fun moveMainFragment(){
        setFragment(FragmentSet.MainFragment)
    }
    fun moveBarcodeResultFragment(){
        setFragment(FragmentSet.BarcodeResultFragment)
    }
    fun setBarcode(barcodeNumber : String){
        _barcode.value = barcodeNumber
    }


}