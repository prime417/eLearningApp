package com.primenova.learningui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inner_rv1 extends Fragment {

    private RecyclerView recyclerView;
    private Adapter_rv1 recyclerviewAdapter;
    private List<Model_rv1> movieList;
    private String jsonArrayUrl = "http://velmm.com/apis/volley_array.json";
    private Gson gson;
    private ProgressDialog pDialog;
    private String TAG = "MainActivity";

    public static Inner_rv1 newInstance() {

        return new Inner_rv1();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_rv1, container, false);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        movieList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerviewAdapter = new Adapter_rv1(getContext());
        recyclerView.setAdapter(recyclerviewAdapter);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");

        Cache cache = MySingleton.getInstance(getContext()).getRequestQueue().getCache();
        Cache.Entry entry = cache.get(jsonArrayUrl);
        if (entry != null) {

            try {
                String data = new String(entry.data, "UTF-8");
                setData(data, true);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            callJsonArrayRequest();
        }
        return view;
    }


    private void callJsonArrayRequest() {
        // TODO Auto-generated method stub
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                jsonArrayUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                setData(response, false);
                dismissDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dismissDialog();
            }
        });
        // Adding request to request queue
        MySingleton.getInstance(getContext()).addToRequestQueue(strReq);
    }

    private void setData(String response, Boolean isCache) {
        //Log.d(TAG, response.toString());
        movieList = Arrays.asList(gson.fromJson(response, Model_rv1[].class));
        recyclerviewAdapter.setMovieList(movieList);
        if (isCache) {
            Toast.makeText(getContext(), "Loading from Volley Cache", Toast.LENGTH_SHORT).show();
            dismissDialog();
        }
    }

    private void dismissDialog() {
        // TODO Auto-generated method stub
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    private void showDialog() {
        // TODO Auto-generated method stub
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

}
