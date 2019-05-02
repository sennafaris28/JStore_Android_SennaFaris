package com.example.jstore_android_sennafaris;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MenuRequest extends StringRequest {

    private static final String Main_URL = "";
    private Map<String, String> params;

    public MenuRequest(
            Item items,
            Response.Listener<String> listener
    ){
        super(Method.GET, Main_URL, listener, null);
        params
    }
}
