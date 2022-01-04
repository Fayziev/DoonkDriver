package uz.anorgroup.doonkdriver.presentation.screens

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
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.request.car.Parcel
import uz.anorgroup.doonkdriver.data.request.car.Passanger
import uz.anorgroup.doonkdriver.databinding.ScreenAboutReferenceBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.OrderCreateViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.OrderCreateViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class ParcelSeatScreen : Fragment(R.layout.screen_about_reference) {
    private val binding by viewBinding(ScreenAboutReferenceBinding::bind)
    private val viewModel: OrderCreateViewModel by viewModels<OrderCreateViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        val bundle = requireArguments()
        val data = bundle.getParcelable<Parcelable>("parcel") as CreateOrderRequest

        saveBtn.setOnClickListener {

                viewModel.orderCreate(
                    CreateOrderRequest(
                        Parcel(
                            data.parcel.address, data.parcel.height, data.parcel.length, 1, data.parcel.weight, data.parcel.width
                        ),
                        Passanger()
                    )
                )
        }
        viewModel.errorFlow.onEach {
            showToast("Error:$it")
        }.launchIn(lifecycleScope)
        viewModel.successFlow.onEach {
            showToast("Success")
            findNavController().navigate(R.id.mainScreen)
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)
        viewModel.openScreenFlow.onEach {
        }.launchIn(lifecycleScope)
    }
}