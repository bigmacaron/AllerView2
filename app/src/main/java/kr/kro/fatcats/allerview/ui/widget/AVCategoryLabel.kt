package kr.kro.fatcats.allerview.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kr.kro.fatcats.allerview.R

class AVCategoryLabel : ConstraintLayout {

    private lateinit var backgroundView: ConstraintLayout
    private lateinit var imageView: ImageView

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
        val v = li.inflate(R.layout.alerview_allergy_category, this, false)
        addView(v)
        imageView = findViewById(R.id.ivCategory)
        backgroundView = findViewById(R.id.backgroundLayout)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AVCategoryLabel)
        setAttrs(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AVCategoryLabel, defStyle, 0)
        setAttrs(typedArray)
    }

    private fun setAttrs(typedArray: TypedArray) {
        val backgroundResId = typedArray.getResourceId(R.styleable.AVCategoryLabel_categoryBackgroundTint, R.color.dark_primary_85)
        val iconResId = typedArray.getResourceId(R.styleable.AVCategoryLabel_categoryIcon, R.drawable.ic_launcher_foreground)
        val drawable: GradientDrawable? =
            ContextCompat.getDrawable(context, R.drawable.background_oval) as GradientDrawable?
        drawable?.let {
            it.color = ContextCompat.getColorStateList(context, backgroundResId)
        }
        backgroundView.background = drawable
        imageView.setImageResource(iconResId)
        typedArray.recycle()
    }

}