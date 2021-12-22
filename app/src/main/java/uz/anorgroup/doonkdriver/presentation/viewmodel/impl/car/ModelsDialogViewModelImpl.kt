package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.responce.car.ModelResponce
import uz.anorgroup.doonkdriver.domain.usecase.car.ModelsDialogUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.ModelsDialogViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject


@HiltViewModel
class ModelsDialogViewModelImpl @Inject constructor(private val useCase: ModelsDialogUseCase) : ModelsDialogViewModel, ViewModel() {
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<ModelResponce>()
    override val openVerifyFlow = eventValueFlow<Unit>()

    override fun getModels() {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.getModels().onEach {
            it.onSuccess { value ->
                progressFlow.emit(false)
                successFlow.emit(value)
                openVerifyFlow.emit(Unit)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }
}
