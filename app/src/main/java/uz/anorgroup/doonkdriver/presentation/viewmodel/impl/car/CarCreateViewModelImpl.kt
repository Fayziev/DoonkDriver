package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.request.car.CreateCarRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateCarResponce
import uz.anorgroup.doonkdriver.domain.usecase.car.CarCreateUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CarCreateViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class CarCreateViewModelImpl @Inject constructor(private val useCase: CarCreateUseCase) : ViewModel(), CarCreateViewModel {

    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<CreateCarResponce>()
    override val openVerifyFlow = eventValueFlow<Unit>()

    override fun carCreate(request: CreateCarRequest) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.createCar(request).onEach {
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