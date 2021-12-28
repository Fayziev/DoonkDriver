package uz.anorgroup.doonkdriver.presentation.page

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
import uz.anorgroup.doonkdriver.data.others.MyStatic
import uz.anorgroup.doonkdriver.databinding.ScreenVehicleBinding
import uz.anorgroup.doonkdriver.presentation.adapters.AllCarsAdapter
import uz.anorgroup.doonkdriver.presentation.viewmodel.AllCarsViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.AllCarsViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast


@AndroidEntryPoint
class CreatePage : Fragment(R.layout.screen_vehicle) {
    private val bind by viewBinding(ScreenVehicleBinding::bind)
    private val adapter = AllCarsAdapter()
    private val viewModel: AllCarsViewModel by viewModels<AllCarsViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openAddCarFlow.onEach {
            findNavController().navigate(R.id.action_mainScreen_to_addCarScreen)
        }.launchIn(lifecycleScope)
        viewModel.openCreateOrderFlow.onEach {
            MyStatic.position=false
            findNavController().navigate(R.id.action_mainScreen_to_createOrderScreen)
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        bind.addAnother.setOnClickListener {
            viewModel.openScreen()
        }
        nextBt.setOnClickListener {
            findNavController().navigate(R.id.createOrderScreen)
        }
        bind.nextBt.setOnClickListener {
            viewModel.openCreateOrder()
        }
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllCars()
        viewModel.successFlow.onEach {
            adapter.submitList(it.data)
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast("Error")
        }.launchIn(lifecycleScope)
    }
}

