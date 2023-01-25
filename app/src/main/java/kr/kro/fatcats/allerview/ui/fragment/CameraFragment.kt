package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.journeyapps.barcodescanner.*
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentCameraBinding
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.viewmodel.MainViewModel


class CameraFragment : BaseFragment<FragmentCameraBinding,MainViewModel>() {
    
    private var capture : CaptureManager? = null

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

        val barcodeScannerView = viewBinding?.barcodeView as DecoratedBarcodeView
        capture = CaptureManager(requireActivity(),barcodeScannerView)
        val intent = requireActivity().intent
        intent.putExtra("RESULT_DISPLAY_DURATION_MS", 500L)
        capture?.initializeFromIntent(intent, savedInstanceState)
        capture?.decode()
        LogUtil.d(LogUtil.DEBUG_LEVEL_2,"onViewCreated:$barcodeScannerView")
        barcodeScannerView.cameraSettings.requestedCameraId = 0
        barcodeScannerView.cameraSettings.isAutoFocusEnabled = true

        barcodeScannerView.decodeContinuous { result ->
            LogUtil.i(LogUtil.DEBUG_LEVEL_2,"barcode: $result")
            val resultString = result.text
            viewModel.setBarcode(resultString)
            viewModel.moveBarcodeResultFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        capture?.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture?.onDestroy()
        capture = null
    }

}