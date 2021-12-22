package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenVehicleTypeBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AllCarsAdapter
import uz.anorgroup.doonkdriver.presentation.dialogs.AddCardBottomDialog
import uz.anorgroup.doonkdriver.presentation.viewmodel.AllCarsViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.AllCarsViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class VehicleScreen : Fragment(R.layout.screen_vehicle_type) {
    private val bind by viewBinding(ScreenVehicleTypeBinding::bind)
    private val adapter = AllCarsAdapter()
    private val viewModel: AllCarsViewModel by viewModels<AllCarsViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        super.onViewCreated(view, savedInstanceState)

        nextBt.setOnClickListener {
            findNavController().navigate(R.id.action_vehicleScreen_to_seatScreen)
        }

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllCars()
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
            adapter.notifyDataSetChanged()
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)
        addCarBt.setOnClickListener {
            val dialog = AddCardBottomDialog()
            dialog.show(childFragmentManager, "CardDialog")

            dialog.setEasyCar {
                findNavController().navigate(R.id.action_vehicleScreen_to_truckAddScreen)
                dialog.dismiss()
            }

            dialog.setNormalCar {
                findNavController().navigate(R.id.action_vehicleScreen_to_truckAddScreen)
                dialog.dismiss()
            }

            dialog.setHardCar {
                findNavController().navigate(R.id.action_vehicleScreen_to_truckAddScreen)
                dialog.dismiss()
            }
        }
    }
}