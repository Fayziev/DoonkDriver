package uz.anorgroup.doonkdriver.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.request.ContinueSignUpRequest
import uz.anorgroup.doonkdriver.domain.usecase.RegisterScreenUseCase
import uz.anorgroup.doonkdriver.presenter.viewmodel.RegisterViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(private val useCase: RegisterScreenUseCase) : ViewModel(), RegisterViewModel {
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<Unit>()
    override val openVerifyFlow = eventValueFlow<Unit>()

    override fun continueSignUpRequest(request: ContinueSignUpRequest) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.continueSingUp(request).onEach {
            it.onSuccess {
                progressFlow.emit(false)
                successFlow.emit(Unit)
                openVerifyFlow.emit(Unit)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }
}