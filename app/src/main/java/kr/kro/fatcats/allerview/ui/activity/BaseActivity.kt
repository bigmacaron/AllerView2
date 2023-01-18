package kr.kro.fatcats.allerview.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.util.LogUtil.DEBUG_LEVEL_2
import javax.annotation.OverridingMethodsMustInvokeSuper

open class BaseActivity : AppCompatActivity() {

    /*
    * Lifecycles
    * */
    @OverridingMethodsMustInvokeSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onStart() {
        super.onStart()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onResume() {
        super.onResume()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onPause() {
        super.onPause()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onStop() {
        super.onStop()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }
}