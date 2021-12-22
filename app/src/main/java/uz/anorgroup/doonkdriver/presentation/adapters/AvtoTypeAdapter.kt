package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.AvtoTypeData
import uz.anorgroup.doonkdriver.databinding.ItemTypeAutoBinding

class AvtoTypeAdapter : ListAdapter<AvtoTypeData, AvtoTypeAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((AvtoTypeData) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<AvtoTypeData>() {
        override fun areItemsTheSame(oldItem: AvtoTypeData, newItem: AvtoTypeData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AvtoTypeData, newItem: AvtoTypeData): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemTypeAutoBinding::bind)

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> itemListener?.invoke(it1) }
            }
        }

        fun load() {
            val value = getItem(absoluteAdapterPosition) as AvtoTypeData
            bind.name.text = value.name
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_type_auto, parent, false))

    fun setListener(f: (AvtoTypeData) -> Unit) {
        itemListener = f
    }
}