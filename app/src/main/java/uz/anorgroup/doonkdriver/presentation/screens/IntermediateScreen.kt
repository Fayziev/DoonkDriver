package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.databinding.ScreenIntermediateBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.timber

@AndroidEntryPoint
class IntermediateScreen : Fragment(R.layout.screen_intermediate) {
    private val bind by viewBinding(ScreenIntermediateBinding::bind)
    private var position = -1
    private var qty = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)
        val bundle2 = requireArguments()
        val data: CreateCarRequest = bundle2.getParcelable("data")!!

        timber(data.carSeet?.size.toString())
        addCarBt.setOnClickListener {
            addCarBt.visibility = View.GONE
            back2.visibility = View.VISIBLE
            whereCity2.visibility = View.VISIBLE
            whereStreet2.visibility = View.VISIBLE
            location2.visibility = View.VISIBLE

//            if (position != -1 && qty != -1) {
//                error1.visibility = View.GONE
//                val listLocation = ArrayList<CarSeet>()
//                data.carSeet?.let { it1 -> listLocation.addAll(it1) }
//                listLocation.add(CarSeet(position, qty))
//                val creteRequest = CreateCarRequest(listLocation)
//                val bundle = Bundle()
//                bundle.putParcelable("data", creteRequest)
//                findNavController().navigate(R.id.truckAddScreen, bundle)
//            } else {
//                error1.visibility = View.VISIBLE
//            }
        }

        nextBt.setOnClickListener {

            error1.visibility = View.GONE
            val listLocation = ArrayList<CarSeet>()
            data.carSeet?.let { it1 -> listLocation.addAll(it1) }
            val creteRequest = CreateCarRequest(listLocation)
            val bundle = Bundle()
            bundle.putParcelable("data", creteRequest)
            findNavController().navigate(R.id.truckAddScreen, bundle)
        }
        val bundle = Bundle()
        var id1 = "0"


        whereCity.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                id1 = it.id.toString()
                whereCity.text = it.name
                bundle.putString("id", id1)
                position = it.id
                if (id1 == it.id.toString()) {
                    dialog.dismiss()
                }
            }
            dialog.show(childFragmentManager, "CityDialog")
        }

        whereStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                qty = it.id
                whereStreet.text = it.name
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }
    }
}