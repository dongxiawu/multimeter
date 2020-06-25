//package personal.dongxia.android.multimeter
//
//import android.util.Size
//import java.util.*
//
///**
// * @date 2020/6/11
// * @author wudongxia
// */
//
//fun getOptimalPreviewOutputSize(sizeList: List<Size>, surfaceSize: Size) : Size {
//
//}
//
//fun getMinPreSize(sizeMap: Array<Size>, surfaceWidth: Int, surfaceHeight: Int, maxHeight: Int): Size? {
//    // 得到与传入的宽高比最接近的size
//    val reqRatio = surfaceWidth.toFloat() / surfaceHeight
//    var curRatio: Float
//    val sizeList: MutableList<Size> = ArrayList()
//    var retSize: Size? = null
//    for (size in sizeMap) {
//        curRatio = size.height.toFloat() / size.width
//        if (reqRatio == curRatio) {
//            sizeList.add(size)
//        }
//    }
////    if (sizeList != null && sizeList.size != 0) {
////        for (i in sizeList.indices.reversed()) {
////            //取Size宽度大于1000的第一个数,这里我们获取大于maxHeight的第一个数，理论上我们是想获取size.getWidth宽度为1080或者1280那些，因为这样的预览尺寸已经足够了
////            if (sizeList[i].width >= maxHeight) {
////                retSize = sizeList[i]
////                break
////            }
////        }
////
////        //可能没有宽度大于maxHeight的size,则取相同比例中最小的那个size
////        if (retSize == null) {
////            retSize = sizeList[sizeList.size - 1]
////        }
////    } else {
////        retSize = com.cktim.camera2library.camera.Camera2Util.getCloselyPreSize(sizeMap, surfaceWidth, surfaceHeight)
////    }
//    return retSize
//}