package com.powerproject.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by COEG-IN on 3/5/2017.
 */

public class ForgetRequest extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://coegin.com/powerproject/forgotpassword.php";
    private Map<String, String> params;

    public ForgetRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);

    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
