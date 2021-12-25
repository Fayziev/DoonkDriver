package uz.anorgroup.doonkdriver.presentation.screens.auth

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.WanderingCubes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.presentation.viewmodel.StartScreenViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.StartScreenViewModelImpl

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.screen_splash) {
    private val viewModel: StartScreenViewModel by viewModels<StartScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val progressBar = view.findViewById(R.id.spin_kit) as ProgressBar
        val doubleBounce: Sprite = WanderingCubes()
        progressBar.indeterminateDrawable = doubleBounce
        viewModel.getStartScreen()
        viewModel.startScreenFlow.onEach {
            delay(2000)
            if (it) findNavController().navigate(R.id.action_splashFragment_to_mainScreen)
            else findNavController().navigate(R.id.action_splashFragment_to_navigation)
        }.launchIn(lifecycleScope)
    }
}