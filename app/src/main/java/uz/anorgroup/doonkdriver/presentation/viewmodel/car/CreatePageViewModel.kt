package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import androidx.lifecycle.LiveData

interface CreatePageViewModel {

    val openPassangerLiveData:LiveData<Unit>
    val openParcelLiveData:LiveData<Unit>
    fun openPassanger()
    fun openParcel()
}