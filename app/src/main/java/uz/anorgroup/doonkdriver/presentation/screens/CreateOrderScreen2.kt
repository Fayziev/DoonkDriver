package uz.anorgroup.doonkdriver.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest2
import uz.anorgroup.doonkdriver.databinding.ScreenCreateOrderBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreateOrderViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CreateOrderViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class CreateOrderScreen2 : Fragment(R.layout.screen_create_order) {
    private val bind by viewBinding(ScreenCreateOrderBinding::bind)
    private val viewModel: CreateOrderViewModel by viewModels<CreateOrderViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {

//        backBtn.setOnClickListener {
//            findNavController().popBackStack()
//        }

    }
}