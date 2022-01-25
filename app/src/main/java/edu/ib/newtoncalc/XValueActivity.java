package edu.ib.newtoncalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class XValueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvalue);
    }

    public void onClick(View view) {
        Polynomial polynomial = (Polynomial) getIntent().getSerializableExtra("Polynomial");
        final TextView textView = findViewById(R.id.resultXValue);
        EditText editText = findViewById(R.id.editXValue);
        double x = Double.parseDouble(editText.getText().toString());
        double result = polynomial.valueX(x);
        String stringdouble = Double.toString(result);
        textView.setText("Result: " + stringdouble);
    }
}