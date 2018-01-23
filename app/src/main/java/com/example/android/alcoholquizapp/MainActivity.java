package com.example.android.alcoholquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox answerVodka, answerGin, answerStirred, answerShaken;
    RadioButton answerSugarCane, answerPotatoes, answerWheat, answerCoconut;
    EditText userTypedAnswer;
    Button submitButton ,resetButton, showAnswer1Button, showAnswer2Button, showAnswer3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void submitAnswers(View view) {
        answerPotatoes = findViewById(R.id.check_potatoes);
        answerWheat = findViewById(R.id.check_wheat);
        answerCoconut = findViewById(R.id.check_coconut);

        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);
        showAnswer1Button = findViewById(R.id.show_answer_1);
        showAnswer2Button = findViewById(R.id.show_answer_2);
        showAnswer3Button = findViewById(R.id.show_answer_3);

        answerVodka = findViewById(R.id.check_vodka);
        boolean checkedVodka = answerVodka.isChecked();

        answerGin = findViewById(R.id.check_gin);
        boolean checkedGin = answerGin.isChecked();

        answerStirred = findViewById(R.id.check_stirred);
        boolean checkedStirred = answerStirred.isChecked();

        answerShaken = findViewById(R.id.check_shaken);
        boolean checkedShaken = answerShaken.isChecked();

        answerSugarCane = findViewById(R.id.check_cane);
        boolean checkedCane = answerSugarCane.isChecked();

        userTypedAnswer = findViewById(R.id.spirit_name);
        String userAnswer = userTypedAnswer.getText().toString();

//        Log.v("MainActivity", "gin " + checkedGin);

        changeButtonState(false);

        displayFinalScore(calculateScore(checkedVodka, checkedGin, checkedStirred, checkedShaken, checkedCane, userAnswer));

    }

    public int calculateScore(boolean vodka, boolean gin, boolean stirred, boolean shaken, boolean sugarCane, String userAnswer) {
        int score = 0;

        if ((! gin) && (! stirred)) {
            if ((vodka) && (shaken)) {
                score += 1;
            }
        }

        if (sugarCane) {
            score += 1;
        }

        if (userAnswer.equalsIgnoreCase("gin")) {
            score += 1;
        }

        return score;
    }

    public void displayFinalScore(int score) {
        TextView finalScore = findViewById(R.id.final_score);
        finalScore.setText("SCORE " + score);
    }

    public void changeButtonState(boolean state) {
        answerVodka.setEnabled(state);
        answerGin.setEnabled(state);
        answerStirred.setEnabled(state);
        answerShaken.setEnabled(state);
        answerSugarCane.setEnabled(state);
        answerPotatoes.setEnabled(state);
        answerWheat.setEnabled(state);
        answerCoconut.setEnabled(state);
        userTypedAnswer.setEnabled(state);
        submitButton.setEnabled(state);
        resetButton.setVisibility(View.VISIBLE);
        showAnswer1Button.setVisibility(View.VISIBLE);
        showAnswer2Button.setVisibility(View.VISIBLE);
        showAnswer3Button.setVisibility(View.VISIBLE);

    }

    public void viewAnswer(View view) {
        String correctAnswer = "";
        int location[]=new int[2];
        
        view.getLocationOnScreen(location);

        if (view.getId() == showAnswer1Button.getId()) {
            correctAnswer = "vodka and shaken";
        }
        if (view.getId() == showAnswer2Button.getId()) {
            correctAnswer = "sugarcane";
        }
        if (view.getId() == showAnswer3Button.getId()) {
            correctAnswer = "gin";
        }

        Toast toast = Toast.makeText(this, correctAnswer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.START, location[0], location[1]+90);
        toast.show();
    }

    public void reset(View view) {
        setContentView(R.layout.activity_main);
        resetButton.setVisibility(View.INVISIBLE);
    }
}
