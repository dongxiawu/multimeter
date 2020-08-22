package personal.dongxia.android.business.Ip;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

//import lombok.Getter;
//import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import personal.dongxia.android.business.Ip.model.Ip;

public class IpServiceImpl implements IpService {
    private static final String APP_KEY ="73ad9c339d158e24ab4f9394f79fb80c";
    private static final String HOST = "http://apis.juhe.cn/ip/ipNew";

    @Override
    public Ip queryIp(String address) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(HOST + "?ip=" + address + "&key=" + APP_KEY)
                .build();
        Response response = null;
        QueryResult result = null;
        try {
            response = client.newCall(request).execute();
            String stringResult = response.body().string();
            result  = JSONObject.parseObject(stringResult, QueryResult.class);
            //result.setAddress(address);
        }catch (IOException e) {
            // todo
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return map(result);
    }

    private Ip map(QueryResult queryResult) {
        Ip ip = new Ip();
        if (queryResult == null || queryResult.result == null || !"200".equals(queryResult.resultcode)) {
            return null;
        }
        //ip.setAddress(queryResult.address);
        //ip.setCity(queryResult.result.City);
        //ip.setCountry(queryResult.result.Country);
        //ip.setProvince(queryResult.result.Province);
        //ip.setIsp(queryResult.result.Isp);
        return ip;
    }

    //@Getter
    //@Setter
    private static class QueryResult {
        private String resultcode;
        private String address;
        private String reason;
        private Result result;
        //@Getter
        //@Setter
        private class Result {
            public String Country;
            public String Province;
            public String City;
            public String Isp;
        }
        private int error_code;
    }
}
