package uz.anorgroup.doonkdriver.presentation.viewmodel

import kotlinx.coroutines.flow.Flow

interface StartScreenViewModel {
    val startScreenFlow: Flow<Boolean>
    fun getStartScreen()
}