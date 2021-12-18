package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenInternetConnectionBinding
import uz.anorgroup.doonkdriver.utils.scope

@AndroidEntryPoint
class InternetConnectionScreen : Fragment(R.layout.screen_internet_connection) {
    private val binding by viewBinding(ScreenInternetConnectionBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {


    }
}