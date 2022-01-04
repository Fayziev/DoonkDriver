package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.anorgroup.doonkdriver.presentation.viewmodel.car.CreatePageViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePageViewModelImpl @Inject constructor() : ViewModel(), CreatePageViewModel {
    override val openPassangerLiveData = MutableLiveData<Unit>()
    override val openParcelLiveData = MutableLiveData<Unit>()
    override fun openPassanger() {
        openPassangerLiveData.value = Unit
    }

    override fun openParcel() {
        openParcelLiveData.value = Unit
    }
}