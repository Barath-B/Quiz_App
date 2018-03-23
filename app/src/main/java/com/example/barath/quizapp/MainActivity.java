package com.example.barath.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] ques_ans;
    int[] ans = {0, 0, 3, 2, 1, 2, 1, 0, 1, 0};
    int[] and = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
    int count = 0;
    int score = 0;
    private RadioButton rb1, rb2, rb3, rb4, rb;
    private RadioGroup r_g;
    private Button nextbut, reset;
    private TextView qn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ques_ans = getResources().getStringArray(R.array.qna);
        initiate();
        setContent();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putInt("count", count);
        savedInstanceState.putInt("score", score);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        count = savedInstanceState.getInt("count");
        score = savedInstanceState.getInt("score");
        setContent();
    }

    private void initiate() {
        count = 0;
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);
        rb4 = findViewById(R.id.option4);
        r_g = findViewById(R.id.rg);
        nextbut = findViewById(R.id.next);
        reset = findViewById(R.id.reset);
        qn = findViewById(R.id.qn);
    }

    /*
    *   Changes the content on the display after each validation
     */
    private void setContent() {
        if (count < 10) {
            qn.setText(ques_ans[count * 5]);
            rb1.setText(ques_ans[(count * 5) + 1]);
            rb2.setText(ques_ans[(count * 5) + 2]);
            rb3.setText(ques_ans[(count * 5) + 3]);
            rb4.setText(ques_ans[(count * 5) + 4]);
        } else {
            nextbut.setVisibility(View.GONE);
            reset.setVisibility(View.VISIBLE);
        }
    }

    /*
    *   validates the answer and displays the final score
     */
    private void validateAns(int selected_ans) {
        if (ans[count] == selected_ans) {
            score++;
        }
        if (count == 9) {
            Toast.makeText(this, getString(R.string.score_jtoast) + score + getString(R.string.scoretot_jToast), Toast.LENGTH_LONG).show();
        }
    }

    /*
    *   Checks for the input and calls validate method and changes the question
     */
    public void next(View view) {
        if (r_g.getCheckedRadioButtonId() != -1) {
            rb = findViewById(r_g.getCheckedRadioButtonId());
            int inx = r_g.indexOfChild(rb);
            validateAns(inx);
            count++;
            r_g.clearCheck();
            setContent();
        } else {
            Toast.makeText(this, R.string.error_jToast, Toast.LENGTH_SHORT).show();
        }
    }

    /*
    *   Resets everything and restarts the quiz
     */
    public void reset(View view) {
        count = 0;
        score = 0;
        r_g.clearCheck();
        setContent();
        nextbut.setVisibility(View.VISIBLE);
        reset.setVisibility(View.GONE);
    }
}
