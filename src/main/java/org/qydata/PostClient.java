package org.qydata;

import com.alibaba.fastjson.JSON;

/**
 * Created by jonhn on 2016/11/11.
 */
public class PostClient {

    public static void main(String[] args) {
        RequestData requestData = new RequestData();
        // 待验证的持有人姓名
        requestData.setRealName("");
        // 待验证的身份证号
        requestData.setIdNo("");
        // 待验证的银行卡号
        requestData.setBankcard("");
        // 待验证的手机号
        requestData.setMobile("");
        // 账号
        requestData.setAuthId("");
        //请求时间戳
        requestData.setTs(System.currentTimeMillis());
        //请求标识
        requestData.setReqId(HashHelper.reqId());
        // 请求签名
        requestData.setSign(HashHelper.md5(requestData.getAuthId() + "此处输入密码" + requestData.getReqId() + requestData.getTs()));
        PostClient.getData(requestData);
    }

    public static String getData(RequestData requestData) {
        final String uri = "https://api.qydata.org:9000/bankcard/verify/4f";
        String json = JSON.toJSONString(requestData);
        try {
            return HttpClient.doPostSSL(uri, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
