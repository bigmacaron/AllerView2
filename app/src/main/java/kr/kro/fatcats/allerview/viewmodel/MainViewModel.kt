package kr.kro.fatcats.allerview.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.kro.fatcats.allerview.model.local.FragmentSet
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel(){

    fun moveMainFragment(){
        setFragment(FragmentSet.MainFragment)
    }

}