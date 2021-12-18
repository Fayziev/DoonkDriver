package uz.anorgroup.doonkdriver.presentation.screens

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenVehicleTypeBinding

@AndroidEntryPoint
class VehicleScreen : Fragment(R.layout.screen_vehicle_type) {
    private val bind by viewBinding(ScreenVehicleTypeBinding::bind)
}