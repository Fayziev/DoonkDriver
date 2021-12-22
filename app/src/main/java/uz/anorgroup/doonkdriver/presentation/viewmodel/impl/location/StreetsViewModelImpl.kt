package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.responce.location.SearchStreetsResponce
import uz.anorgroup.doonkdriver.domain.usecase.location.StreetssDialogUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.location.StreetsViewModels
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject


@HiltViewModel
class StreetsViewModelImpl @Inject constructor(private val useCase: StreetssDialogUseCase) : StreetsViewModels, ViewModel() {
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<SearchStreetsResponce>()
    override val openVerifyFlow = eventValueFlow<Unit>()

    override fun getCitys(city: String, query: String) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }

        useCase.getStreets(city, query).onEach {
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
