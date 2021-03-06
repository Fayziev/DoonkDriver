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
import uz.anorgroup.doonkdriver.databinding.ItemTransportTypeBinding

class TypeOfBodyAdapter : ListAdapter<TypeBodyData, TypeOfBodyAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((TypeBodyData) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<TypeBodyData>() {
        override fun areItemsTheSame(oldItem: TypeBodyData, newItem: TypeBodyData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TypeBodyData, newItem: TypeBodyData): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemTransportTypeBinding::bind)

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> itemListener?.invoke(it1) }
            }
        }

        fun load() {
            val value = getItem(absoluteAdapterPosition)
            bind.transportType.text = value.name
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_transport_type, parent, false))

    fun setListener(f: (TypeBodyData) -> Unit) {
        itemListener = f
    }
}