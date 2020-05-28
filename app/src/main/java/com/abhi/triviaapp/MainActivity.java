package com.abhi.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.triviaapp.controller.AppController;
import com.abhi.triviaapp.data.AnswerListAsyncResponse;
import com.abhi.triviaapp.data.QuestionBank;
import com.abhi.triviaapp.model.Question;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button TRUE_button ;
private Button FALSE_button;
private ImageButton NEXT_button;
private ImageButton PREV_button;
private TextView QUES_text ;
private TextView COUNTER_text ;
private int counter=0;
private List<Question> questionList  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TRUE_button=findViewById(R.id.true_button);
        FALSE_button=findViewById(R.id.false_button);
        NEXT_button=findViewById(R.id.next_button);
        PREV_button=findViewById(R.id.prev_button);
        QUES_text=findViewById(R.id.ques_text);
        COUNTER_text=findViewById(R.id.score_text) ;

        TRUE_button.setOnClickListener(this);
        FALSE_button.setOnClickListener(this);
        NEXT_button.setOnClickListener(this);
        PREV_button.setOnClickListener(this);


            questionList =  new  QuestionBank().getQuestion(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {

                    QUES_text.setText(questionArrayList.get(counter).getQues());

                    COUNTER_text.setText(counter+"/"+questionArrayList.size());



            }
        });
          // Log.d("HELLO", "onCreate"+questionList)  ;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.prev_button:
                if(counter>0){
                    counter=(counter-1)%questionList.size();
                    update();
                }
                else{
                    counter=questionList.size()-1 ;
                    update();
                }
                break;

            case R.id.next_button:
                counter=(counter+1)%questionList.size();
                update() ;

            case R.id.true_button:
                CHECK_ANS(true) ;
                update();
                break;
            case R.id.false_button:
                CHECK_ANS(false) ;
                update();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }


    }

    private void CHECK_ANS(boolean b) {

        boolean ans = questionList.get(counter).getAns() ;
        if(b==ans){
            Toast.makeText(this,"correct!!!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Wrong!!!",Toast.LENGTH_LONG).show();
        }

    }

    private void update() {
        String Ques= questionList.get(counter).getQues().toString();
        QUES_text.setText(Ques);
        COUNTER_text.setText(counter+"/"+questionList.size());

    }
}
