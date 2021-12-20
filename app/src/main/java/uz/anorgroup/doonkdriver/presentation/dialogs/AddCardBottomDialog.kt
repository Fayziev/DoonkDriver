package uz.anorgroup.doonkdriver.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.BottomDialogAddCarBinding

@AndroidEntryPoint
class AddCardBottomDialog : BottomSheetDialogFragment() {
    private var easyCar: (() -> Unit)? = null
    private var normalCar: (() -> Unit)? = null
    private var hardCar: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_add_car, container, false)

    private val bind by viewBinding(BottomDialogAddCarBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun setEasyCar(f: () -> Unit) {
        easyCar = f
    }

    fun setNormalCar(f: () -> Unit) {
        normalCar = f
    }

    fun setHardCar(f: () -> Unit) {
        hardCar = f
    }
}