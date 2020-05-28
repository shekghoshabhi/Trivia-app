package com.abhi.triviaapp.model;

//this class is created to create/construct the question and ans
// objects that we receive from the api
//instances to this class are created in QuestionBank.java class


import androidx.annotation.NonNull;

public class Question {
    private String Ques ;
    private boolean ans ;

    public Question() {
    }

    public Question(String ques, boolean ans) {
        this.Ques = Ques;
        this.ans = ans;
    }

    public String getQues() {
        return Ques;
    }

    public void setQues(String Ques) {
        this.Ques = Ques;
    }

    public boolean getAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Question{" +
                "Ques='" + Ques + '\'' +
                ", ans=" + ans +
                '}';
    }
}
