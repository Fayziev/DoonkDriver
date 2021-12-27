package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.databinding.ScreenTruckAddBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.TransportTypeBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.TypeOfBodyBottomDialog
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class TruckAddScreen : Fragment(R.layout.screen_truck_add) {
    private val bind by viewBinding(ScreenTruckAddBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle = requireArguments()
        val data = bundle.getParcelable<Parcelable>("data") as CreateCarRequest
        var body = -1
        var type = -1

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        tipTransportaLine.setOnClickListener {
            val dialog = TypeOfBodyBottomDialog()
            dialog.setListener {
                tipTransportaLine.setText(it.name)
                body = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "typeofDialog")
        }

        tipKuzovaLine.setOnClickListener {
            val dialog = TransportTypeBottomDialog()
            dialog.setListener {
                tipKuzovaLine.setText(it.name)
                type = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "transportType")
        }
        saveBtn.setOnClickListener {
            if (body != -1 && type != -1
                && liftingCapacity.text!!.isNotEmpty() && capacity.text!!.isNotEmpty()
                && length.text!!.isNotEmpty() && width.text!!.isNotEmpty() && height.text!!.isNotEmpty()
            ) {
                val newData = CreateCarRequest(
                    data.carSeet, body, type,
                    liftingCapacity.text.toString().toInt(), capacity.text.toString().toInt()
                )
                val bundle2 = Bundle()
                bundle2.putParcelable("data", newData)
//                findNavController().navigate(R.id.action_truckAddScreen_to_addCarScreen, bundle2)
            }
        }

    }
}