package com.example.nirma.math4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.solvers.RegulaFalsiSolver;
import org.apache.commons.math3.analysis.solvers.SecantSolver;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class Secant extends AppCompatActivity {
    Button Solve,Reset;
    EditText eq,ans,a,b,acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secant);
        Solve=(Button)findViewById(R.id.solve);
        Reset=(Button)findViewById(R.id.reset);
        a=(EditText)findViewById(R.id.a);
        b=(EditText)findViewById(R.id.b);
        acc=(EditText)findViewById(R.id.acc);
        eq=(EditText)findViewById(R.id.input);
        ans=(EditText)findViewById(R.id.ans);

        Solve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String inString;
                double min=1,max=2,accuracy=0.001;
                int itr=10;
                inString=eq.getText().toString();
                if(a.getText().toString() != "")
                    min=Double.valueOf(a.getText().toString());
                if(b.getText().toString() != "")
                    max=Double.valueOf(b.getText().toString());
                if(acc.getText().toString() != "")
                    accuracy=Double.valueOf(acc.getText().toString());
                final Function f=new Function("f(x)= " + inString);
                SecantSolver Solver=new SecantSolver(accuracy);

                UnivariateFunction fUnivariateFunction=new UnivariateFunction() {
                    @Override
                    public double value(double x) {
                        Expression expression=new Expression("f( "+x+" )",f);
                        return (double)(expression.calculate());
                    }
                };

                double d=Solver.solve(itr, fUnivariateFunction, min,max);
                ans.setText(String.valueOf(d));
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                a.setText("");
                b.setText("");
                acc.setText("");
                eq.setText("");
                ans.setText("");
            }
        });

    }
}
