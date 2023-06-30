package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] questions = {
            "What is the purpose of the AndroidManifest.xml file?",
            "Which component is responsible for displaying notifications in Android?",
            "What is the purpose of the 'findViewById()' method in Android?",
            "Which method is called when an activity is first created?",
            "What is the purpose of an Intent in Android?"
    };

    String[][] options = {
//            {"0","1","2","3"},
//            {"0","1","2","3"},
//            {"0","1","2","3"},
//            {"0","1","2","3"},
//            {"0","1","2","3"}
            {"(a) To define the layout of an activity", "(b) To store data persistently", "(c) To handle button clicks", "(d) To declare app components and permissions"},
            {"(a) Activity", "(b) Fragment", "(c) NotificationManager", "(d) BroadcastReceiver"},
            {"(a) To create a new view instance", "(b) To set the content view of an activity", "(c) To handle button clicks", "(d) To find and reference a view from the layout XML"},
            {"(a) 'onCreate()'", "(b) 'onResume()'", "(c) 'onStart()'", "(d) 'onStop()'"},
            {"(a) To handle user input", "(b) To communicate between components and start new activities", "(c) To store data persistently","(d) To perform network operations"}
    };

    int[] correctAnswers = {3, 2, 3, 0, 1}; // Index of the correct answer for each question

    int currentQuestion = 0;
    int score = 0;
    boolean answerChecked = false;

    TextView questionTextView;
    TextView answerStatus;

    RadioButton option1RadioButton;
    RadioButton option2RadioButton;
    RadioButton option3RadioButton;
    RadioButton option4RadioButton;
    RadioGroup groupOptions;
    ColorStateList defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.ques);
        answerStatus = findViewById(R.id.ans);

        groupOptions = findViewById(R.id.options);

        option1RadioButton = findViewById(R.id.option1_radio_button);
        option2RadioButton = findViewById(R.id.option2_radio_button);
        option3RadioButton = findViewById(R.id.option3_radio_button);
        option4RadioButton = findViewById(R.id.option4_radio_button);

        defaultColor =  option1RadioButton.getTextColors();

        showQuestion();

        groupOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!answerChecked) {
                    answerChecked = true;

                    checkAnswer();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentQuestion++;

                            if (currentQuestion < questions.length) {
                                showQuestion();
                            } else {
                                showScore();
                            }

                            answerChecked = false;
                            enableRadioButtons(true);
                        }
                    }, 1000);

                    enableRadioButtons(false);
                }
            }
        });
    }

    private void showQuestion() {
        option1RadioButton.setTextColor(defaultColor);
        option2RadioButton.setTextColor(defaultColor);
        option3RadioButton.setTextColor(defaultColor);
        option4RadioButton.setTextColor(defaultColor);

        questionTextView.setText(questions[currentQuestion]);
        option1RadioButton.setText(options[currentQuestion][0]);
        option2RadioButton.setText(options[currentQuestion][1]);
        option3RadioButton.setText(options[currentQuestion][2]);
        option4RadioButton.setText(options[currentQuestion][3]);

        option1RadioButton.setChecked(false);
        option2RadioButton.setChecked(false);
        option3RadioButton.setChecked(false);
        option4RadioButton.setChecked(false);

        answerStatus.setText("");
    }

    void enableRadioButtons(boolean enable) {
        option1RadioButton.setEnabled(enable);
        option2RadioButton.setEnabled(enable);
        option3RadioButton.setEnabled(enable);
        option4RadioButton.setEnabled(enable);
    }

    private void checkAnswer() {
        int selectedAnswer = -1;

        if (option1RadioButton.isChecked()) {
            selectedAnswer = 0;
        } else if (option2RadioButton.isChecked()) {
            selectedAnswer = 1;
        } else if (option3RadioButton.isChecked()) {
            selectedAnswer = 2;
        } else if (option4RadioButton.isChecked()) {
            selectedAnswer = 3;
        }

        if (selectedAnswer == correctAnswers[currentQuestion]) {
            score++;
            answerStatus.setText("Correct Answer!");
            answerStatus.setTextColor(Color.parseColor("#00FF00"));
        }
        else if(selectedAnswer != correctAnswers[currentQuestion]){
            if(correctAnswers[currentQuestion] == 0){
                option1RadioButton.setTextColor(Color.parseColor("#00FF00"));
            } else if (correctAnswers[currentQuestion] == 1) {
                option2RadioButton.setTextColor(Color.parseColor("#00FF00"));
            } else if (correctAnswers[currentQuestion] == 2) {
                option3RadioButton.setTextColor(Color.parseColor("#00FF00"));
            } else if (correctAnswers[currentQuestion] == 3) {
                option4RadioButton.setTextColor(Color.parseColor("#00FF00"));
            }
            answerStatus.setText("Incorrect Answer!");
            answerStatus.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    private void showScore() {
        Intent finalScreen = new Intent(MainActivity.this, FinalScore.class);
        finalScreen.putExtra("finalScore", score);
        startActivity(finalScreen);
        finish();
    }
}