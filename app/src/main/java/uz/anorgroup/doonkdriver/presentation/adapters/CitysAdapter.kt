package uz.anorgroup.doonkdriver.presentation.adapters

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.location.DataCity
import uz.anorgroup.doonkdriver.databinding.ItemTransportTypeBinding
import uz.anorgroup.doonkdriver.utils.getColor

class CitysAdapter(var query: String) : ListAdapter<DataCity, CitysAdapter.HistoryVH>(MyDifUtils) {
    private var itemListener: ((DataCity) -> Unit)? = null

    object MyDifUtils : DiffUtil.ItemCallback<DataCity>() {
        override fun areItemsTheSame(oldItem: DataCity, newItem: DataCity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataCity, newItem: DataCity): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryVH(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(ItemTransportTypeBinding::bind)

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { it1 -> itemListener?.invoke(it1) }
            }
        }

        fun load() {
            val value = getItem(absoluteAdapterPosition) as DataCity
            val spanSt = SpannableString(value.name)
            val foregroundSpan = ForegroundColorSpan(getColor(android.R.color.holo_red_dark))
            val startIndex = value.name.indexOf(query, 0, true)//  salom  a=1 5-1=4
            val lastIndex = startIndex + query.length
            spanSt.setSpan(foregroundSpan, startIndex, lastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            bind.transportType.text = spanSt
        }
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.load()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH =
        HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_transport_type, parent, false))

    fun setListener(f: (DataCity) -> Unit) {
        itemListener = f
    }
}