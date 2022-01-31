package edu.ib.newtoncalc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;
import java.util.Map;

public class XValueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvalue);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress netAddress = InetAddress.getByName("google.com");
            return !netAddress.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void onClick(View view) {
        Polynomial polynomial = (Polynomial) getIntent().getSerializableExtra("Polynomial");
        final TextView textView = findViewById(R.id.resultXValue);
        EditText editText = findViewById(R.id.editXValue);
        if (editText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter the X Value!", Toast.LENGTH_SHORT).show();
            return;
        }
        String x = editText.getText().toString();
        String result;
        if(isInternetAvailable()||isNetworkConnected()) {
            String url = "https://newton.vercel.app/api/v2/simplify/".concat(polynomial.substituteForValueX(x));
            RequestQueue queue = Volley.newRequestQueue(this);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                Map m = gson.fromJson(response, Map.class);
                textView.setText("Result: " + m.get("result").toString());
            }, error -> textView.setText("error"));

            queue.add(stringRequest);
        }
        else {
            result = Double.toString(polynomial.calculateForValueX(Double.parseDouble(x)));
            textView.setText("Result: " + result);
        }
    }
}