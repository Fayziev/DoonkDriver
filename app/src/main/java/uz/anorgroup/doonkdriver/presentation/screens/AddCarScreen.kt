package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenCarAddBinding

@AndroidEntryPoint
class AddCarScreen : Fragment(R.layout.screen_car_add) {
    private val bind by viewBinding(ScreenCarAddBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.saveBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_addCarScreen_to_tripDetalisScreen
            )
        }
    }
}