package edu.uoc.uocquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    GameController controller=GameController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin= (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            EditText edUserName = findViewById(R.id.edUserName);
            controller.setPlayer(edUserName.getText().toString());
            startActivity(intent);

        } });
    }
}