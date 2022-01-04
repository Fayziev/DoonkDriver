package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.data.responce.car.Passanger
import uz.anorgroup.doonkdriver.databinding.ItemOrderBinding

class GetAllOrdersAdapter2(private val parcel: List<Parcel>, private val passenger: List<Passanger>) :
    RecyclerView.Adapter<GetAllOrdersAdapter2.HistoryVH>() {

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemOrderBinding::bind)

        init {
//            bind.item.setOnClickListener {
//                itemListener?.invoke(absoluteAdapterPosition)
//            }
        }

        fun load() {
            bind.passajirText.text = "${passenger[absoluteAdapterPosition].count_of_client} пассажир"
            bind.countOfClient.text = "${4 - passenger[absoluteAdapterPosition].count_of_client} мест"
//            val startDate = passenger[absoluteAdapterPosition].date_of_departure.split('T') as ArrayList<String>
//            bind.startTime.text = startDate[1]
//            bind.dateText.text = startDate[0].reversed()
            bind.description.text = "Comment:${parcel[absoluteAdapterPosition].comment}"
//            if (parcel[absoluteAdapterPosition].address.toString().isNullOrEmpty()) {
//                bind.location.text = parcel[absoluteAdapterPosition].address.toString()
//            } else {
//                bind.location.text = "Location"
//            }
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) = holder.load()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))

//    fun setListener(f: (Int) -> Unit) {
//        itemListener = f
//    }

    override fun getItemCount(): Int {
        return if (parcel.size >= passenger.size) passenger.size
        else parcel.size
    }
}