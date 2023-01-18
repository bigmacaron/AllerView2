package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentMainBinding
import kr.kro.fatcats.allerview.viewmodel.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding,MainViewModel>() {

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentMainBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_main, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentMainBinding) {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}