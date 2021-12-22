package uz.anorgroup.doonkdriver.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.ModelData

class ModelTypeAdapter2(private val list: List<ModelData>) : RecyclerView.Adapter<ModelTypeAdapter2.VH>() {
    private var itemListener: ((ModelData) -> Unit)? = null

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val transportType: TextView = view.findViewById(R.id.transportType)

        init {
            itemView.setOnClickListener {
                itemListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() {
            transportType.text = list[absoluteAdapterPosition].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_transport_type, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
    fun setListener(f:(ModelData)->Unit){
        itemListener=f
    }
}