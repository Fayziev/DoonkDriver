package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.ModelData
import uz.anorgroup.doonkdriver.databinding.ItemTransportTypeBinding

class ModelTypeAdapter : ListAdapter<ModelData, ModelTypeAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((ModelData) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<ModelData>() {
        override fun areItemsTheSame(oldItem: ModelData, newItem: ModelData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ModelData, newItem: ModelData): Boolean {
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
            val value = getItem(absoluteAdapterPosition) as ModelData
            bind.transportType.text = value.name
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_transport_type, parent, false))

    fun setListener(f: (ModelData) -> Unit) {
        itemListener = f
    }
}