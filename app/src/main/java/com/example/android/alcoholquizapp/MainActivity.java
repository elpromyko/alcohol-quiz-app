package com.example.android.alcoholquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CheckBox answerVodka, answerGin, answerStirred, answerShaken, answerChile, answerBolivia,
            answerBrazil, answerPeru;
    RadioButton answerSugarcane, answerAllCountries, answerTomato, answerVatican;
    EditText userTypedAnswer;
    Button submitButton ,resetButton, bondAnswerButton, rumAnswerButton, ginAnswerButton, whiskyAnswerButton,
            piscoAnswerButton, maryAnswerButton, wineAnswerButton;
    RadioGroup groupWhisky, groupRum, groupMary, groupWine;
    LinearLayout groupBond, groupPisco;
    List<Button> answerButtons;
    List<LinearLayout> groups;
    List<RadioButton> radioCorrectAnswers;
    TextView finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitAnswers(View view) {

        finalScore = findViewById(R.id.final_score);

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

        answerButtons = Arrays.asList(bondAnswerButton, rumAnswerButton, ginAnswerButton,
                whiskyAnswerButton, piscoAnswerButton, maryAnswerButton, wineAnswerButton);

        groups = Arrays.asList(groupWhisky, groupRum, groupMary, groupWine, groupBond, groupPisco);

        radioCorrectAnswers = Arrays.asList(answerSugarcane, answerAllCountries, answerTomato, answerVatican);

        changeButtonState(false);

        displayFinalScore(calculateScore());

    }

    public int calculateScore() {
        int score = 0;

        if ((! answerGin.isChecked()) && (! answerStirred.isChecked())) {
            if ((answerVodka.isChecked()) && (answerShaken.isChecked())) score++;
        }

        if ((! answerBolivia.isChecked()) && (! answerBrazil.isChecked())) {
            if ((answerChile.isChecked()) && (answerPeru.isChecked())) score++;
        }

        if (userTypedAnswer.getText().toString().equalsIgnoreCase(getResources().getString(R.string.bond_answer_2))) score++;

        for (RadioButton correctAnswer : radioCorrectAnswers) {
            if (correctAnswer.isChecked()) score++;
        }

        return score;
    }

    public void displayFinalScore(int score) {
        finalScore.setText("SCORE " + score);
    }

    public void changeButtonState(boolean state) {
        userTypedAnswer.setFocusable(state);
        userTypedAnswer.setEnabled(state);
        submitButton.setEnabled(state);

        resetButton.setVisibility(View.VISIBLE);
        for (Button btn : answerButtons) {
            btn.setVisibility(View.VISIBLE);
        }

        for (LinearLayout grp : groups) {
            for (int i = 0; i < grp.getChildCount(); i++) {
                grp.getChildAt(i).setEnabled(state);
            }
        }

    }

    public void viewAnswer(View view) {
        String correctAnswer = "";
        int location[]=new int[2];

        for (Button btn : answerButtons) {
            if (view.getId() == btn.getId()) {
                correctAnswer = (String) btn.getTag();
            }
        }

        view.getLocationOnScreen(location);
        Toast toast = Toast.makeText(this, correctAnswer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.START, location[0], location[1]+90);
        toast.show();
    }

    public void reset(View view) {
        setContentView(R.layout.activity_main);
    }
}
