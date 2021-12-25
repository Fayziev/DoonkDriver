package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenShowTripBinding

@AndroidEntryPoint
class ShowTripScreen : Fragment(R.layout.screen_show_trip) {
    private val bind by viewBinding(ScreenShowTripBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind.nextBt.setOnClickListener {
            findNavController().navigate(R.id.mainScreen)
        }
    }
}