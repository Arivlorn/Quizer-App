package edu.andrews.cptr252.aisensee.quizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The control layer for the main menu of the app.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    /** Called when user taps Quiz button. */
    public void quizButton(View view) {

        // if there are one or more questions already created
        if (QuestionList.getInstance(getApplicationContext()).getQuestions().size() != 0) {
            // Creates a new intent with the information for context and the class we want to call.
            Intent intent = new Intent(this, QuizActivity.class);
            // Starts the new activity.
            startActivity(intent);
        }
        // if there are no questions created directs to questions list instead
        else {
            // Creates a new intent with the information for context and the class we want to call.
            Intent intent = new Intent(this, AddQuestionsActivity.class);
            // Starts the new activity.
            startActivity(intent);
        }

    }

    /** Called when user taps Add Questions button. */
    public void addQuestionsButton(View view) {

        // Creates a new intent with the information for context and the class we want to call.
        Intent intent = new Intent(this, AddQuestionsActivity.class);
        // Starts the new activity.
        startActivity(intent);

    }

}
