package uz.anorgroup.doonkdriver.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ItemCarBinding

@AndroidEntryPoint
class AddCardBottomDialog : BottomSheetDialogFragment() {
    private var deleteListener: (() -> Unit)? = null
    private var editListener: (() -> Unit)? = null
    private var historyListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.item_car, container, false)

    private val bind by viewBinding(ItemCarBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun setDeletestener(f: () -> Unit) {
        deleteListener = f
    }

    fun setEditstener(f: () -> Unit) {
        editListener = f
    }

    fun setHistorystener(f: () -> Unit) {
        historyListener = f
    }
}