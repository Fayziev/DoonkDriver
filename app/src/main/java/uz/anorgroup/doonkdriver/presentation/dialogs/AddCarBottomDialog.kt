package uz.anorgroup.doonkdriver.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.BottomDialogAddCarBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.BodyBtDialogViewModelImpl

@AndroidEntryPoint
class AddCarBottomDialog : BottomSheetDialogFragment() {
    private var easyCar: (() -> Unit)? = null
    private var normalCar: (() -> Unit)? = null
    private var hardCar: (() -> Unit)? = null
    private val viewModel by viewModels<BodyBtDialogViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.bottom_dialog_add_car, container, false)

    private val bind by viewBinding(BottomDialogAddCarBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.furaCar.setOnClickListener {
            hardCar?.invoke()
        }
        bind.littleCar.setOnClickListener {
            easyCar?.invoke()
        }
        bind.busCar.setOnClickListener {
            normalCar?.invoke()
        }
    }

}