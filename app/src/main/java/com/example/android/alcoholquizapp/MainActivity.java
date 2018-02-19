package com.example.android.alcoholquizapp;

import android.content.Intent;
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
    private CheckBox answerVodka;
    private CheckBox answerGin;
    private CheckBox answerStirred;
    private CheckBox answerShaken;
    private CheckBox answerChile;
    private CheckBox answerBolivia;
    private CheckBox answerBrazil;
    private CheckBox answerPeru;
    private RadioButton answerSugarcane;
    private RadioButton answerAllCountries;
    private RadioButton answerTomato;
    private RadioButton  answerVatican;
    private EditText userTypedAnswer;
    private Button submitButton;
    private Button resetButton;
    private Button bondAnswerButton;
    private Button rumAnswerButton;
    private Button ginAnswerButton;
    private Button whiskyAnswerButton;
    private Button piscoAnswerButton;
    private Button maryAnswerButton;
    private Button wineAnswerButton;
    private RadioGroup groupWhisky;
    private RadioGroup groupRum;
    private RadioGroup groupMary;
    private RadioGroup groupWine;
    private LinearLayout groupBond;
    private LinearLayout groupPisco;
    private List<Button> answerButtons;
    private List<LinearLayout> groups;
    private List<RadioButton> radioCorrectAnswers;
    private TextView finalScore;
    static final String WHISKY_GROUP_STATE = "whiskyGroup";
    static final String SCORE = "score";
    static final String SCORE_VISIBILITY = "scoreVisibility";
    static final String ANSWER_VISIBILITY = "answerVisibility";
    static final String RESET_VISIBILITY = "resetVisibility";
    static final String SUBMIT_STATE = "submitButtonState";
    static final String USER_ANSWER_STATE = "userTypedAnswerState";
    static final String USER_ANSWER_FOCUSABLE = "userAnswerFocusable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save current states and visibilities for buttons and answers
        savedInstanceState.putBoolean(WHISKY_GROUP_STATE, groupWhisky.getChildAt(0).isEnabled());
        savedInstanceState.putCharSequence(SCORE, finalScore.getText());
        savedInstanceState.putInt(SCORE_VISIBILITY, finalScore.getVisibility());
        savedInstanceState.putInt(ANSWER_VISIBILITY, bondAnswerButton.getVisibility());
        savedInstanceState.putInt(RESET_VISIBILITY, resetButton.getVisibility());
        savedInstanceState.putBoolean(SUBMIT_STATE, submitButton.isEnabled());
        savedInstanceState.putBoolean(USER_ANSWER_STATE, userTypedAnswer.isEnabled());
        savedInstanceState.putBoolean(USER_ANSWER_FOCUSABLE, userTypedAnswer.isFocusable());

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state (enabled / disabled) for all group of answers
        for (LinearLayout group : groups) {
            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(savedInstanceState.getBoolean(WHISKY_GROUP_STATE));
            }
        }
        finalScore.setText(savedInstanceState.getCharSequence(SCORE));
        finalScore.setVisibility(savedInstanceState.getInt(SCORE_VISIBILITY));

        // Restore visibility for answer , reset and submit buttons
        for (Button btn : answerButtons) {
            btn.setVisibility(savedInstanceState.getInt(ANSWER_VISIBILITY));
        }
        resetButton.setVisibility(savedInstanceState.getInt(RESET_VISIBILITY));
        submitButton.setEnabled(savedInstanceState.getBoolean(SUBMIT_STATE));

        // Restore EditText View state
        userTypedAnswer.setEnabled(savedInstanceState.getBoolean(USER_ANSWER_STATE));
        userTypedAnswer.setFocusable(savedInstanceState.getBoolean(USER_ANSWER_FOCUSABLE));
    }

    public void initializeVariables() {
        finalScore = findViewById(R.id.final_score);
        userTypedAnswer = findViewById(R.id.spirit_name);

        // groups of answers
        groupWhisky = findViewById(R.id.group_whisky);
        groupRum = findViewById(R.id.group_rum);
        groupBond = findViewById(R.id.group_bond);
        groupPisco = findViewById(R.id.group_pisco);
        groupMary = findViewById(R.id.group_mary);
        groupWine = findViewById(R.id.group_wine);

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

        // correct radio
        answerAllCountries = findViewById(R.id.radio_all_countries);
        answerSugarcane = findViewById(R.id.radio_cane);
        answerTomato = findViewById(R.id.radio_tomato);
        answerVatican = findViewById(R.id.radio_vatican);

        // lists
        answerButtons = Arrays.asList(bondAnswerButton, rumAnswerButton, ginAnswerButton,
                whiskyAnswerButton, piscoAnswerButton, maryAnswerButton, wineAnswerButton);
        groups = Arrays.asList(groupWhisky, groupRum, groupMary, groupWine, groupBond, groupPisco);
        radioCorrectAnswers = Arrays.asList(answerSugarcane, answerAllCountries, answerTomato, answerVatican);

    }

    public void submitAnswers(View view) {
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
        finalScore.setText(String.format("SCORE: %d/7", score));
        finalScore.setVisibility(View.VISIBLE);
    }

    public void changeButtonState(boolean state) {
        userTypedAnswer.setFocusable(state);
        userTypedAnswer.setEnabled(state);
        submitButton.setEnabled(state);

        resetButton.setVisibility(View.VISIBLE);
        for (Button btn : answerButtons) {
            btn.setVisibility(View.VISIBLE);
        }

        for (LinearLayout group : groups) {
            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(state);
            }
        }

    }

    // Shows toast message below button with button tag dependant text
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
