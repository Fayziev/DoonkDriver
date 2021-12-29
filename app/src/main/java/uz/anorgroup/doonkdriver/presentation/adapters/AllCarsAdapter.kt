package uz.anorgroup.doonkdriver.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.DataItem
import uz.anorgroup.doonkdriver.databinding.ItemCarBinding

class AllCarsAdapter(var itemPosition: Int,var listener:(model:DataItem)->Unit) : ListAdapter<DataItem, AllCarsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((DataItem) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(var binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
//        private val bind by viewBinding(ItemCarBinding::bind)

//        init {
//            itemView.setOnClickListener {
//                bind.root.setOnClickListener {
//                    itemPosition=adapterPosition
//                }
//            }
//        }

        fun load(position: Int)= with(binding) {
binding.root.setOnClickListener {
    Log.d("BBB", "load:Bosildi ")
    itemPosition=adapterPosition
    listener(getItem(position))
}

            val value = getItem(absoluteAdapterPosition) as DataItem
            textMarka.text = value.brand
            textModel.text = value.model
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load(position)
        if (position == itemPosition) {
            holder.binding.background.setBackgroundResource(R.drawable.profile_page_line_bg)
        } else {
            holder.binding.background.setBackgroundResource(R.drawable.profile_page_atr_bg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(ItemCarBinding.inflate(LayoutInflater.from(parent.context),parent,false))

//    fun setListener(f: (DataItem) -> Unit) {
//        itemListener = f
//    }
}