package uz.anorgroup.doonkdriver.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.databinding.ScreenTruckAddBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.TransportTypeBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.TypeOfBodyBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.TruckViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.TruckViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class TruckAddScreen : Fragment(R.layout.screen_truck_add) {
    private val bind by viewBinding(ScreenTruckAddBinding::bind)
    private val viewModel: TruckViewModel by viewModels<TruckViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle = requireArguments()
        val data = bundle.getParcelable<Parcelable>("data") as CreateCarRequest2
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
            if (tipTransportaLine.text.isNotEmpty() && tipKuzovaLine.text.isNotEmpty()
                && liftingCapacity.text!!.isNotEmpty() && capacity.text!!.isNotEmpty()
                && length.text!!.isNotEmpty() && width.text!!.isNotEmpty() && height.text!!.isNotEmpty()
            ) {
                val newData = CreateCarRequest2(
                    data.brand, data.carModel, data.color, data.yearOfIssue, data.photos, body, type,
                    liftingCapacity.text.toString().toInt(), capacity.text.toString().toInt()
                )
                val bundle2 = Bundle()
                bundle2.putParcelable("data", newData)
                viewModel.openScreen()
                viewModel.openScreenFlow.observe(this@TruckAddScreen, {
                    findNavController().navigate(R.id.action_truckAddScreen_to_createOrderScreen, bundle2)
                })
                errorText.visibility = View.GONE
            } else {
                errorText.visibility = View.VISIBLE
            }
        }

    }
}