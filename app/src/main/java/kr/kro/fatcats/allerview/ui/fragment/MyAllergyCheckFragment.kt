package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentMyAllergyCheckBinding
import kr.kro.fatcats.allerview.ui.activity.MainActivity
import kr.kro.fatcats.allerview.ui.adapter.MyAllergyCheckRVAdapter
import kr.kro.fatcats.allerview.viewmodel.MainViewModel


class MyAllergyCheckFragment : BaseFragment<FragmentMyAllergyCheckBinding,MainViewModel>() {

    private var recyclerViewAdapter : MyAllergyCheckRVAdapter? = null

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentMyAllergyCheckBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_my_allergy_check, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentMyAllergyCheckBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.activity = requireActivity() as MainActivity
        viewBinding.fragment = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()
        setCoordinator()
    }

    fun clickConfirm(){
        lifecycleScope.launch(Dispatchers.IO){
            viewModel.resetCheckedData()
            recyclerViewAdapter?.getItem()?.forEach {
                viewModel.updateMyFood(it)
            }
            withContext(Dispatchers.Main){
                activity?.onBackPressed()
            }
        }
    }

    private fun setCoordinator() {
        viewBinding?.collapsingToolbarLayout?.apply {
            setExpandedTitleTextAppearance(R.style.expandedTitle)
            setCollapsedTitleTextAppearance(R.style.collapsingTitle)
        }
        viewBinding?.collapsingAppBarLayout?.addOnOffsetChangedListener { appBar, verticalOffset ->
            appBar.clipChildren = Math.abs(verticalOffset) > 83
            if(Math.abs(verticalOffset) in 10..84){
                viewBinding?.layoutBox?.background?.alpha = 840/Math.abs(verticalOffset)+30
            }else{
                viewBinding?.layoutBox?.background?.alpha = 100
            }
            if(appBar.clipChildren){
                viewBinding?.layoutBox?.visibility = View.GONE
            }else{
                viewBinding?.layoutBox?.visibility = View.VISIBLE
            }
        }
    }

    private fun setRecyclerViewAdapter(){
        lifecycleScope.launch {
            recyclerViewAdapter =  MyAllergyCheckRVAdapter(
                withContext(Dispatchers.IO){
                    viewModel.getMyFood()
                }
            )
            viewBinding?.recyclerview?.apply {
                adapter = recyclerViewAdapter
                layoutManager = GridLayoutManager(requireContext(), 3)
            }
        }
    }

}