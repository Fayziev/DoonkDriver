package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class CreatePage : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        var qty1 = -1
        var position1 = -1
        var qty2 = -1
        var position2 = -1
        val listLocation = ArrayList<CarSeet>()
        nextBt.setOnClickListener {
//            if (whereCity.text.isEmpty() && whereStreet.text.isEmpty()
//                && directionsStreet.text.isEmpty() && directionsCity.text.isEmpty()
//            ) {
//                error1.visibility = View.VISIBLE
//                error2.visibility = View.VISIBLE
//            } else {
//                error1.visibility = View.GONE
//                error2.visibility = View.GONE
//            }
            if (qty1 != -1 && position1 != -1 && qty2 != -1 && position2 != -1) {
                listLocation.add(CarSeet(position1, qty1))
                listLocation.add(CarSeet(position2, qty2))
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate)
                Timber.d("YYY:" + listLocation[0].position + " " + listLocation[0].position)
                Timber.d("YYY:" + listLocation[1].position + " " + listLocation[1].position)
            } else {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            }
        }

        bind.whereCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.whereCity.text = it.name
                dialog.dismiss()
                qty1 = it.id
            }
            dialog.show(childFragmentManager, "CityDialog")
        }

        bind.whereStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.setListener {
                bind.whereStreet.text = it.name
                dialog.dismiss()
                position1 = it.id
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }


        bind.directionsCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.directionsCity.text = it.name
                dialog.dismiss()
                qty2 = it.id
            }
            dialog.show(childFragmentManager, "CityDialog")
        }
        bind.directionsStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.setListener {
                bind.directionsStreet.text = it.name
                dialog.dismiss()
                position2 = it.id
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }

    }
}