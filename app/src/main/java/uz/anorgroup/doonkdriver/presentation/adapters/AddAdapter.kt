package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.LocationAddData
import uz.anorgroup.doonkdriver.databinding.ItemAddLocationBinding

class AddAdapter : ListAdapter<LocationAddData, AddAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((LocationAddData) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<LocationAddData>() {
        override fun areItemsTheSame(oldItem: LocationAddData, newItem: LocationAddData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LocationAddData, newItem: LocationAddData): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemAddLocationBinding::bind)

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> itemListener?.invoke(it1) }
            }
        }

        fun load() {

//            if (getItem(absoluteAdapterPosition).city.isNotEmpty()
//                && getItem(absoluteAdapterPosition).street.isNotEmpty()
//            ) {
//                bind.whereCity.text = getItem(absoluteAdapterPosition).city
//                bind.whereStreet.text = getItem(absoluteAdapterPosition).street
//            }
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_add_location, parent, false))

    fun setListener(f: (LocationAddData) -> Unit) {
        itemListener = f
    }
}