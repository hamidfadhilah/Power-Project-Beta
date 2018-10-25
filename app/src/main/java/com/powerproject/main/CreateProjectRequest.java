package com.powerproject.main;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by COEG-IN on 3/13/2017.
 */

public class CreateProjectRequest extends StringRequest {
    private static final String CREATE_REQUEST_URL = "http://coegin.com/powerproject/createproject.php";
    private Map<String, String> params;

    public CreateProjectRequest(String projectName, String projectDesc,
                                String start, String finish, String owner, String manager,
                                int member,   Response.Listener<String> listener) {
        super(Method.POST, CREATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("project", projectName);
        params.put("deskripsi", projectDesc);
        params.put("mulai", start);
        params.put("berakhir", finish);
        params.put("owner", owner);
        params.put("manager", manager);
        params.put("member", member + "");
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
