package com.example.counts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText numberView;
    TextView operationView;
    String lastOperation = "=";
    TextView resultView;
    Double operand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operand = null;
        resultView =(TextView) findViewById(R.id.resultView);
        numberView = (EditText) findViewById(R.id.numberView);
        operationView = (TextView) findViewById(R.id.operationView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        resultView.setText(operand.toString());
        operationView.setText(lastOperation);
    }
    private void operationCommit(Double number){

        if(operand ==null){
            operand = number;
        }
        else {
            if (lastOperation.equals("=")) {
                operand = 0.0;
            }

            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        if(operand == -0.0){
            operand = 0.0;
        }
        resultView.setText(operand.toString());
        numberView.setText("");
    }

    public void operationCreate(View view){

        Button button = (Button)view;
        String op = button.getText().toString();
        String number = numberView.getText().toString();
        if(number.length()>0){
            try{
                operationCommit(Double.valueOf(number));
            }catch (NumberFormatException ex){
                numberView.setText("");
            }
        }
        lastOperation = op;
        operationView.setText(lastOperation);
    }

}