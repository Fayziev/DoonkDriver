package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.request.car.CreateOrderRequest
import uz.anorgroup.doonkdriver.data.responce.car.CreateOrderResponse
import uz.anorgroup.doonkdriver.domain.usecase.car.OrderCreateUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.OrderCreateViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class OrderCreateViewModelImpl @Inject constructor(
    private val useCase: OrderCreateUseCase
) : ViewModel(), OrderCreateViewModel {
    override val openScreenFlow = eventValueFlow<Unit>()

    override fun openScreen() {
        viewModelScope.launch {
            openScreenFlow.emit(Unit)
        }
    }

    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<CreateOrderResponse>()

    override fun orderCreate(request: CreateOrderRequest) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.orderCreate(request).onEach {
            it.onSuccess { value ->
                progressFlow.emit(false)
                successFlow.emit(value)
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)
    }
}