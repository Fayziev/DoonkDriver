package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.request.auth.LoginRequest
import uz.anorgroup.doonkdriver.domain.usecase.auth.LoginScreenUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.auth.LoginViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(private val useCase: LoginScreenUseCase) : ViewModel(), LoginViewModel {
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<Unit>()
    override val openRegisterFlow = eventValueFlow<Unit>()
    override val openVerifyFlow = eventValueFlow<Unit>()

    override fun login(request: LoginRequest) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.userLogin(request).onEach {
            it.onSuccess {
                progressFlow.emit(false)
                successFlow.emit(Unit)
                openVerifyFlow.emit(Unit)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
                openRegisterFlow.emit(Unit)
            }
        }.launchIn(viewModelScope)
    }
}