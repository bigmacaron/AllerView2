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
import kr.kro.fatcats.allerview.databinding.FragmentBarcodeResultBinding
import kr.kro.fatcats.allerview.databinding.FragmentCameraBinding
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}