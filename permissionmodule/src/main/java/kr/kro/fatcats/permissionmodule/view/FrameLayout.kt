package kr.kro.fatcats.permissionmodule.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.FrameLayout

class FrameLayout(context: Context) : FrameLayout(context) {


    private val dialog = Dialog(context, { leftButtonCallback() }, { rightButtonCallback() })

    init {
        dialog.show()
        cancelListener()
    }

    // dialog cancel Listener
    private fun cancelListener(){
        dialog.setOnCancelListener {
            (context as Activity).finish()
        }
    }

    // dialog default LeftButton Callback
    private fun leftButtonCallback() {
        // dialog 를 닫고 activity 를 종료
        dialog.dismiss()
        (context as Activity).finish()
    }

    // dialog default RightButton Callback
    private fun rightButtonCallback() {
        // 권한 획득 할 수 있는 세팅 화면으로 이동
        val intent =
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + context.packageName))
        val activityContext = (context as PermissionActivity)
        activityContext.requestSettingDetailLaunch.launch(intent)
        dialog.dismiss()
    }

}





