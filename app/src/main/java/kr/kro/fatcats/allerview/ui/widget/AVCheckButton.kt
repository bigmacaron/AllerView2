package kr.kro.fatcats.allerview.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatCheckBox
import kr.kro.fatcats.allerview.R

class AVCheckButton : AppCompatCheckBox {

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setCheckImage()
    }
    private fun setCheckImage(){
        setOnCheckedChangeListener { buttonView, isChecked ->
            Log.e("TAG", "setCheckImage: $isChecked ", )
            if(isChecked){
                buttonView.setButtonDrawable(R.drawable.ic_check_box_checked)
            }else{
                buttonView.setButtonDrawable(R.drawable.ic_check_box_default)
            }
            invalidate()
        }
    }

}

