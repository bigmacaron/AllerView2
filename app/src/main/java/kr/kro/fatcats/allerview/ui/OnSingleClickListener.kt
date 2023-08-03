package kr.kro.fatcats.allerview.ui

import android.os.SystemClock
import android.view.View

class OnSingleClickListener(
    private val onClickListener: (view: View) -> Unit,
) : View.OnClickListener {

    companion object {
        // 버튼 사이에 허용 시간 간격
        private const val INTERVAL = 200L
    }

    // 이전 클릭 시간 기록
    private var lastClickedTime = 0L

    override fun onClick(v: View) {

        // 클릭 시간
        val onClickedTime = SystemClock.elapsedRealtime()

        // 지정한 시간의 간격 보다 작으면 반환
        if ((onClickedTime-lastClickedTime) < INTERVAL) { return }

        lastClickedTime = onClickedTime
        onClickListener.invoke(v)
    }
}