package edu.ib.newtoncalc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class DerivativeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative);
        Polynomial polynomial = (Polynomial) getIntent().getSerializableExtra("Polynomial");
        TextView textView = findViewById(R.id.derivative_result);
        String url = "https://newton.vercel.app/api/v2/derive/".concat(polynomial.toString());

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Map m = gson.fromJson(response, Map.class);
            textView.setText("Derivative of polynomial: " + m.get("result").toString());
        }, error -> textView.setText("error"));

        queue.add(stringRequest);
    }
}