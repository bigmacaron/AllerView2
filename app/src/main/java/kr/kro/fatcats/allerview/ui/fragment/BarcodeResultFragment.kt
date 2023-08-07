package kr.kro.fatcats.allerview.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentBarcodeResultBinding
import kr.kro.fatcats.allerview.ui.activity.MainActivity
import kr.kro.fatcats.allerview.ui.listener.AppBarStateChangeListener
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.viewmodel.MainViewModel


class BarcodeResultFragment : BaseFragment<FragmentBarcodeResultBinding,MainViewModel>() {

    private val appBarStateChangeListener: AppBarStateChangeListener =
        object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                LogUtil.d(LogUtil.DEBUG_LEVEL_2,"onStateChanged state: $state")
                when (state) {
                    State.COLLAPSED -> {
                        viewBinding?.tvBarcode?.run {
                            visibility = View.VISIBLE
                        }
                    }
                    else -> {
                        viewBinding?.tvBarcode?.run {
                            visibility = View.GONE
                        }
                    }
                }
            }
        }

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
        viewBinding.appBar.addOnOffsetChangedListener(appBarStateChangeListener)
        subscriptionFoodInformation()
    }

    private fun subscriptionFoodInformation() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.foodInformation.collectLatest { foodInfo ->
                    LogUtil.d(LogUtil.DEBUG_LEVEL_2,"foodInformation collectLatest $foodInfo")
                }
            }
        }
    }

}