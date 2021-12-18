package uz.anorgroup.doonkdriver.presentation.screens

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenTripDetalisBinding

@AndroidEntryPoint
class TripDetalisScreen : Fragment(R.layout.screen_trip_detalis) {
    private val bind by viewBinding(ScreenTripDetalisBinding::bind)

}