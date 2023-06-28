package kr.kro.fatcats.allerview.ui

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.kro.fatcats.allerview.R
import kr.kro.fatcats.allerview.ui.widget.AVButton
import kr.kro.fatcats.allerview.ui.widget.AVImageView

@BindingAdapter(value = ["setImageByGlide"])
fun bindSetImageByGlide (view : AVImageView, value: String?){
    var temp : String? = if(value == "sulfurousAcid"){
        "sulfurous_acid"
    }else{
        value
    }
    val resourceId = view.context.resources.getIdentifier(temp, "mipmap", view.context.packageName)
    Glide.with(view.context).load(resourceId).into(view)
}

@BindingAdapter(value = ["setCheckBoxRootViewChange"])
fun bindSetCheckBoxRootViewChange (view : ConstraintLayout, value: Boolean){
    if(value){
        view.background.setTint(view.context.getColor(R.color.reverse_primary_85))
    }else{
        view.background.setTint(view.context.getColor(R.color.button))
    }
}