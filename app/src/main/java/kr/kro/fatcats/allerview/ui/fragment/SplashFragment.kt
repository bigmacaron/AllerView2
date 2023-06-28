package kr.kro.fatcats.allerview.ui.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.databinding.FragmentSplashBinding
import kr.kro.fatcats.allerview.model.local.room.entity.MyFoodData
import kr.kro.fatcats.allerview.viewmodel.MainViewModel
import kr.kro.fatcats.permissionmodule.PermissionListener
import kr.kro.fatcats.permissionmodule.builder.Permission

class SplashFragment : BaseFragment<FragmentSplashBinding,MainViewModel>() {

    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            lifecycleScope.launch {
                delay(1000)
                findNavController().popBackStack()
                viewModel.moveMainFragment()
            }
        }
        override fun onPermissionDenied(deniedPermissions: List<String>) {
            requireActivity().finish()
        }
    }

    override fun viewModel(): MainViewModel {
        val lazy: Lazy<MainViewModel> = viewModels(ownerProducer = { requireActivity() })
        return lazy.value
    }

    override fun inflate(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): FragmentSplashBinding {
        return DataBindingUtil.inflate(layoutInflater, R.layout.fragment_splash, container, attachToRoot)
    }

    override fun initData(viewBinding: FragmentSplashBinding) {
        viewBinding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                checkPermission()
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            setFoodList()
            if(viewModel.getMyFood().isEmpty()){
                setMyFood()
            }
        }
    }
    private fun checkPermission(){
        Permission.create()
            .setPermissionListener(permissionListener)
            .setPermissions(Manifest.permission.CAMERA)
            .useDialog(true)
            .check()
    }
    private fun setFoodList(){
        viewModel.foodKoreanNames = requireContext().resources.getStringArray(R.array.string_food_list)
        viewModel.foodENGNames = requireContext().resources.getStringArray(R.array.string_food_list_eng)
    }
    private fun setMyFood(){
        val foodKoreanNames = viewModel.foodKoreanNames
        val foodENGNames = viewModel.foodENGNames
        foodENGNames.forEachIndexed { index, englishName ->
            viewModel.insertMyFood(
                MyFoodData(
                    englishName,
                    foodKoreanNames[index],
                    false,
                    createWord(englishName)
                )
            )
        }
    }

    private fun createWord(engName : String) : String{
        val resourceId = requireContext().resources.getIdentifier(engName, "array", requireContext().packageName)
        val words = requireContext().resources?.getStringArray(resourceId)
        var temp = ""
        words?.forEachIndexed { index, s ->
            temp += if( words.lastIndex != index){
                "$s@@"
            }else{
                s
            }
        }
       return temp
    }
}