package com.example.android.alcoholquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox answerVodka, answerGin, answerStirred, answerShaken, answerChile, answerBolivia,
            answerBrazil, answerPeru;
    RadioButton answerSugarcane, answerAllCountries, answerTomato, answerVatican;
    EditText userTypedAnswer;
    Button submitButton ,resetButton, bondAnswerButton, rumAnswerButton, ginAnswerButton, whiskyAnswerButton,
            piscoAnswerButton, maryAnswerButton, wineAnswerButton;
    RadioGroup groupWhisky, groupRum, groupMary, groupWine;
    LinearLayout groupBond, groupPisco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void submitAnswers(View view) {
        // buttons
        submitButton = findViewById(R.id.submit_button);
        resetButton = findViewById(R.id.reset_button);
        bondAnswerButton = findViewById(R.id.show_answer_bond);
        rumAnswerButton = findViewById(R.id.show_answer_rum);
        ginAnswerButton = findViewById(R.id.show_answer_gin);
        whiskyAnswerButton = findViewById(R.id.show_answer_whisky);
        piscoAnswerButton = findViewById(R.id.show_answer_pisco);
        maryAnswerButton = findViewById(R.id.show_answer_mary);
        wineAnswerButton = findViewById(R.id.show_answer_wine);

        // checkboxes
        answerVodka = findViewById(R.id.check_vodka);
        answerGin = findViewById(R.id.check_gin);
        answerStirred = findViewById(R.id.check_stirred);
        answerShaken = findViewById(R.id.check_shaken);
        answerBolivia = findViewById(R.id.check_bolivia);
        answerBrazil = findViewById(R.id.check_brazil);
        answerChile = findViewById(R.id.check_chile);
        answerPeru = findViewById(R.id.check_peru);

        // radio
        answerAllCountries = findViewById(R.id.radio_all_countries);
        answerSugarcane = findViewById(R.id.radio_cane);
        answerTomato = findViewById(R.id.radio_tomato);
        answerVatican = findViewById(R.id.radio_vatican);

        userTypedAnswer = findViewById(R.id.spirit_name);

        groupWhisky = findViewById(R.id.group_whisky);
        groupBond = findViewById(R.id.group_bond);
        groupPisco = findViewById(R.id.group_pisco);
        groupRum = findViewById(R.id.group_rum);
        groupMary = findViewById(R.id.group_mary);
        groupWine = findViewById(R.id.group_wine);

//        Log.v("MainActivity", "gin " + checkedGin);

        changeButtonState(false);

        displayFinalScore(calculateScore());

    }

    public int calculateScore() {
        int score = 0;

        if ((! answerGin.isChecked()) && (! answerStirred.isChecked())) {
            if ((answerVodka.isChecked()) && (answerShaken.isChecked())) score++;
        }

        if (answerSugarcane.isChecked()) score++;

        if (userTypedAnswer.getText().toString().equalsIgnoreCase("gin")) score++;

        if (answerAllCountries.isChecked()) score++;

        if ((! answerBolivia.isChecked()) && (! answerBrazil.isChecked())) {
            if ((answerChile.isChecked()) && (answerPeru.isChecked())) score++;
        }

        if (answerTomato.isChecked()) score++;

        if (answerVatican.isChecked()) score++;

        return score;
    }

    public void displayFinalScore(int score) {
        TextView finalScore = findViewById(R.id.final_score);
        finalScore.setText("SCORE " + score);
    }

    public void changeButtonState(boolean state) {
        userTypedAnswer.setFocusable(state);
        userTypedAnswer.setEnabled(state);
        submitButton.setEnabled(state);
        resetButton.setVisibility(View.VISIBLE);
        bondAnswerButton.setVisibility(View.VISIBLE);
        rumAnswerButton.setVisibility(View.VISIBLE);
        ginAnswerButton.setVisibility(View.VISIBLE);
        whiskyAnswerButton.setVisibility(View.VISIBLE);
        piscoAnswerButton.setVisibility(View.VISIBLE);
        maryAnswerButton.setVisibility(View.VISIBLE);
        wineAnswerButton.setVisibility(View.VISIBLE);

        for (int i = 0; i < groupWhisky.getChildCount(); i++) {
            groupWhisky.getChildAt(i).setEnabled(state);
        }
        for (int i = 0; i < groupBond.getChildCount(); i++) {
            groupBond.getChildAt(i).setEnabled(state);
        }
        for (int i = 0; i < groupPisco.getChildCount(); i++) {
            groupPisco.getChildAt(i).setEnabled(state);
        }
        for (int i = 0; i < groupRum.getChildCount(); i++) {
            groupRum.getChildAt(i).setEnabled(state);
        }
        for (int i = 0; i < groupMary.getChildCount(); i++) {
            groupMary.getChildAt(i).setEnabled(state);
        }
        for (int i = 0; i < groupWine.getChildCount(); i++) {
            groupWine.getChildAt(i).setEnabled(state);
        }

    }

    public void viewAnswer(View view) {
        String correctAnswer = "";
        int location[]=new int[2];

        view.getLocationOnScreen(location);
//        Log.v("MainActivity", String.valueOf(view.getId()));

        if (view.getId() == bondAnswerButton.getId()) {
            correctAnswer = "vodka and shaken";
        }
        if (view.getId() == rumAnswerButton.getId()) {
            correctAnswer = "sugarcane";
        }
        if (view.getId() == ginAnswerButton.getId()) {
            correctAnswer = "gin";
        }
        if (view.getId() == whiskyAnswerButton.getId()) {
            correctAnswer = "all countries";
        }
        if (view.getId() == piscoAnswerButton.getId()) {
            correctAnswer = "Chile and Peru";
        }
        if (view.getId() == maryAnswerButton.getId()) {
            correctAnswer = "tomato";
        }
        if (view.getId() == wineAnswerButton.getId()) {
            correctAnswer = "Vatican City";
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
