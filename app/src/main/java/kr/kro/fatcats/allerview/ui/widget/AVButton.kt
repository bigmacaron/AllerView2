package kr.kro.fatcats.allerview.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kr.kro.fatcats.allerview.R

class AVButton : ConstraintLayout {

    private lateinit var backgroundView: ConstraintLayout
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
        getAttrs(attrs, defStyleAttr)
    }

    private fun init() {
        val infService = Context.LAYOUT_INFLATER_SERVICE
        val li = context.getSystemService(infService) as LayoutInflater
        val v = li.inflate(R.layout.alerview_button, this, false)
        addView(v)

        backgroundView = findViewById(R.id.backgroundLayout)
        imageView = findViewById(R.id.ivIcon)
        textView = findViewById(R.id.tvTitle)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AVButton)
        setAttrs(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AVButton, defStyle, 0)
        setAttrs(typedArray)
    }

    private fun setAttrs(typedArray: TypedArray) {
        val backgroundResId = typedArray.getResourceId(R.styleable.AVButton_btnBackgroundTint, R.color.primary_85)
        val symbolResId = typedArray.getResourceId(R.styleable.AVButton_btnSymbol, R.drawable.ic_launcher_foreground)
        val textColorResId = typedArray.getColor(R.styleable.AVButton_btnTxtColor, ContextCompat.getColor(context, R.color.text_color))
        val textResId = typedArray.getString(R.styleable.AVButton_btnTxt)

//        backgroundView.backgroundTintList = ContextCompat.getColorStateList(context, backgroundResId)
        imageView.setImageResource(symbolResId)
        textView.setTextColor(textColorResId)
        textView.text = textResId

        typedArray.recycle()
    }

}