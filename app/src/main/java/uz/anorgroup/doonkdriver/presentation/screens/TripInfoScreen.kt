package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.others.MyStatic
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.databinding.ScreenTripInfoBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.OrderCreateViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.OrderCreateViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class TripInfoScreen : Fragment(R.layout.screen_trip_info) {
    private val bind by viewBinding(ScreenTripInfoBinding::bind)
    private val viewModel: OrderCreateViewModel by viewModels<OrderCreateViewModelImpl>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openScreenFlow.onEach {
            findNavController().navigate(R.id.action_tripInfoScreen_to_tripDetalisScreen)
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        val bundle = requireArguments()
        val data = bundle.getParcelable<CreateOrderRequest>("data2") as CreateOrderRequest

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        yesBaggage.setOnClickListener {
            yesBaggage.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noBaggage.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.luggage = true
        }
        noBaggage.setOnClickListener {
            noBaggage.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesBaggage.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesBaggage.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.luggage = false
        }
        yesAnimals.setOnClickListener {
            yesAnimals.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noAnimals.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.animal = true
        }
        noAnimals.setOnClickListener {
            noAnimals.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesAnimals.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesAnimals.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.animal = false
        }
        yesSmoke.setOnClickListener {
            yesSmoke.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noSmoke.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.can_smoke = true
        }
        noSmoke.setOnClickListener {
            noSmoke.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesSmoke.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesSmoke.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.can_smoke = false
        }
        yesTrailer.setOnClickListener {
            yesTrailer.setBackgroundResource(R.drawable.little_circle_bt_bg)
            yesTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            noTrailer.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            noTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.trailer = true
        }
        noTrailer.setOnClickListener {
            noTrailer.setBackgroundResource(R.drawable.little_circle_bt_bg)
            noTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            yesTrailer.setBackgroundResource(R.drawable.little_white_circle_bt_bg)
            yesTrailer.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_transparent))
            MyStatic.trailer = false
        }
        bind.nextBt.setOnClickListener {
            viewModel.orderCreate(
                CreateOrderRequest(
                    data.car,
                    data.address,
                    data.date_of_departure,
                    data.count_of_client,
                    MyStatic.trailer,
                    MyStatic.luggage,
                    MyStatic.animal,
                    MyStatic.can_smoke
                )
            )
        }

        viewModel.errorFlow.onEach {
            showToast("Error:$it")
            viewModel.openExp()
            viewModel.openExpLiveData.observe(viewLifecycleOwner,{
                val bundleNew=Bundle()
                val dataNew=  CreateOrderRequest(
                    data.car,
                    data.address,
                    data.date_of_departure,
                    data.count_of_client,
                    MyStatic.trailer,
                    MyStatic.luggage,
                    MyStatic.animal,
                    MyStatic.can_smoke
                )
                bundleNew.putParcelable("value",dataNew)
                findNavController().navigate(R.id.action_tripInfoScreen_to_expScreen2,bundleNew)
            })
        }.launchIn(lifecycleScope)
        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)
        viewModel.successFlow.onEach {
            showToast("Success")
            viewModel.openScreen()
        }.launchIn(lifecycleScope)
    }
}