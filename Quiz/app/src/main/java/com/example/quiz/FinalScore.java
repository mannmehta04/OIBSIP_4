package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FinalScore extends AppCompatActivity {

    TextView userStatus, userScore;
    TextView correct, incorrect;

    int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        userStatus = findViewById(R.id.userStat);
        userScore = findViewById(R.id.userScore);

        correct = findViewById(R.id.correctAns);
        incorrect = findViewById(R.id.incorrectAns);

        Intent getScore = getIntent();

        finalScore = getScore.getIntExtra("finalScore", -1);

        displayStatus(finalScore);
    }

    public void displayStatus(int finalScore){
        userScore.setText(finalScore+" / 5");
        correct.setText("Correct Answers: "+finalScore);
        incorrect.setText("Incorrect Answers: "+(5-finalScore));

        if(finalScore == 5){
            userStatus.setText("Congratulations! You are Fluent in Java Android. Keep it Up!");
        } else if (finalScore < 5 && finalScore > 2) {
            userStatus.setText("Great Job! Keep improving your mistakes and you are doing good!");
        } else if (finalScore < 3 && finalScore > 0) {
            userStatus.setText("Keep Practicing, and it will help you to score further!");
        } else {
            userStatus.setText("You didn't prepared well. Did you?");
        }
    }

    public void resetQuiz(View v){
        Intent resetBack = new Intent(FinalScore.this, SplashScreen.class);
        startActivity(resetBack);
    }

    public void exit(View v){
        Toast.makeText(this, "Thank you for taking the Quiz!", Toast.LENGTH_LONG).show();
        this.finishAffinity();
    }
}