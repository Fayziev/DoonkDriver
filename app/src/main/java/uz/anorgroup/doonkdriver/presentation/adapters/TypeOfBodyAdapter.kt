package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.TypeBodyData
import uz.anorgroup.doonkdriver.databinding.ItemCarBinding

class TypeOfBodyAdapter : ListAdapter<List<TypeBodyData>, TypeOfBodyAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((List<TypeBodyData>) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<List<TypeBodyData>>() {
        override fun areItemsTheSame(oldItem: List<TypeBodyData>, newItem: List<TypeBodyData>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: List<TypeBodyData>, newItem: List<TypeBodyData>): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemCarBinding::bind)

        init {
//            bind.item.setOnClickListener {
//                getItem(absoluteAdapterPosition)?.let { it1 -> itemListener?.invoke(it1) }
//            }
        }

        fun load() {
            val value = getItem(absoluteAdapterPosition)

        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false))
}