package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.MyStatic
import uz.anorgroup.doonkdriver.data.responce.car.DataItem
import uz.anorgroup.doonkdriver.databinding.ItemCarBinding

class AllCarsAdapter : ListAdapter<DataItem, AllCarsAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((Int) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemCarBinding::bind)

        init {
            bind.item.setOnClickListener {
                itemListener?.invoke(absoluteAdapterPosition)
            }
        }

        fun load(controller: Boolean) {
            if (controller) {
                bind.bgItem.setBackgroundResource(R.drawable.back_edit_drw2)
            } else {
                bind.bgItem.setBackgroundResource(R.drawable.profile_page_atr_bg)
            }
            val value = getItem(absoluteAdapterPosition) as DataItem
            bind.textMarka.text = value.brand
            bind.textModel.text = value.model
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        if (position == MyStatic.count) {
            holder.load(true)
        } else {
            holder.load(false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false))

    fun setListener(f: (Int) -> Unit) {
        itemListener = f
    }
}