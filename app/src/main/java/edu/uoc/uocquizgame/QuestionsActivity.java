package edu.uoc.uocquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class QuestionsActivity extends AppCompatActivity {
    GameController controller=GameController.getInstance();
    GameController.GameControllerQuestionObserver observer;
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
    }

    private void checkUnitPassed(){
        if(controller.getCurrentQuestion()==QuizContent.ITEMS.size() ){
// Current unit Test over
            if(QuizContent.ITEMS.size()==controller.getCorrectAnswersInCurrentTest()) {
// all questions are right
/* TO DO
  controller set unit state to UnitType.PASSED
  Set progress in txtProgress "END OF TEST: Total Right Answers: "+controller.getCorrectAnswersInCurrentTest()+" - TEST PASSED!"
  controller update level
  controller update score 10
    play sound cheer
  countDownTimer.cancel();
  set counter text to "FINISHED! WELL DONE!"
*/

            }
            else {
// all questions are not  right
/* TO DO
  controller set unit state to UnitType.FAILED
  Set progress in txtProgress "END OF TEST: Total Right Answers: "+controller.getCorrectAnswersInCurrentTest()+" - TEST FAILED!"
    play sound fail
  countDownTimer.cancel();
  set counter text to "FINISHED! TEST FAILED!"
*/

            }
        }
        else {
            // Current unit test is not over. change the Question
            // Set new title question in  txtQuestion.setText
            // Set progress in txtProgress
        }
    }
}