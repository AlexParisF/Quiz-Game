package edu.uoc.uocquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {
    GameController controller = GameController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        controller.addScoreObserver(new GameController.GameControllerScoreObserver() {
            @Override public void onScoreChanged() {
                TextView edTitle = findViewById(R.id.edTitle);
                edTitle.setText(GameController.getInstance().getPlayer()+
                        "- Score:"+GameController.getInstance().getScore()+
                        " - Level:"+GameController.getInstance().getLevel());
            }

        });
        controller.setLevel(0);
        controller.setScore(0);
        controller.initTest();
    }
}