package com.example.worldinfoapplication.enitity;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomInterfaces {

    //create the interface

<<<<<<< HEAD
    //Json object interface
=======
    //json object response
>>>>>>> 7cdcd59ddf6b37e8fedd2e1e1704396976c4da0e
    public interface VolleyResponse {
      public   void onResponse(JSONObject object, String tag);
      public   void onError(VolleyError error, String tag);
    }

<<<<<<< HEAD
    //json array interface
=======
    //JSON array response
>>>>>>> 7cdcd59ddf6b37e8fedd2e1e1704396976c4da0e
    public interface VolleyJsonArrayResponse{
        public   void onJsonArrayResponse(JSONArray object, String tag);
        public   void onJsonArrayError(VolleyError error, String tag);
    }

<<<<<<< HEAD
    //String request interface
    public interface VolleyStringResponse{
        void onStringResponse(String response,String tag);
        void onStringResponseError(VolleyError error,String tag);
    }


=======
    public interface VolleyStringResponse{
        void onStringResponse(String object,String tag);
        void onStringError(VolleyError error,String tag);
    }

>>>>>>> 7cdcd59ddf6b37e8fedd2e1e1704396976c4da0e
}
