package com.fadhlanhawali.alirin.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fadhlanhawali.alirin.Network.VolleyNetwork;
import com.fadhlanhawali.alirin.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatisticFragment extends Fragment {

    private String TAG = StatisticFragment.class.getSimpleName();
    GraphView graph;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);
        graph = (GraphView) v.findViewById(R.id.graph);

        readData();
        return v;
    }

    void readData(){
        JSONObject body = new JSONObject();
        String url = "http://167.99.75.26:5008/report/";

        //POST REQUEST
        VolleyNetwork request = new VolleyNetwork(url, body, TAG);
        request.getRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Toast.makeText(getContext(), result.toString(), Toast.LENGTH_SHORT).show();
                    JSONArray array = result.getJSONArray("data");
                    DataPoint[] points = new DataPoint[array.length()];
                    for(int i = 0;i<array.length();i++){

                        JSONObject detail = array.getJSONObject(i);
                        Long timestamp = detail.getLong("timestamp");
                        int humidity = detail.getInt("humidity");
                        Log.d("Result : ",detail.toString());
                        points[i] = new DataPoint(i,humidity);

                    }
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

                    graph.getViewport().setScalable(true);
                    graph.getViewport().setScalableY(true);
                    graph.addSeries(series);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        },getContext());
    }
}