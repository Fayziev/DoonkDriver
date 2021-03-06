package uz.anorgroup.doonkdriver.presentation.screens.auth

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.widget.textChanges
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.databinding.ScreenLoginBinding
import uz.anorgroup.doonkdriver.presentation.viewmodel.auth.LoginViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.impl.auth.LoginViewModelImpl
import uz.anorgroup.doonkdriver.utils.hideKeyboard
import uz.anorgroup.doonkdriver.utils.scope
import uz.anorgroup.doonkdriver.utils.showToast

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val binding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openRegisterFlow.onEach {
            findNavController().navigate(LoginScreenDirections.actionLoginScreenToRegisterScreen(binding.editText.rawText))
        }.launchIn(lifecycleScope)

        viewModel.openVerifyFlow.onEach {
            val bundle = Bundle()
            bundle.putString("phone", binding.editText.rawText)
            bundle.putBoolean("pos", true)
            findNavController().navigate(R.id.action_loginScreen_to_verifyScreen, bundle)
        }.launchIn(lifecycleScope)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (isEnabled) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                    requireActivity().finish()
                }
            }

        })
        editText.hideKeyboard()
        combine(
            editText.textChanges().map {
                it.length == 17

            },
            transform = { phone -> phone }
        ).onEach {
            loginBtn.isEnabled = it[0]
            if (it[0]) editText.hideKeyboard()
        }.launchIn(lifecycleScope)

        loginBtn.setOnClickListener {
            viewModel.login(LoginRequest(editText.rawText))
        }

        viewModel.successFlow.onEach {
            showToast("Success")
        }.launchIn(lifecycleScope)

        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)
        viewModel.errorFlow.onEach {
            showToast(it)
        }.launchIn(lifecycleScope)
    }
}
