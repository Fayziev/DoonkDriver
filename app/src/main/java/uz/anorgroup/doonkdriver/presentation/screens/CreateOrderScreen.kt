package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class CreateOrderScreen : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        nextBt.setOnClickListener {
            if (whereCity.text.isEmpty() && whereStreet.text.isEmpty()
                && directionsStreet.text.isEmpty() && directionsCity.text.isEmpty()) {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            } else {
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate)
            }
        }
    }
}