package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.utils.scope


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

            if (qty1 != -1 && position1 != -1 && qty2 != -1 && position2 != -1) {
                listLocation.add(CarSeet(position1, qty1))
                listLocation.add(CarSeet(position2, qty2))
                val data = CreateCarRequest(listLocation)
                val bundle = Bundle()
                bundle.putParcelable("data", data)
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                findNavController().navigate(R.id.action_mainScreen_to_screenIntermediate, bundle)
            } else {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            }

        }

        val bundle = Bundle()
        var id1 = "0"
        var id2 = "0"
        bind.whereCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.whereCity.text = it.name
                id1 = it.id.toString()
                position1 = it.id
                dialog.dismiss()
            }
            bundle.putString("id", id1)
            dialog.show(childFragmentManager, "CityDialog")
        }

        bind.whereStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.whereStreet.text = it.name
                qty1 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }


        bind.directionsCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                bind.directionsCity.text = it.name
                id2 = it.id.toString()
                position2 = it.id
                dialog.dismiss()

            }
            bundle.putString("id", id2)
            dialog.show(childFragmentManager, "CityDialog")
        }
        bind.directionsStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                bind.directionsStreet.text = it.name
                qty2 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")

        }

    }
}

