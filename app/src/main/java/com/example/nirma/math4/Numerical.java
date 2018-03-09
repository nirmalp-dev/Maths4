package com.example.nirma.math4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Numerical extends AppCompatActivity {
    Button bis,secant,fal,gausse,gaussj,gausss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical);
        bis=(Button)findViewById(R.id.bisection);
        secant=(Button)findViewById(R.id.secant);
        gausse=(Button)findViewById(R.id.gausselimination);
        fal=(Button)findViewById(R.id.fposi);
        gaussj=(Button)findViewById(R.id.gaussjacobi);
        gausss=(Button)findViewById(R.id.gaussseidel);
        bis=(Button)findViewById(R.id.bisection);
        bis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        Bisection.class);
                startActivity(myIntent);
            }
        });
        secant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        Secant.class);
                startActivity(myIntent);
            }
        });
        fal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        False_Position.class);
                startActivity(myIntent);
            }
        });
        gausss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        Gauss_Seidel.class);
                startActivity(myIntent);
            }
        });
        gaussj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        Gauss_Jacobi.class);
                startActivity(myIntent);
            }
        });
        gausse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Numerical.this,
                        Gauss_Elimination.class);
                startActivity(myIntent);
            }
        });
    }
}
