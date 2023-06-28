package kr.kro.fatcats.allerview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import kr.kro.fatcats.allerview.databinding.LayoutCheckItemBinding
import kr.kro.fatcats.allerview.model.local.room.entity.MyFoodData
import kr.kro.fatcats.allerview.util.LogUtil


class MyAllergyCheckRVAdapter(private val foodData: ArrayList<MyFoodData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CheckListItemHolder(LayoutCheckItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            binding.root.setOnClickListener { const ->
                foodData[adapterPosition].let {
                    foodData[adapterPosition] = foodData[adapterPosition].copy(
                        it.englishName,
                        it.koreanName,
                        it.isChecked.not(),
                        it.words
                    )
                }
                notifyItemChanged(adapterPosition)
            }
        }
    }

    fun getItem() : ArrayList<MyFoodData>?{
        return foodData
    }
    override fun getItemCount(): Int {
        return foodData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
           is CheckListItemHolder ->{
               holder.binding.data = foodData[position]
               holder.binding.myCheckBox.isChecked = foodData[position].isChecked
           }
        }
    }

    class CheckListItemHolder(val binding: LayoutCheckItemBinding) : RecyclerView.ViewHolder(binding.root){}

}
