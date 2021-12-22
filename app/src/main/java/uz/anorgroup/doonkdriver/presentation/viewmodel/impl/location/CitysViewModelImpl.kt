package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.data.responce.location.SearchCityResponce
import uz.anorgroup.doonkdriver.domain.usecase.location.CitysDialogUseCase
import uz.anorgroup.doonkdriver.presentation.viewmodel.location.CitysViewModels
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import javax.inject.Inject


@HiltViewModel
class CitysViewModelImpl @Inject constructor(private val useCase: CitysDialogUseCase) : CitysViewModels, ViewModel() {
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val successFlow = eventValueFlow<SearchCityResponce>()
    override val openVerifyFlow = eventValueFlow<Unit>()

    override fun getCitys(query: String) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        useCase.getCitys(query).onEach {
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
