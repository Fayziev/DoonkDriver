//package uz.anorgroup.doonkdriver.presenter.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import by.kirich1409.viewbindingdelegate.viewBinding
//import uz.anorgroup.doonkanorgroup.R
//import uz.anorgroup.doonkanorgroup.data.OrdersInfo
//import uz.anorgroup.doonkanorgroup.databinding.SearchPageItemBinding
//import uz.anorgroup.doonkdriver.data.OrdersInfo
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
//        private val bind by viewBinding(SearchPageItemBinding::bind)
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
//                bind.company.text = it.companyName
//                bind.timeOut.text = it.timeOut
//                bind.timeCome.text = it.timeCome
//                bind.timeTrip.text = it.timeTrip
//                bind.description.text = it.desciption
//                bind.costText.text = it.cost
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
//        holder.load()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
//        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.search_page_item, parent, false))
//}