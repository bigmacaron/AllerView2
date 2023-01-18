package kr.kro.fatcats.allerview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kr.kro.fatcats.allerview.util.LogUtil
import kr.kro.fatcats.allerview.util.LogUtil.DEBUG_LEVEL_2
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class BaseFragment<VBinding: ViewDataBinding, VModel: ViewModel> : Fragment() {


    abstract fun viewModel(): VModel

    abstract fun inflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): VBinding

    abstract fun initData(viewBinding: VBinding)

    protected var viewBinding: VBinding? = null
    protected val viewModel: VModel by lazy {
        viewModel()
    }

    /*
     * Lifecycles
     * */
    @OverridingMethodsMustInvokeSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            initData(this)
        }
        return viewBinding?.root
    }

    @OverridingMethodsMustInvokeSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    override fun onDestroyView() {
        super.onDestroyView()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

    @OverridingMethodsMustInvokeSuper
    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(DEBUG_LEVEL_2, javaClass.simpleName)
    }

}