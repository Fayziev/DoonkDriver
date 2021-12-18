package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenTripDetalisBinding

@AndroidEntryPoint
class TripDetalisScreen : Fragment(R.layout.screen_trip_detalis) {
    private val bind by viewBinding(ScreenTripDetalisBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.nextBt.setOnClickListener {
            findNavController().navigate(R.id.action_tripDetalisScreen_to_showTripScreen)
        }
    }
}