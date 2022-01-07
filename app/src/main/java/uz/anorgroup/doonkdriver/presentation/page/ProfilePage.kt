package uz.anorgroup.doonkdriver.presentation.page

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.PageProfileBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.ProfileViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car.ProfileViewModelImpl
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class ProfilePage : Fragment(R.layout.page_profile) {

    private val bind by viewBinding(PageProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = bind.scope {
        viewModel.getImage()
        viewModel.getName()
        viewModel.getNameFlow.onEach {
            name.text = it
        }.launchIn(lifecycleScope)
        viewModel.getImageFlow.onEach {
            Glide.with(requireContext())
                .load(it)
                .centerCrop()
                .placeholder(R.drawable.ic_trip_detalis)
                .into(avatar)
        }.launchIn(lifecycleScope)
        carsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_vehicleScreen)
        }
        exit.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("Do you really want to logout")
                .setTitle("Logout")
                .setPositiveButton("Yes") { dialog, which ->
                    viewModel.setStartScreen(false)
                    viewModel.openScreen()
                    viewModel.openScreenFlow.onEach {
                        findNavController().navigate(R.id.navigation)
                    }.launchIn(lifecycleScope)
                }.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }.show()
        }
    }
}