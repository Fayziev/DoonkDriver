package uz.anorgroup.doonkdriver.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
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
import uz.anorgroup.doonkdriver.data.request.car.AddressItem
import uz.anorgroup.doonkdriver.data.request.car.OrderCreateRequest
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreateOrderViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CreateOrderViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class CreateOrderScreen2 : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)
    private val viewModel: CreateOrderViewModel by viewModels<CreateOrderViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle2 = requireArguments()
        val data = bundle2.getParcelable<Parcelable>("data2") as OrderCreateRequest
        val listLocation = ArrayList<AddressItem>()
        var position1 = -1
        var qty1 = -1
        var position2 = -1
        var qty2 = -1

        showToast(data.toString())
        nextBt.setOnClickListener {
            if (whereCity.text.isNotEmpty()
                && whereStreet.text.isNotEmpty()
                && directionsCity.text.isNotEmpty()
                && directionsStreet.text.isNotEmpty()
            ) {
                listLocation.add(AddressItem(position1, qty1))
                listLocation.add(AddressItem(position2, qty2))

                error1.visibility = View.GONE
                error2.visibility = View.GONE
                val bundle = Bundle()
                bundle.putParcelable("data2", OrderCreateRequest(data.car, listLocation))
                viewModel.openScreen()
                viewModel.openScreenLiveData.observe(this@CreateOrderScreen2, {
                    findNavController().navigate(R.id.action_createOrderScreen2_to_intermediateScreen2, bundle)
                })
            } else {
                error1.visibility = View.VISIBLE
                error2.visibility = View.VISIBLE
            }
        }

        val bundle = Bundle()
        var id1 = "0"
        var id2 = "0"
        whereCity.setOnClickListener {
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
            if (it.isNotEmpty()) whereCity.text = it
        }.launchIn(lifecycleScope)

        whereStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                qty1 = it.id
                viewModel.whereStreet(it.name)
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }
        viewModel.whereStreetFlow.onEach {
            if (it.isNotEmpty()) whereStreet.text = it
        }.launchIn(lifecycleScope)


        directionsCity.setOnClickListener {
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
            if (it.isNotEmpty()) directionsCity.text = it
        }.launchIn(lifecycleScope)

        directionsStreet.setOnClickListener {
            val dialog = StreetsBottomDialog()
            dialog.arguments = bundle
            dialog.setListener {
                qty2 = it.id
                viewModel.directionStreet(it.name)
                dialog.dismiss()
            }
            dialog.show(childFragmentManager, "StreetDialog")
        }
        viewModel.directionStreetFlow.onEach {
            if (it.isNotEmpty()) directionsStreet.text = it
        }.launchIn(lifecycleScope)

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}