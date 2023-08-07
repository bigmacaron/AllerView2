package kr.kro.fatcats.allerview.ui.listener

import com.google.android.material.appbar.AppBarLayout
import kr.kro.fatcats.allerview.util.LogUtil
import kotlin.math.abs

abstract class AppBarStateChangeListener: AppBarLayout.OnOffsetChangedListener {
    enum class State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private var currentState = State.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        LogUtil.d(LogUtil.DEBUG_LEVEL_2,"onOffsetChanged verticalOffset : $verticalOffset appbarTotalScrollRange : ${appBarLayout.totalScrollRange}")
        appBarLayout.let {
            when {
                verticalOffset == 0 -> {
                    if (currentState != State.EXPANDED) {
                        onStateChanged(appBarLayout, State.EXPANDED)
                        currentState = State.EXPANDED
                    }
                }
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    if (currentState != State.COLLAPSED) {
                        onStateChanged(appBarLayout, State.COLLAPSED)
                        currentState = State.COLLAPSED
                    }
                }
                else -> {
                    if (currentState != State.IDLE) {
                        onStateChanged(appBarLayout, State.IDLE)
                        currentState = State.IDLE
                    }
                }
            }
        }
    }

    /**
     * Notifies on state change
     * @param appBarLayout Layout
     * @param state Collapse state
     */
    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}