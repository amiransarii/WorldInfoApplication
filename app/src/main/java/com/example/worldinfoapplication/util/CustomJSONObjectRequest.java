package com.example.worldinfoapplication.util;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.worldinfoapplication.enitity.CustomInterfaces;
import org.json.JSONObject;

import java.util.Map;

public class CustomJSONObjectRequest implements  Response.Listener<JSONObject>, Response.ErrorListener {
    private static String tag=CustomJSONObjectRequest.class.getSimpleName();
    private  LogUtils log=new LogUtils(tag,true);
    private CustomInterfaces.VolleyResponse volleyResponse;
    private JsonObjectRequest jsonObjectRequest;

    public CustomJSONObjectRequest(int method, String url, JSONObject jsonObject, String tag,
                                   CustomInterfaces.VolleyResponse volleyResponse) {
        this.volleyResponse = volleyResponse;
        this.tag= tag;
        jsonObjectRequest = new JsonObjectRequest(method, url, jsonObject, this, this);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        volleyResponse.onError(error, tag);

    }

    @Override
    public void onResponse(JSONObject response) {
        volleyResponse.onResponse(response, tag);
    }



    public JsonObjectRequest getJsonObjectRequest() {
        return jsonObjectRequest;
    }
}
