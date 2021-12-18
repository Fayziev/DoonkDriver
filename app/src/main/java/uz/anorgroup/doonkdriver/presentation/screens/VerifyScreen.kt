package uz.anorgroup.doonkdriver.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.anorgroup.doonkanorgroup.presenter.viewmodel.VerifyViewModel
import uz.anorgroup.doonkanorgroup.presenter.viewmodel.impl.VerifyViewModelImpl
import uz.anorgroup.doonkdriver.R
import uz.anorgroup.doonkdriver.databinding.ScreenVerifyBinding
import uz.gita.doonkdriver.data.request.RegisterRequest
import uz.gita.doonkdriver.data.request.VerifyRequest
import uz.gita.doonkdriver.utils.scope
import uz.gita.doonkdriver.utils.showToast

@AndroidEntryPoint
class VerifyScreen : Fragment(R.layout.screen_verify) {
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel: VerifyViewModel by viewModels<VerifyViewModelImpl>()
    private var s: String? = null
    private var phone: String? = null
    private var name: String? = null
    private var surname: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.successFlow.onEach {
            showToast("Success")
//            findNavController().navigate(R.id.action_verifyScreen_to_mainScreen)
        }.launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        loginBtn.isEnabled = false
        val bundle = requireArguments()
        val pos = bundle.getBoolean("pos")
        if (pos) {
            phone = bundle.getString("phone") as String
        } else {
            phone = bundle.getString("phone") as String
            name = bundle.getString("name") as String
            surname = bundle.getString("surname") as String
        }
        smsVerifyCode.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->
            loginBtn.isEnabled = isComplete
            if (isComplete) {
                s = code
            }
        }
        loginBtn.setOnClickListener {
            var code = ""
            s?.let {
                code = it
            }
            if (pos) {
                viewModel.verify(
                    VerifyRequest(code, phone!!)
                )
            } else {
                viewModel.register(
                    RegisterRequest(code, phone!!, surname!!, name!!)
                )
            }
        }

        viewModel.errorFlow.onEach {
            showToast("Error code")
        }.launchIn(lifecycleScope)

        viewModel.progressFlow.onEach {
            if (it) progress.show()
            else progress.hide()
        }.launchIn(lifecycleScope)

        viewModel.openMainFlow.onEach {
//            findNavController().navigate(R.id.mainScreen)
        }.launchIn(lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        s = null
        phone = null
        name = null
        surname = null
    }

}