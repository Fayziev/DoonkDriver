package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.DataX
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.databinding.ItemOrderBinding

//class GetAllOrdersAdapter : ListAdapter<Parcel, GetAllOrdersAdapter.HistoryVH>(MyDifUtils) {
//    private var itemListener: ((Int) -> Unit)? = null
//
//    object MyDifUtils : DiffUtil.ItemCallback<DataX>() {
//        override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
//        private val bind by viewBinding(ItemOrderBinding::bind)
//
//        init {
////            bind.item.setOnClickListener {
////                itemListener?.invoke(absoluteAdapterPosition)
////            }
//        }
//
//        fun load() {
////            val parcel = getItem(absoluteAdapterPosition).parcel
////            val passenger = getItem(absoluteAdapterPosition).passanger
////            bind.passajirText.text = "${passenger[absoluteAdapterPosition].count_of_client} пассажир"
////            bind.countOfClient.text = "${4 - passenger[absoluteAdapterPosition].count_of_client} мест"
////            val startDate = passenger[absoluteAdapterPosition].date_of_departure.split('T') as Array<*>
////            bind.startTime.text = startDate[1].toString()
////            bind.dateText.text = startDate[0].toString().reversed()
////            bind.description.text = "Comment:${parcel[absoluteAdapterPosition].comment}"
////            bind.location.text = parcel[absoluteAdapterPosition].address.toString()
//        }
//    }
//
//    override fun onBindViewHolder(holder: HistoryVH, position: Int) = holder.load()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
//        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))
//
//    fun setListener(f: (Int) -> Unit) {
//        itemListener = f
//    }
//}