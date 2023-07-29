package com.example.calculator;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView Result_tv,Solution_tv;
    MaterialButton ButtonC,ButtonBracketOpen,ButtonBracketClose;
    MaterialButton Buttondivide,ButtonMultiply,ButtonPlus,ButtonMinus,ButtonEqual;
    MaterialButton Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9;
    MaterialButton ButtonAC,ButtonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result_tv = findViewById(R.id.result_tv);
        Solution_tv = findViewById(R.id.solution_tv);

        assignId(ButtonC,R.id.button_C);
        assignId(ButtonBracketOpen,R.id.button_openbracket);
        assignId(ButtonBracketClose,R.id.button_closebracket);
        assignId(Buttondivide,R.id.button_divide);
        assignId(ButtonMultiply,R.id.button_multiply);
        assignId(ButtonPlus,R.id.button_Addition);
        assignId(ButtonMinus,R.id.button_minus);
        assignId(ButtonEqual,R.id.button_Equal);
        assignId(Button0,R.id.button_0);
        assignId(Button1,R.id.button_1);
        assignId(Button2,R.id.button_2);
        assignId(Button3,R.id.button_3);
        assignId(Button4,R.id.button_4);
        assignId(Button5,R.id.button_5);
        assignId(Button6,R.id.button_6);
        assignId(Button7,R.id.button_7);
        assignId(Button8,R.id.button_8);
        assignId(Button9,R.id.button_9);
        assignId(ButtonAC,R.id.button_ac);
        assignId(ButtonDot,R.id.button_dot);



    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = Solution_tv.getText().toString();

        if (buttonText.equals("AC")){
            Solution_tv.setText("");
            Result_tv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            Solution_tv.setText(Result_tv.getText());
            return;
        }
        if(buttonText.equals("C")){

                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate=dataToCalculate+buttonText;

        }

        Solution_tv.setText(dataToCalculate);
        Solution_tv.setMovementMethod(new ScrollingMovementMethod());

        String FinalResult = getResult(dataToCalculate);

        if(!FinalResult.equals("err")){
            Result_tv.setText(FinalResult);
        }

    }

    String getResult(String data){
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
           String Final_Result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           Final_Result = Final_Result.replace(".0","");
           return Final_Result;
        }catch (Exception e){
            return "err";
        }
    }
}