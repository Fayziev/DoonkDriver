package uz.anorgroup.doonkdriver.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.data.responce.car.Passanger
import uz.anorgroup.doonkdriver.databinding.ItemOrderBinding

class GetAllPassengerAdapter(private val passenger: List<Passanger>) :
    RecyclerView.Adapter<GetAllPassengerAdapter.HistoryVH>() {
    private var listener: ((Passanger) -> Unit)? = null

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemOrderBinding::bind)

        init {
            itemView.setOnClickListener {
                listener?.invoke(passenger[bindingAdapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun load() {
            val client = passenger[absoluteAdapterPosition].count_of_client
            bind.passajirText.text = "$client пассажир"
            bind.countOfClient.text = "${4 - client} мест"
//            if (passenger[absoluteAdapterPosition].date_of_departure.isNullOrEmpty()) {
//                val startDate = passenger[absoluteAdapterPosition].date_of_departure.split('T') as ArrayList<String>
//                bind.startTime.text = startDate[1]
//                bind.dateText.text = startDate[0].reversed()
//            } else {
//                bind.startTime.text = "08:30"
//                bind.dateText.text = "11-11-2021"
//            }
//            passenger[absoluteAdapterPosition].address.let {
//                bind.location.text = it.toString()
//            }
//            if (passenger[absoluteAdapterPosition].address.) {
//                bind.location.text = "Location"
//            } else {
//            }
        }
    }


    override fun onBindViewHolder(holder: HistoryVH, position: Int) = holder.load()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))


    override fun getItemCount(): Int = passenger.size
    fun setListener(f: (Passanger) -> Unit) {
        listener = f
    }
}