package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentCameraBinding
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.viewmodel.MainViewModel


class CameraFragment : BaseFragment<FragmentCameraBinding,MainViewModel>() {

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentCameraBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_camera, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentCameraBinding) {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}