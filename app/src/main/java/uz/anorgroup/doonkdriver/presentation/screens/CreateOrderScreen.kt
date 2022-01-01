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
import uz.anorgroup.doonkdriver.data.request.car.CarSeet
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.presentation.dialogs.CitysBottomDialog
import uz.anorgroup.doonkdriver.presentation.dialogs.StreetsBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreateOrderViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CreateOrderViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class CreateOrderScreen : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)
    private val viewModel: CreateOrderViewModel by viewModels<CreateOrderViewModelImpl>()
    private lateinit var bundleScreen: Bundle
    private lateinit var data: CreateCarRequest2

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        bundleScreen = requireArguments()
        data = bundleScreen.getParcelable<Parcelable>("data") as CreateCarRequest2
        showToast(data.carSeet?.size.toString())

        val listLocation = ArrayList<CarSeet>()
        var position1 = -1
        var qty1 = -1
        var position2 = -1
        var qty2 = -1

        nextBt.setOnClickListener {
            if (whereCity.text.isNotEmpty()
                && whereStreet.text.isNotEmpty()
                && directionsCity.text.isNotEmpty()
                && directionsStreet.text.isNotEmpty()
            ) {
                listLocation.add(CarSeet(position1, qty1))
                listLocation.add(CarSeet(position2, qty2))
                val dataNew = CreateCarRequest2(
                    data.brand,
                    data.carModel,
                    data.color,
                    data.yearOfIssue,
                    data.photos,
                    data.typeOfBody,
                    data.typeOfTransport,
                    data.liftingCapacity,
                    data.weight,
                    listLocation
                )
                val bundle = Bundle()
                bundle.putParcelable("data", dataNew)
                error1.visibility = View.GONE
                error2.visibility = View.GONE
                viewModel.openScreen()
                viewModel.openScreenLiveData.observe(this@CreateOrderScreen, {
                    findNavController().navigate(R.id.action_createOrderScreen_to_screenIntermediate, bundle)
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