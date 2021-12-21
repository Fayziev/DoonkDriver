package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenTripInfoBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class TripInfoScreen : Fragment(R.layout.screen_trip_info) {
    private val bind by viewBinding(ScreenTripInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =bind.scope {
        super.onViewCreated(view, savedInstanceState)
        yesBaggage.setOnClickListener {
            yesBaggage.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noBaggage.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        noBaggage.setOnClickListener {
            noBaggage.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesBaggage.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        yesAnimals.setOnClickListener {
            yesAnimals.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noAnimals.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        noAnimals.setOnClickListener {
            noAnimals.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesAnimals.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        yesSmoke.setOnClickListener {
            yesSmoke.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noSmoke.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        noSmoke.setOnClickListener {
            noSmoke.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesSmoke.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        yesTrailer.setOnClickListener {
            yesTrailer.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noTrailer.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        noTrailer.setOnClickListener {
            noTrailer.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesTrailer.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
        }
        bind.nextBt.setOnClickListener {
            findNavController().navigate(R.id.action_tripInfoScreen_to_tripDetalisScreen)
        }
    }
}