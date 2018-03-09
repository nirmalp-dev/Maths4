package com.example.nirma.math4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Numerical extends AppCompatActivity {
    Button bis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical);
        bis=(Button)findViewById(R.id.bisection);
        bis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        Bisection.class);
                startActivity(myIntent);
            }
        });
    }
}
