package edu.andrews.cptr252.aisensee.quizer;

import androidx.appcompat.app.AppCompatActivity;

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

    /** Called when user taps Add Questions button. */
    public void addQuestionsButton(View view) {

        // Creates a new intent with the information for context and the class we want to call.
        Intent intent = new Intent(this, AddQuestionsActivity.class);

        // Starts the new activity.
        startActivity(intent);

    }

    /** Called when user taps Quiz button. */
    public void quizButton(View view) {
        // TODO: 2 Link to quiz mode activity
        // Creates a new intent with the information for context and the class we want to call.
        //Intent intent = new Intent(this, .class);

        // Starts the new activity.
        //startActivity(intent);
    }

}
