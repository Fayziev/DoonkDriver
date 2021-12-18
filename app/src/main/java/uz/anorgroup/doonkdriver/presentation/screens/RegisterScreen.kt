package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import uz.anorgroup.doonkdriver.presentation.viewmodel.RegisterViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.RegisterViewModelImpl
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.auth.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.databinding.ScreenRegisterBinding
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {
    private val binding by viewBinding(ScreenRegisterBinding::bind)
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()
    private val args: RegisterScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.successFlow.onEach {
            showToast("Success")
            val bundle = Bundle()
            bundle.putString("phone", args.phone)
            bundle.putString("name", binding.nameEditText.text.toString())
            bundle.putString("surname", binding.surnameEditText.text.toString())
            bundle.putBoolean("pos", false)
            findNavController().navigate(
                R.id.action_registerScreen_to_verifyScreen, bundle
            )
        }.launchIn(lifecycleScope)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        combine(
            nameEditText.textChanges().map {
                it.length > 3
            },
            surnameEditText.textChanges().map {
                it.length > 3
            },
            transform = { name, surname -> name && surname }
        ).onEach {
            loginBtn.isEnabled = it
        }.launchIn(lifecycleScope)

        loginBtn.setOnClickListener {
            viewModel.continueSignUpRequest(
                ContinueSignUpRequest(
                    args.phone
                )
            )
        }
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)

        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)
    }

}
