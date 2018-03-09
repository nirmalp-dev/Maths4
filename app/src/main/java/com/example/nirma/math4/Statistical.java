package com.example.nirma.math4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Statistical extends AppCompatActivity {
    Button nes,nues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical);
        nes=(Button)findViewById(R.id.nes);
        nues=(Button)findViewById(R.id.nues);
        nes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Statistical.this,
                        Newton_Equal.class);
                startActivity(myIntent);
            }
        });
        nues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Statistical.this,
                        Newton_Unequal.class);
                startActivity(myIntent);
            }
        });
    }
}
