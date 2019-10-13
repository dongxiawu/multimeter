package personal.dongxia.android.business.Ip.model;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ip {

    @IntDef({
            AddressType.V4,
            AddressType.V6
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface AddressType {
        int V4 = 0;
        int V6 = 1;
    }

    private @AddressType int addressType;
    private String address; // ip 地址
    private String country;
    private String province;
    private String city;
    private String isp; // 运营商
}
