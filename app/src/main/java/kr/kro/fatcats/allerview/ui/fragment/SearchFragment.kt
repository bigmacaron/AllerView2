package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import dagger.hilt.android.AndroidEntryPoint
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentSearchBinding
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.viewmodel.MainViewModel
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding,MainViewModel>() {

    private var capture : CaptureManager? = null

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentSearchBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentSearchBinding) {
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