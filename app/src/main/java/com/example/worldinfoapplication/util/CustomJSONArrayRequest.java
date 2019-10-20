package com.example.worldinfoapplication.util;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.worldinfoapplication.enitity.CustomInterfaces;

import org.json.JSONArray;

public class CustomJSONArrayRequest implements  Response.Listener<JSONArray>, Response.ErrorListener {
    private CustomInterfaces.VolleyJsonArrayResponse volleyResponse;
    private static String tag= CustomJSONArrayRequest.class.getSimpleName();
    private JsonArrayRequest jsonArrayRequest;

    public CustomJSONArrayRequest(String url, String tag,
                                  CustomInterfaces.VolleyJsonArrayResponse volleyResponse) {
        this.volleyResponse = volleyResponse;
        this.tag= tag;

        jsonArrayRequest = new JsonArrayRequest(url,this,this);
                //new JsonArrayRequest(url, volleyResponse);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        volleyResponse.onJsonArrayError(error, tag);
    }

    @Override
    public void onResponse(JSONArray response) {
        volleyResponse.onJsonArrayResponse(response,tag);
    }

    public JsonArrayRequest getJsonArrayRequest() {
        return jsonArrayRequest;
    }
}
