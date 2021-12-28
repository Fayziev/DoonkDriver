package uz.anorgroup.doonkdriver.presentation.viewmodel.car

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface TruckViewModel {
    val openScreenFlow: LiveData<Unit>
    fun openScreen()
}