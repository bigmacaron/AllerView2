package kr.kro.fatcats.allerview.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.ui.fragment.CameraFragment
import kr.kro.fatcats.allerview.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private val nav : NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragmentLabel = nav.currentDestination?.label
        if(fragmentLabel == CameraFragment().javaClass.simpleName){
            finish()
        }else{
            super.onBackPressed()
        }
    }
}