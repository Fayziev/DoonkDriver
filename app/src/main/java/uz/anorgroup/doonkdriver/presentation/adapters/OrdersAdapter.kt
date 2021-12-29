//package uz.anorgroup.doonkdriver.presentation.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import by.kirich1409.viewbindingdelegate.viewBinding
//import uz.anorgroup.doonkdriver.R
//import uz.anorgroup.doonkdriver.data.others.OrdersInfo
//import uz.anorgroup.doonkdriver.databinding.ItemOrderBinding
//
//class OrdersAdapter : ListAdapter<OrdersInfo, OrdersAdapter.HistoryVH>(MyDifUtils) {
//    private var itemListener: ((OrdersInfo) -> Unit)? = null
//
//    object MyDifUtils : DiffUtil.ItemCallback<OrdersInfo>() {
//        override fun areItemsTheSame(oldItem: OrdersInfo, newItem: OrdersInfo): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: OrdersInfo, newItem: OrdersInfo): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
//        private val bind by viewBinding(ItemOrderBinding::bind)
//
//        init {
//            bind.item.setOnClickListener {
//                getItem(absoluteAdapterPosition)?.let { it1 -> itemListener?.invoke(it1) }
//            }
//        }
//
//        fun load() {
//            val value = getItem(absoluteAdapterPosition)
//            value?.let {
//                bind.
////                    .text = it.companyName
////                bind.timeOut.text = it.timeOut
////                bind.timeCome.text = it.timeCome
////                bind.timeTrip.text = it.timeTrip
////                bind.description.text = it.desciption
////                bind.costText.text = it.cost
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
//        holder.load()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
//        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))
//}