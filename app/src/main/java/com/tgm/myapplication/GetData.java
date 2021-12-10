package com.tgm.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tgm.myapplication.databinding.ActivityGetDataBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetData extends AppCompatActivity {

    private ActivityGetDataBinding binding;
    private List<Model> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDataFromServer();

    }

    private void getDataFromServer() {

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest request = new StringRequest(Request.Method.GET, Constant.getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: "+response);
                // converting json to java object
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
//                Model[] model = gson.fromJson(response, Model[].class);
//                for (Model model1 : model){
//                    list.add(model1);
//                } // -> convert whole array in list by using loop

                // list.addAll(Arrays.asList(model)); -> convert whole array in list

                // we can use both method
                Type userListType = new TypeToken<ArrayList<Model>>(){}.getType();
                ArrayList<Model> list = gson.fromJson(response, userListType);
                binding.recyclerView.setAdapter(new Adapter(list));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}