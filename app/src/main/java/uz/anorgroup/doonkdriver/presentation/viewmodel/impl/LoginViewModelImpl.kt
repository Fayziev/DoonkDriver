package uz.anorgroup.doonkanorgroup.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkanorgroup.data.request.LoginRequest
import uz.anorgroup.doonkanorgroup.domain.usecase.LoginScreenUseCase
import uz.anorgroup.doonkanorgroup.presenter.viewmodel.LoginViewModel
import uz.anorgroup.doonkanorgroup.utils.eventValueFlow
import uz.anorgroup.doonkanorgroup.utils.isConnected
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