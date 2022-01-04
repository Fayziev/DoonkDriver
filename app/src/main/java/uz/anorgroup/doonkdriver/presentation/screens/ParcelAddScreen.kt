package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.AddresX
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.request.car.Parcel
import uz.anorgroup.doonkdriver.data.request.car.Passanger
import uz.anorgroup.doonkdriver.databinding.ScreenNewOrderBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreateOrderViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CreateOrderViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast
@AndroidEntryPoint
class ParcelAddScreen : Fragment(R.layout.screen_new_order) {
    private val binding by viewBinding(ScreenNewOrderBinding::bind)
    private val viewModel: CreateOrderViewModel by viewModels<CreateOrderViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        val listLocation = ArrayList<AddresX>()
        var position1 = -1
        var qty1 = -1
        var position2 = -1
        var qty2 = -1

        nextBt.setOnClickListener {
            if (regionEditText.text!!.isNotEmpty()
                && senderInfo.text!!.isNotEmpty()
                && senderPhone.text!!.isNotEmpty()
                && cityEditText.text!!.isNotEmpty()
                && recipientInfo.text!!.isNotEmpty()
                && recipientPhone.text!!.isNotEmpty()
                && recipientRegionEdit.text!!.isNotEmpty()
                && recipientCityEdit.text!!.isNotEmpty()
            ) {
                listLocation.add(AddresX(position1, qty1))
                listLocation.add(AddresX(position2, qty2))
                val bundle = Bundle()
                bundle.putParcelable("parcel", CreateOrderRequest(Parcel(listLocation), Passanger()))
                findNavController().navigate(R.id.action_parcelAddScreen_to_parcelSeatScreen, bundle)
            } else {
                showToast("Fill in the blanks")
            }
        }

        val bundle = Bundle()
        var id1 = "0"
        var id2 = "0"
        regionEditText.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                viewModel.whereCity(it.name)
                id1 = it.id.toString()
                bundle.putString("id", id1)
                position1 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "CityDialog")
        }
        viewModel.whereCityFlow.onEach {
            if (it.isNotEmpty()) regionEditText.setText(it)
        }.launchIn(lifecycleScope)

        cityEditText.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                viewModel.whereStreet(it.name)
                qty1 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }

        viewModel.whereStreetFlow.onEach {
            if (it.isNotEmpty()) cityEditText.setText(it)
        }.launchIn(lifecycleScope)


        recipientRegionEdit.setOnClickListener {
            val dialog = CitysBottomDialog()
            dialog.setListener {
                viewModel.directionCity(it.name)
                position2 = it.id
                id2 = it.id.toString()
                bundle.putString("id", id2)
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "CityDialog")
        }

        viewModel.directionCityFlow.onEach {
            if (it.isNotEmpty()) recipientRegionEdit.setText(it)
        }.launchIn(lifecycleScope)


        recipientCityEdit.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                viewModel.directionStreet(it.name)
                qty2 = it.id
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }

        viewModel.directionStreetFlow.onEach {
            if (it.isNotEmpty()) recipientCityEdit.setText(it)
        }.launchIn(lifecycleScope)

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}