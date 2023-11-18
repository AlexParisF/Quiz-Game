package edu.uoc.uocquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import edu.uoc.uocquizgame.placeholder.PlaceholderContent;

public class QuestionsActivity extends AppCompatActivity {
    GameController controller=GameController.getInstance();
    GameController.GameControllerQuestionObserver observer;

    boolean on_finish = false;
    CountDownTimer contador = new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            TextView contador = findViewById(R.id.contador);
            contador.setText(millisUntilFinished / 1000 + " seconds left");
        }

        public void onFinish() {
            on_finish = true;
            controller.setCurrentQuestion(QuizContent.ITEMS.size());
            checkUnitPassed();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int quizNumber = intent.getIntExtra("quiz_number", 1);
        QuizContent.loadQuestionsFromJSON(this,quizNumber);
        controller.initTest();
        setContentView(R.layout.activity_questions);
        observer=new GameController.GameControllerQuestionObserver() {
            @Override public void onQuestionChanged() {
                checkUnitPassed();
            }
        };
        controller.addQuestionObserver(observer);
        controller.setCurrentUnit(quizNumber);

        TextView unitTest = findViewById(R.id.unitTest);
        unitTest.setText(PlaceholderContent.UNITS.get(controller.getCurrentUnit()).description);
        ImageView imageUnit = findViewById(R.id.imageUnit);
        imageUnit.setImageResource(PlaceholderContent.UNITS.get(controller.getCurrentUnit()).icon);
        TextView question = findViewById(R.id.question);
        question.setText(QuizContent.ITEMS.get(controller.getCurrentQuestion()).getTitle());
        TextView progress = findViewById(R.id.progress);
        progress.setText("Question "+(controller.getCurrentQuestion()+1)+"/"+QuizContent.ITEMS.size()+" - Right Answers: "+controller.getCorrectAnswersInCurrentTest());
        contador.start();
    }
    private void play(int resource){
        MediaPlayer mp=MediaPlayer.create(getApplicationContext(),resource);
        mp.start();
    }
    private void checkUnitPassed(){
        if(controller.getCurrentQuestion()==QuizContent.ITEMS.size() ){
            // Current unit Test over
            if(QuizContent.ITEMS.size()==controller.getCorrectAnswersInCurrentTest()) {
                // all questions are right
                controller.changeUnitState(GameController.UnitType.PASSED, controller.getCurrentUnit());
                TextView progress = findViewById(R.id.progress);
                progress.setText("END OF TEST: Total Right Answers: "+controller.getCorrectAnswersInCurrentTest()+" - TEST PASSED!");
                controller.updateLevel();
                controller.updateScore(10);
                contador.cancel();
                TextView contador = findViewById(R.id.contador);
                contador.setText("FINISHED! WELL DONE!");
                play(R.raw.cheer);            }
            else {
                // all questions are not  right
                controller.changeUnitState(GameController.UnitType.FAILED, controller.getCurrentUnit());
                TextView progress = findViewById(R.id.progress);
                progress.setText("END OF TEST: Total Right Answers: " + controller.getCorrectAnswersInCurrentTest() + " - TEST FAILED!");
                contador.cancel();
                TextView contador = findViewById(R.id.contador);
                contador.setText("FINISHED! TEST FAILED!");
                if (!on_finish) {
                    play(R.raw.fail);
                }
                else play(R.raw.gong);
            }
        }

        else {
            // Current unit test is not over. change the Question
            TextView question = findViewById(R.id.question);
            question.setText(QuizContent.ITEMS.get(controller.getCurrentQuestion()).getTitle());
            TextView progress = findViewById(R.id.progress);
            progress.setText("Question "+(controller.getCurrentQuestion()+1)+"/"+QuizContent.ITEMS.size()+" - Right Answers: "+controller.getCorrectAnswersInCurrentTest());

        }
    }
}