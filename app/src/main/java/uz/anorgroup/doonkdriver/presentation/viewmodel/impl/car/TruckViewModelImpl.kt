package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.TruckViewModel
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import javax.inject.Inject

@HiltViewModel
class TruckViewModelImpl @Inject constructor() : ViewModel(), TruckViewModel {
    override val openScreenFlow = MutableLiveData<Unit>()
    override fun openScreen() {
        openScreenFlow.value=Unit
    }
}