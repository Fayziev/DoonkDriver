package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenTripDetalisBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class TripDetalisScreen : Fragment(R.layout.screen_trip_detalis) {
    private val bind by viewBinding(ScreenTripDetalisBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        yes.setOnClickListener {
            yes.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yes.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            no.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            no.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        no.setOnClickListener {
            no.setBackgroundResource(R.drawable.little_circle_bt_bg)
            no.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yes.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yes.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        nextBt.setOnClickListener {
            findNavController().navigate(R.id.action_tripDetalisScreen_to_showTripScreen)
        }
    }
}