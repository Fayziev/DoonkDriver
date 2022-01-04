package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.responce.car.Passanger
import uz.anorgroup.doonkdriver.databinding.ScreenPassengerinfoBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class PassengerScreenInfo : Fragment(R.layout.screen_passengerinfo) {
    private val binding by viewBinding(ScreenPassengerinfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        val bundle = requireArguments()
        val data=bundle.getSerializable("passenger") as Passanger
        if (data.animal) {
            animal.text = "Yes"
        } else {
            animal.text = "No"
        }
        if (data.luggage) {
            luggage.text = "Yes"
        } else {
            luggage.text = "No"
        }
        if (data.can_smoke) {
            canSmoke.text = "Yes"
        } else {
            canSmoke.text = "No"
        }
        if (data.trailer) {
            trailer.text = "Yes"
        } else {
            trailer.text = "No"
        }
        count.text = data?.count_of_client.toString()

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}