package com.example.worldinfoapplication.util;

<<<<<<< HEAD
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CustomStringRequest  {
    private String mResponse; //to get response

    public  String getStringResponse(String url, final String value){

         StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         mResponse = response;
                     }
                 },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(ActivityName.this, error.toString(), Toast.LENGTH_LONG).show();
                     }
                 }) {
             @Override
             protected Map<String, String> getParams() throws AuthFailureError {
                 HashMap<String, String> hashMap = new HashMap<String, String>();
                 hashMap.put("value", value);
                 return hashMap;
             }
         };

          WorldInfoApplication.getInstance().addToRequestQueue(stringRequest);
          final RequestQueue requestQueue=  WorldInfoApplication.getInstance().getRequestQueue();
          requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
             @Override
             public void onRequestFinished(Request<Object> request) {
                 requestQueue.getCache().clear();
             }
         });


    return mResponse;
     }
=======
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.worldinfoapplication.enitity.CustomInterfaces;

public class CustomStringRequest {

    private static String tag=CustomStringRequest.class.getSimpleName();
    private  LogUtils log=new LogUtils(tag,true);
  //  private CustomInterfaces.VolleyStringResponse
    private JsonObjectRequest jsonObjectRequest;
>>>>>>> 7cdcd59ddf6b37e8fedd2e1e1704396976c4da0e

}
