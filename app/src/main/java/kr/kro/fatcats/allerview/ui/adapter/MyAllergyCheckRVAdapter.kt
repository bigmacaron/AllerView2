package kr.kro.fatcats.allerview.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import kr.kro.fatcats.allerview.databinding.LayoutCheckItemBinding


class MyAllergyCheckRVAdapter<T>() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items = arrayListOf<T>()
    fun setItem(item : T){
        items.add(item)
        notifyDataSetChanged() // todo
    }

    fun setItems(item : ArrayList<T>){
        items.addAll(item)
        notifyDataSetChanged() // todo
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutCheckItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("Adapter", "getItemCount: ${items.size} ")
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.e("Adapter", "onBindViewHolder: ${items[position]} : Position $position ")
        val setItem: T = items[position]

        val holder =  when(holder){
            is ViewHolder ->{
                holder
            }
            else -> {null}
        }
//
//        when(setItem){
//            is MainModel ->{
//
//            }
//        }

        holder?.binding?.setVariable(BR.checkData, setItem)
        holder?.binding?.executePendingBindings()
    }

    class ViewHolder(val binding: LayoutCheckItemBinding) : RecyclerView.ViewHolder(binding.root){}

}
