package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.LocationAddData
import uz.anorgroup.doonkdriver.databinding.ItemAddLocationBinding

class AddLocationAdapter(private val list: List<LocationAddData>) : RecyclerView.Adapter<AddLocationAdapter.HistoryVH>() {
    private var itemCityListener: (() -> Unit)? = null
    private var itemStreetListener: (() -> Unit)? = null


    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemAddLocationBinding::bind)

        init {
            bind.whereCity.setOnClickListener {
                itemCityListener?.invoke()
            }
            bind.whereStreet.setOnClickListener {
                itemStreetListener?.invoke()
            }
        }

        fun load() {
//                bind.whereCity.text = list[absoluteAdapterPosition].city
//                bind.whereStreet.text = list[absoluteAdapterPosition].street
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) = holder.load()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_add_location, parent, false))

    fun setCityListener(f: () -> Unit) {
        itemCityListener = f
    }

    fun setStreetListener(f: () -> Unit) {
        itemStreetListener = f
    }

    override fun getItemCount(): Int = list.size
}