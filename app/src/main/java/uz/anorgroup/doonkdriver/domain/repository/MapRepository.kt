package uz.anorgroup.doonkdriver.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.anorgroup.doonkdriver.data.responce.map.MapResponse

interface MapRepository {
    fun getDirection(
        origin: String,
        destination: String,
        key: String,
    ): Flow<Result<MapResponse>>
}