package com.tgm.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData extends AppCompatActivity {

    private ActivityGetDataBinding binding;
    ArrayList<Model> list = new ArrayList<>();
    private final String TAG = "GetData.class";
    private Adapter adapter;

    // TODO: delete data + edit data
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
                Log.d("TAG", "onResponse: " + response);

                // converting json to java object
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
//                Model[] model = gson.fromJson(response, Model[].class);
//                for (Model model1 : model){
//                    list.add(model1);
//                } // -> convert whole array in list by using loop

                // list.addAll(Arrays.asList(model)); -> convert whole array in list

                // we can use both method
                Type userListType = new TypeToken<ArrayList<Model>>() {
                }.getType();
                list = gson.fromJson(response, userListType);
                adapter = new Adapter(list, (id, position) -> {
                    deleteRecord(id, position);
                });
                binding.recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void deleteRecord(int id, int position) {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.deleteUrl, response -> {
            Log.d(TAG, "deleteRecord: " + response);
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            removeItem(position);
        }, error -> {
            Log.d(TAG, "deleteRecord: " + error.getMessage());
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", String.valueOf(id));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void removeItem(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }
}