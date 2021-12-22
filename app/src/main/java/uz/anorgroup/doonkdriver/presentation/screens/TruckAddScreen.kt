package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenTruckAddBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class TruckAddScreen : Fragment(R.layout.screen_truck_add) {
    private val bind by viewBinding(ScreenTruckAddBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =bind.scope{

        tipTransportaLine.setOnClickListener{

        }

        saveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_truckAddScreen_to_addCarScreen)
        }
    }
}