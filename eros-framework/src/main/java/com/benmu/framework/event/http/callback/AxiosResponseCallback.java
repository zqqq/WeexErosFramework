package com.benmu.framework.event.http.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.benmu.framework.http.okhttp.callback.Callback;
import com.benmu.framework.model.AxiosResultBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by Carry on 2018/5/28.
 */

public abstract class AxiosResponseCallback extends Callback<AxiosResultBean> {


    @Override
    public AxiosResultBean parseNetworkResponse(Response response, int id) throws Exception {
        int code = response.code();
        String responseBody = response.body().string();
        Map<String, List<String>> responseHeader = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap()
                .entrySet()) {
            responseHeader.put(entry.getKey(), entry.getValue());
        }
        try {
            return new AxiosResultBean(code, "", JSON.parse(responseBody), responseHeader);
        } catch (JSONException e) {
            e.printStackTrace();
            return new AxiosResultBean(-1, "json解析错误", null, responseHeader);
        }

    }
}