package com.example.nirma.math4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class Bisection extends AppCompatActivity {
    Button Solve,Reset;
    EditText eq,ans,a,b,acc,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection);
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
                double min=1,max=2,accuracy=0.001;
                int itr=10;
                inString=eq.getText().toString();
                if(a.getText().toString() != "")
                    min=Double.valueOf(a.getText().toString());
                if(b.getText().toString() != "")
                    max=Double.valueOf(b.getText().toString());
                System.out.println("upto b");

                if(no.getText().toString() != "")//trying to access null pointer :: error
                    itr=Integer.valueOf(no.getText().toString());
                System.out.println("upto no");
                if(acc.getText().toString() != "")
                    accuracy=Double.valueOf(acc.getText().toString());
                System.out.println("upto acc");
                final Function f=new Function("f(x)= " + inString);
                System.out.println("upto funal");
                BisectionSolver Solver=new BisectionSolver(accuracy);
                System.out.println("solver");

                UnivariateFunction fUnivariateFunction=new UnivariateFunction() {
                    @Override
                    public double value(double x) {
                        Expression expression=new Expression("f( "+x+" )",f);
                        //mXparser.consolePrintln(expression.calculate() + " " + x);
                        return (double)(expression.calculate());
                    }
                };

                double d=Solver.solve(itr, fUnivariateFunction, min,max);
                ans.setText(String.valueOf(d));
            }
        });

    }
}
