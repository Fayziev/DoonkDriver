package uz.anorgroup.doonkdriver.presentation.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.PageCreatePassangerBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreatePageViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.CreatePageViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope


@AndroidEntryPoint
class CreatePage : Fragment(R.layout.page_create_passanger) {
    private val binding by viewBinding(PageCreatePassangerBinding::bind)
    private val viewModel: CreatePageViewModel by viewModels<CreatePageViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        passanger.setOnClickListener {
            viewModel.openPassanger()
            viewModel.openPassangerLiveData.observe(this@CreatePage, {
                findNavController().navigate(R.id.action_mainScreen_to_passengerScreen2)
            })
        }
        parsel.setOnClickListener {
            viewModel.openParcel()
            viewModel.openParcelLiveData.observe(this@CreatePage, {
                findNavController().navigate(R.id.action_mainScreen_to_parcelAddScreen)
            })
        }
    }
}

