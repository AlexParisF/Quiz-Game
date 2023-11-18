package com.example.uocquizgame;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuizContent {
    public static List<Question> ITEMS = new ArrayList<Question>();
    public static String[] jsonFiles=new String[]{"questionsList1.json","questionsList2.json","questionsList3.json","questionsList4.json","questionsList5.json","questionsList6.json"};


    public static void loadQuestionsFromJSON(Context c, int number) {
        ITEMS.clear();
        String json = null;
        try {
            InputStream is =
                    c.getAssets().open(jsonFiles[number]);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray questionsList = jsonObject.getJSONArray("questions_list");
            for (int i = 0; i < questionsList.length(); i++) {
                JSONObject jsonQuestion = questionsList.getJSONObject(i);

                String title = jsonQuestion.getString("title");

                Question q=new Question(title);
                JSONArray answerList = jsonQuestion.getJSONArray("answers");
                for (int j=0;j<answerList.length();j++){
                    JSONObject jsonAnswer=answerList.getJSONObject(j);
                    String answer=jsonAnswer.getString("answer");
                    boolean isRight=jsonAnswer.getBoolean("isRight");
                    q.addPossibleAnswer(new Answer(answer,isRight));
                }
                ITEMS.add(q);
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

    }

    public static class Question {
        private String title;

        public List<Answer> getPossibleAnswers() {
            return possibleAnswers;
        }

        public void setPossibleAnswers(List<Answer> possibleAnswers) {
            this.possibleAnswers = possibleAnswers;
        }

        private List<Answer> possibleAnswers= new ArrayList<Answer>();

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void addPossibleAnswer(Answer a){
            possibleAnswers.add(a);
        }

        public Question(String title) {
            this.title = title;
        }


    }

    public static class Answer{
        String description;
        boolean isRightAnswer;
        public Answer(String description,boolean isRightAnswer){
            this.description=description;
            this.isRightAnswer=isRightAnswer;
        }
    }

}
