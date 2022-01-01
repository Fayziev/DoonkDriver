package uz.anorgroup.doonkdriver.presentation.viewmodel.impl.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.anorgroup.doonkdriver.domain.repository.repositoryimpl.MapRepositoryImpl
import uz.anorgroup.doonkdriver.presentation.viewmodel.map.MapViewModel
import uz.anorgroup.doonkdriver.utils.eventFlow
import uz.anorgroup.doonkdriver.utils.eventValueFlow
import uz.anorgroup.doonkdriver.utils.isConnected
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MapViewModelImpl @Inject constructor(private val repository: MapRepositoryImpl) : ViewModel(), MapViewModel {
    override val successFlow = eventValueFlow<List<LatLng>>()
    override val errorFlow = eventValueFlow<String>()
    override val progressFlow = eventValueFlow<Boolean>()
    override val openAddCarFlow = eventValueFlow<Unit>()
    override val openCreateOrderFlow = eventFlow()

    override fun getMap(origin: String, destination: String, key: String) {
        if (!isConnected()) {
            viewModelScope.launch {
                errorFlow.emit("Internet bilan muammo bo'ldi")
            }
            return
        }
        viewModelScope.launch {
            progressFlow.emit(true)
        }
        repository.getDirection(origin, destination, key).onEach {
            it.onSuccess { value ->
                progressFlow.emit(false)
                val points = value.routes[0].overviewPolyline.points
                successFlow.emit(decodePoly(points))
            }
            it.onFailure { throwable ->
                progressFlow.emit(false)
                errorFlow.emit(throwable.message.toString())
            }
        }.launchIn(viewModelScope)

    }


    private fun decodePoly(
        encoded: String
    ): List<LatLng> {
        val poly: MutableList<LatLng> = ArrayList()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val p = LatLng(
                lat.toDouble() / 1E5,
                lng.toDouble() / 1E5
            )
            poly.add(p)
        }
        return poly
    }
}