package personal.dongxia.android.multimeter.phone

/**
 * 号码归属地信息
 * @date 2020/9/19
 * @author wudongxia
 */
data class PhoneNumberInfo(
        /*省份*/
        val province: String
        /*城市，(北京、上海、重庆、天津直辖市可能为空)*/
        , val city: String? = null
        /*区号，(部分记录可能为空)*/
        , val areaCode: String? = null
        /*邮编，(部分记录可能为空)*/
        , val zip: String? = null
        /*运营商*/
        , val company: String? = null
) {
    override fun toString(): String {
        return "PhoneNumberInfo{" +
                "\n province = $province" +
                "\n city = $city" +
                "\n areaCode = $areaCode" +
                "\n zip = $zip" +
                "\n company = $company" +
                "}"
    }
}