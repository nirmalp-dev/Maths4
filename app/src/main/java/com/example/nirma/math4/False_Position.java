package com.example.nirma.math4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.solvers.RegulaFalsiSolver;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class False_Position extends AppCompatActivity {
    Button Solve,Reset;
    EditText eq,ans,a,b,acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false__position);
        Solve=(Button)findViewById(R.id.solve);
        Reset=(Button)findViewById(R.id.reset);
        a=(EditText)findViewById(R.id.a);
        b=(EditText)findViewById(R.id.b);
        acc=(EditText)findViewById(R.id.acc);
        eq=(EditText)findViewById(R.id.input);
        ans=(EditText)findViewById(R.id.ans);

        Solve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                String inString;
                double min = 1, max = 2, accuracy = 0.001;
                int itr = 1000;
                inString = eq.getText().toString();
                if (a.getText().toString() != "")
                    min = Double.valueOf(a.getText().toString());
                if (b.getText().toString() != "")
                    max = Double.valueOf(b.getText().toString());
                if (acc.getText().toString() != "")
                    accuracy = Double.valueOf(acc.getText().toString());

                if (inString.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Equation", Toast.LENGTH_SHORT).show();
                    eq.setFocusable(true);
                }
                else {

                    final Function f = new Function("f(x)= " + inString);
                    RegulaFalsiSolver Solver = new RegulaFalsiSolver(accuracy);

                    UnivariateFunction fUnivariateFunction = new UnivariateFunction() {
                        @Override
                        public double value(double x) {
                            Expression expression = new Expression("f( " + x + " )", f);
                            return (double) (expression.calculate());
                        }
                    };

                    double d = Solver.solve(itr, fUnivariateFunction, min, max);
                    ans.setText(String.valueOf(d));
                }
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
