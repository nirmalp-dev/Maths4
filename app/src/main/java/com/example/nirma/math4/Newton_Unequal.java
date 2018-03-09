package com.example.nirma.math4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;

public class Newton_Unequal extends AppCompatActivity {
    Button solve,reset;
    EditText n,x,ans,X,Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton__unequal);
        solve=(Button)findViewById(R.id.solve);
        reset=(Button)findViewById(R.id.reset);
        n=(EditText)findViewById(R.id.n);
        x=(EditText)findViewById(R.id.x);
        X=(EditText)findViewById(R.id.X);
        Y=(EditText)findViewById(R.id.Y);
        ans=(EditText)findViewById(R.id.ans);

        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size=Integer.valueOf(n.getText().toString());
                String Xvalues[] =  X.getText().toString().split(" ");
                String Yvalues[] =  Y.getText().toString().split(" ");
                double val_x[]=new double[size];
                double val_y[]=new double[size];
                for(int i=0;i<size;i++)
                {
                    val_x[i]=Double.valueOf(Xvalues[i]);
                    val_y[i]=Double.valueOf(Yvalues[i]);
                }
                DividedDifferenceInterpolator dInterpolator=new DividedDifferenceInterpolator();

                double interpolationX=Double.valueOf(x.getText().toString());
                 UnivariateDifferentiableFunction function=dInterpolator.interpolate(val_x,val_y);
                double interpolatedY = function.value(interpolationX);
                ans.setText("f( " + interpolationX + " ) = " + interpolatedY + " ");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                X.setText("");
                Y.setText("");
                ans.setText("");
                x.setText("");
                n.setText("");
            }
        });

    }
}
