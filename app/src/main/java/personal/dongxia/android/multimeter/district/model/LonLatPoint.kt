package personal.dongxia.android.multimeter.district.model

/**
 * @date 2020/6/27
 * @author wudongxia
 */
data class LonLatPoint(val longitude: Float, val latitude: Float) {
    init {
        assert(longitude <= 180F && longitude >= -180F)
        assert(latitude <= 90F && latitude >= -90F)
    }
}