package kr.kro.fatcats.permissionmodule.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import kr.kro.fatcats.permissionmodule.R

class Dialog(
    context: Context,
    private val leftButtonCallback: () -> Unit,
    private val rightButtonCallback: () -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_base)

        val cancel = findViewById<Button>(R.id.dialogButtonCancel)
        val moveSetting = findViewById<Button>(R.id.dialogButtonSetting)

        window?.setBackgroundDrawableResource(R.color.white)

        // leftButton Click Event
        cancel.setOnClickListener {
            leftButtonCallback.invoke()
        }

        // rightButton Click Event
        moveSetting.setOnClickListener {
            rightButtonCallback.invoke()
        }
    }

}






