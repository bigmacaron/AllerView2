package kr.kro.fatcats.allerview.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class AVDefaultButton : AppCompatButton {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context : Context, attrs : AttributeSet){

    }
}