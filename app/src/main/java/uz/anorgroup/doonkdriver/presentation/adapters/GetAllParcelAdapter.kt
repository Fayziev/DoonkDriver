package uz.anorgroup.doonkdriver.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Parcel
import uz.anorgroup.doonkdriver.databinding.ItemParcelBinding

class GetAllParcelAdapter(private val parcel: List<Parcel>) :
    RecyclerView.Adapter<GetAllParcelAdapter.HistoryVH>() {
    private var listener: ((Parcel) -> Unit)? = null

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemParcelBinding::bind)

        init {
            itemView.setOnClickListener {
                listener?.invoke(parcel[bindingAdapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun load() {
//            if (parcel[bindingAdapterPosition].address.toString().isEmpty()) {
//                bind.location1.text = "Location1"
//                bind.location2.text = "Location2"
//            } else {
//                bind.location1.text = parcel[bindingAdapterPosition].address.toString()
//                bind.location2.text = parcel[bindingAdapterPosition].address.toString()
//            }
//            if (parcel[bindingAdapterPosition].type.toString().isNotEmpty()) {
//                bind.typeText.text = parcel[bindingAdapterPosition].type.toString()
//            }
//            if (parcel[bindingAdapterPosition].weight.toString().isNotEmpty()) {
//                bind.weightText.text = "${parcel[bindingAdapterPosition].weight} kg"
//            }
            if (parcel[bindingAdapterPosition].width.toString().isNotEmpty()
                && parcel[bindingAdapterPosition].length.toString().isNotEmpty()
                && parcel[bindingAdapterPosition].height.toString().isNotEmpty()
            ) {
                bind.sizeText.text =
                    "${parcel[bindingAdapterPosition].width}cm X ${parcel[bindingAdapterPosition].length}cm X ${parcel[bindingAdapterPosition].height}cm"
            }
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) = holder.load()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_parcel, parent, false))

    override fun getItemCount(): Int = parcel.size

    fun setListener(f: (Parcel) -> Unit) {
        listener = f
    }
}