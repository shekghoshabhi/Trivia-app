package com.abhi.triviaapp.data;

import com.abhi.triviaapp.model.Question;

import java.util.ArrayList;

public  interface AnswerListAsyncResponse {

    void processFinished(ArrayList<Question> questionArrayList);

}
