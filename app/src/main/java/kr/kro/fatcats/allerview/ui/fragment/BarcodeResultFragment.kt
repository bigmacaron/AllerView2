package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentBarcodeResultBinding
import kr.kro.fatcats.allerview.ui.activity.MainActivity
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.viewmodel.MainViewModel


class BarcodeResultFragment : BaseFragment<FragmentBarcodeResultBinding,MainViewModel>() {

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentBarcodeResultBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_barcode_result, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentBarcodeResultBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.activity  = requireActivity() as MainActivity // Todo onBackPressed 이벤트를 어떻게 처리할지 추후에 수정..
        checkAVCategoryPosition(viewBinding)
        viewBinding.run {
            if (categoryFruit.parent is ConstraintLayout) {
                val tempParent = categoryFruit.parent as ConstraintLayout
                LogUtil.d(LogUtil.DEBUG_LEVEL_2,"initData children ${tempParent.children}")
                LogUtil.d(LogUtil.DEBUG_LEVEL_2,"initData count ${tempParent.childCount}")
                tempParent.forEachIndexed { index, view ->
                    LogUtil.d(LogUtil.DEBUG_LEVEL_2,"\ninitData index: $index, view:$view")
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun checkAVCategoryPosition(viewBinding: FragmentBarcodeResultBinding?) {
        viewBinding?.let {

        }
    }

}