package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentMyAllergyCheckBinding
import kr.kro.fatcats.allerview.model.local.food.FoodData
import kr.kro.fatcats.allerview.ui.adapter.MyAllergyCheckRVAdapter
import kr.kro.fatcats.allerview.viewmodel.MainViewModel


class MyAllergyCheckFragment : BaseFragment<FragmentMyAllergyCheckBinding,MainViewModel>() {

    private val myAllergyCheckRVAdapter = MyAllergyCheckRVAdapter<FoodData>()

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentMyAllergyCheckBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_my_allergy_check, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentMyAllergyCheckBinding) {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()
    }

    private fun setRecyclerViewAdapter(){

        viewBinding?.recyclerview?.apply {
            adapter = myAllergyCheckRVAdapter
        }

        val foodArray = context?.resources?.getStringArray(R.array.string_food_list)
        val foodDataList = arrayListOf<FoodData>()
        foodArray?.map {
            foodDataList.add(FoodData(it))
        }
        myAllergyCheckRVAdapter.setItems(foodDataList)

    }

}