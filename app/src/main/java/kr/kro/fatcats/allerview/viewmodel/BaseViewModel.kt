package kr.kro.fatcats.allerview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.kro.fatcats.allerview.model.local.FragmentSet
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.util.LogUtil.DEBUG_LEVEL_3
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel(){

    protected fun setFragment(fragmentSet: FragmentSet) {
        viewModelScope.launch {
            LogUtil.d(DEBUG_LEVEL_3, "set fragment: ${fragmentSet.javaClass.simpleName}")
            _fragmentSetFlow.emit(fragmentSet)
        }
    }

    private val _fragmentSetFlow = MutableSharedFlow<FragmentSet>()
    val fragmentSetFlow = _fragmentSetFlow.asSharedFlow()
}