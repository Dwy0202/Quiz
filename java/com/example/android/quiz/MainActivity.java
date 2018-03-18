package com.example.android.quiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int finalScore = 0;
    String playersName = null;
    private RadioButton question_1a, question_2d, question_3a, question_4c, question_6b;
    private CheckBox question_5a, question_5b, question_5c, question_5d;
    private EditText name, question_7a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    //All Radio buttons, checkboxes, and edit text objects.
    private void findViews() {
        name = findViewById(R.id.name);
        question_7a = findViewById(R.id.question_7a);
        question_1a = findViewById(R.id.question_1a);
        question_2d = findViewById(R.id.question_2d);
        question_3a = findViewById(R.id.question_3a);
        question_4c = findViewById(R.id.question_4c);
        question_5a = findViewById(R.id.question_5a);
        question_5b = findViewById(R.id.question_5b);
        question_5c = findViewById(R.id.question_5c);
        question_5d = findViewById(R.id.question_5d);
        question_6b = findViewById(R.id.question_6b);
    }

    //Method to handle Edit text field for name
    public String name() {
        playersName = name.getText().toString();
        return playersName;
    }

    //Question 1
    public int questionOne() {
        if (question_1a.isChecked()) {
            return 1;
        } else
            return 0;
    }

    //Question 2
    public int questionTwo() {
        if (question_2d.isChecked()) {
            return 1;
        } else
            return 0;
    }

    //Question 3
    public int questionThree() {
        if (question_3a.isChecked()) {
            return 1;
        } else
            return 0;
    }

    //Question 4
    public int questionFour() {
        if (question_4c.isChecked()) {
            return 1;
        } else
            return 0;
    }

    //Question 5
    //Consist of checkboxes so must include which group selected equals correct answer
    public int questionFive() {
        if (question_5a.isChecked() && question_5b.isChecked() && question_5c.isChecked() && !question_5d.isChecked()) {
            return 1;
        } else
            return 0;
    }

    //Question 6
    public int questionSix() {
        if (question_6b.isChecked()) {
            return 1;
        } else
            return 0;
    }

    //Question 7
    //Edit text field and compares input with string
    public int questionSeven() {
        if (question_7a.getText().toString().equals("Hertz") || question_7a.getText().toString().equals("hertz")) {
            return 1;
        } else
            return 0;
    }


    //Adds up all values returned from Question 1-7
    public void totalScore(View v) {
        int scoreOne = questionOne();
        int scoreTwo = questionTwo();
        int scoreThree = questionThree();
        int scoreFour = questionFour();
        int scoreFive = questionFive();
        int scoreSix = questionSix();
        int scoreSeven = questionSeven();
        finalScore = scoreOne + scoreTwo + scoreThree + scoreFour + scoreFive + scoreSix + scoreSeven;
        String message = returnMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    //Email results intent
    //includes error handling if score is less than 0
    public void emailButton(View view) {
        if (name.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter a name to receive final score", Toast.LENGTH_SHORT).show();
        } else if (finalScore >= 1) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Quiz Score for " + playersName);
            emailIntent.putExtra(Intent.EXTRA_TEXT, returnMessage());
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        } else
            Toast.makeText(this, name() + ", You don't want to email these results", Toast.LENGTH_SHORT).show();
    }

    //Display message's depending on finalScore
    public String returnMessage() {
        String quizBodyMessage = (name() + ", ");

        if (name.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter a name to receive final score", Toast.LENGTH_SHORT).show();
        } else if (finalScore == 7) {
            quizBodyMessage += ("You got a perfect score!!");
        } else if (finalScore > 5) {
            quizBodyMessage += ("Very close, You got  " + finalScore + "/7");
        } else if (finalScore >= 4) {
            quizBodyMessage += ("You should study more. Your score is  " + finalScore + "/7");
        } else if (finalScore <= 3) {
            quizBodyMessage += ("Horrible! Are you sleeping or studying? You score is  " + finalScore + "/7");

        }
        return quizBodyMessage;

    }

}
