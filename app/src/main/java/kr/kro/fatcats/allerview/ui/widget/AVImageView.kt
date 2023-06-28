package kr.kro.fatcats.allerview.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import kr.kro.fatcats.allerview.R

class AVImageView : AppCompatImageView {

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init(context, attrs)
    }
//
//    private fun init(context : Context, attrs : AttributeSet?){
//        if (!isInEditMode) {
//            if (null != attrs) {
//                val a = context.obtainStyledAttributes(attrs, R.styleable.AVImageView)
//                setImageByGlide(context, a)
//                a.recycle()
//            }
//        }
//    }
//
//    private fun setImageByGlide(context: Context, a: TypedArray) {
//        if (a.hasValue(R.styleable.AVImageView_setImageByGlide)) {
//            val value = a.getString(R.styleable.AVImageView_setImageByGlide)
//            val resourceId = context.resources.getIdentifier(value, "mipmap", context.packageName)
//            Glide.with(context).load(resourceId).into(this)
//        }
//    }

}