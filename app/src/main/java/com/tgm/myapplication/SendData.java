package com.tgm.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tgm.myapplication.databinding.ActivitySendDataBinding;

import java.util.HashMap;
import java.util.Map;

public class SendData extends AppCompatActivity {

    private ActivitySendDataBinding binding;
    private final String TAG = "SendData.class";
    String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendDataBtn.setOnClickListener(view -> {
            getDataFromUser();
            sendDataToServer();
        });

    }

    private void getDataFromUser() {
        name = binding.nameEditText.getText().toString();
        password = binding.passEditText.getText().toString();
    }

    private void sendDataToServer(){
        StringRequest request = new StringRequest(Request.Method.POST, Constant.sendUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                binding.nameEditText.setText("");
                binding.passEditText.setText("");

                Toast.makeText(SendData.this, response, Toast.LENGTH_LONG).show();
                Log.d(TAG, "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SendData.this, error.toString(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onResponse: "+error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("password", password);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}