package com.fadhlanhawali.alirin.Network;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fadhlanhawali.alirin.AppController;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyNetwork {
    private String url;
    private String TAG;
    private JSONObject id;
    private String token;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getId() {
        return id;
    }

    public void setId(JSONObject id) {
        this.id = id;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VolleyNetwork(String url, JSONObject id, String TAG)
    {
        this.id = id;
        this.url = url;
        this.TAG = TAG;
    }


    public VolleyNetwork(String url, String TAG, String token)
    {
        this.url = url;
        this.TAG = TAG;
        this.token = token;
    }



    public void postRequest(final VolleyCallback callback, Context context) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,getUrl(), getId(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                NetworkResponse networkResponse = error.networkResponse;
                callback.onError(error);
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("x-meeting-token",getToken());
                return headers;
            }
        };

        AppController.getInstance(context).addToRequestQueue(jsonObjReq);


    }

    public void getRequest(final VolleyCallback callback,Context context)
    {
        JSONObject duata = new JSONObject();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,url,duata,
                new Response.Listener<JSONObject>() {
                    @Override

                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                callback.onError(error);
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("x-meeting-token",getToken());
                return headers;
            }
        };

        AppController.getInstance(context).addToRequestQueue(jsonObjReq);

    }

    public interface VolleyCallback{
        void onSuccess(JSONObject result);
        void onError(VolleyError error);
    }

}
