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
    private var itemCityListener: ((Int) -> Unit)? = null
    private var itemStreetListener: ((Int) -> Unit)? = null
    private var itemDeleteListener: ((Int) -> Unit)? = null

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
            bind.whereCity.setOnClickListener {
                itemCityListener?.invoke(bindingAdapterPosition)
            }
            bind.whereStreet.setOnClickListener {
                itemStreetListener?.invoke(bindingAdapterPosition)
            }
            bind.delete.setOnClickListener {
                itemDeleteListener?.invoke(bindingAdapterPosition)
            }
        }

        fun load() {
            val item = getItem(bindingAdapterPosition)
            bind.whereCity.text = if (item.city.isEmpty()) bind.root.context.getString(R.string.city) else item.city
            bind.whereStreet.text = if (item.street.isEmpty()) bind.root.context.getString(R.string.street) else item.street
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_add_location, parent, false))

    fun setCityListener(f: (Int) -> Unit) {
        itemCityListener = f
    }

    fun setStreetListener(f: (Int) -> Unit) {
        itemStreetListener = f
    }
    fun setDeleteListener(f: (Int) -> Unit) {
        itemDeleteListener = f
    }
}