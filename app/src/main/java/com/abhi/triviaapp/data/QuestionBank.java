package com.abhi.triviaapp.data;

import android.util.Log;

import com.abhi.triviaapp.controller.AppController;
import com.abhi.triviaapp.model.Question;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

// QuestionBank is a class whuch is used to get the response(questions) from the api by ;
//A question.java class helps to create objects of these questions
//the response is in JSONarray type and each array has an internal array consisting of two thongs:-
  //  1 questions
  //  2 ans to above question
//when an object is created  , the final object is stored in ( ArrayList<Question> questionArrayList)
//and thus returned

public class QuestionBank {


    private final String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<Question> questionArrayList=new ArrayList<>();

    //returns a list of questions
    public List<Question> getQuestion(final AnswerListAsyncResponse callBack){


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                      for (int i=0;i<response.length();i++) {
                          Question question = new Question();

                          try {
                              question.setQues(response.getJSONArray(i).get(0).toString());
                              question.setAns(response.getJSONArray(i).getBoolean(1));

                              questionArrayList.add(question) ;
                            //    Log.d("HELLO","h"+question) ;





                          } catch (JSONException e) {
                              e.printStackTrace();
                          }

                      }
                      if(null!= callBack) callBack.processFinished(questionArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) ;
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList ;
    }



}
