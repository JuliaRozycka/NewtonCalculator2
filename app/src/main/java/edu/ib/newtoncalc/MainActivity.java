package edu.ib.newtoncalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateXValueClick(View view) {
        Intent intent = new Intent(getApplicationContext(), XValueActivity.class);
        EditText editText = findViewById(R.id.editCoefficients);
        String text = editText.getText().toString();
        Polynomial coefficients = new Polynomial(text.split(","));
        intent.putExtra("Polynomial", coefficients);
        startActivity(intent);
    }

    public void calculateDerivative(View view) {
        Intent intent = new Intent(getApplicationContext(), DerivativeActivity.class);
        EditText editText = findViewById(R.id.editCoefficients);
        String text = editText.getText().toString();
        Polynomial coefficients = new Polynomial(text.split(","));
        intent.putExtra("Polynomial", coefficients);
        startActivity(intent);
    }

}