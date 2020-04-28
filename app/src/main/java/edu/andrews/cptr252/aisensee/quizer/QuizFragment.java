package edu.andrews.cptr252.aisensee.quizer;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Creates and controls the quiz mode of the app.
 */
public class QuizFragment extends Fragment {

    /** The question text view */
    private TextView mQuestionField;

    /** The questions left text view */
    private TextView mQuestionsLeftField;

    /** The number of questions left */
    private int mQuestionsLeft;

    /** Key for storing questions left in bundle */
    private static final String KEY_QUESTIONS_LEFT = "questionsLeft";

    /** The questions correct text view */
    private TextView mQuestionsCorrectField;

    /** The number of questions answered correctly */
    private int mQuestionsCorrect;

    /** Key for storing questions correct in bundle */
    private static final String KEY_QUESTIONS_CORRECT = "questionsCorrect";

    /** The true button */
    private ImageButton mTrueButton;

    /** The false button */
    private ImageButton mFalseButton;

    /** Instance of singleton questions list class */
    private QuestionList mQuestionsListClass;

    /** Array list of questions */
    private ArrayList<Question> mQuestions;

    /** Current question index */
    private int i;

    /** Key for storing index of current question for bundle */
    private static final String KEY_QUESTION_INDEX = "questionIndex";

    /** Background TextView */
    private TextView mBackground;


    /** Constructor */
    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Process to run when question has been answered.
     * @param userAnswer the user's true/false answer
     */
    @SuppressLint("SetTextI18n")
    private void checkAnswer(boolean userAnswer) {

        // if there are still questions left and user entered correctly
        if (i != -1 && userAnswer == mQuestions.get(i).getAnswer()) {

            // display new number of correct
            mQuestionsCorrectField.setText(Integer.toString(++mQuestionsCorrect));

            // change background color for input feedback
            int colorFrom = getResources().getColor(R.color.colorTrueDark);
            int colorTo = getResources().getColor(R.color.colorTransparent);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(750); // milliseconds
            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    mBackground.setBackgroundColor((int) animator.getAnimatedValue());
                }
            });
            colorAnimation.start();

        }
        // if user entered incorrectly
        else {

            // change background color for input feedback
            int colorFrom = getResources().getColor(R.color.colorFalseDark);
            int colorTo = getResources().getColor(R.color.colorTransparent);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(750); // milliseconds
            colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    mBackground.setBackgroundColor((int) animator.getAnimatedValue());
                }
            });
            colorAnimation.start();

            // for now, do nothing else

        }

        // check to see if there are questions are left, as action is dependent on being done or not
        // if there are questions left
        if ((mQuestions.size() - i - 1) != 0) {

            // set question as next question (advancing index by 1)
            mQuestionField.setText(mQuestions.get(++i).getQuestion());

            // take one away from the number of questions left and update TextView
            mQuestionsLeftField.setText(Integer.toString(--mQuestionsLeft));

        }
        // if there are no questions left
        else {

            // set i to -1 to signify that there are no questions left
            i = -1;

            // display final message
            finalMessage();
        }


    }

    /**
     * Sets the completion message for the app.
     */
    @SuppressLint("SetTextI18n")
    private void finalMessage() {

        // set questions left to 0
        mQuestionsLeftField.setText(Integer.toString(0));   // ideally, would not use hard coded number. Works, though!
        // set questions correct to value
        mQuestionsCorrectField.setText(Integer.toString(mQuestionsCorrect));

        // concatenates and displays final message
        //String string = (R.string.final_message_part_1 + mQuestionsCorrect + R.string.final_message_part_2 + mQuestions.size() + R.string.final_message_part_3);
        mQuestionField.setText(getString(R.string.final_message_part_1)
                + " " + mQuestionsCorrect + " " + getString(R.string.final_message_part_2)
                + " " + mQuestions.size() + " " + getString(R.string.final_message_part_3));
    }

    /**
     * Updates question, questions left, and questions correct.
     */
    @SuppressLint("SetTextI18n")
    private void updateQuestion() {

        // set question to first question. Placed here as would not work if i = -1 in final case
        mQuestionField.setText(mQuestions.get(i).getQuestion());

        // set questions left and questions correct text box to initial values
        mQuestionsLeftField.setText(Integer.toString(mQuestionsLeft ));
        mQuestionsCorrectField.setText(Integer.toString(mQuestionsCorrect));
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get list of questions and assign to mQuestions for local access
        mQuestionsListClass = QuestionList.getInstance(getActivity());

        // add questions from that class to private ArrayList for easy display
        mQuestions = mQuestionsListClass.getQuestions();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // define view
        View v = inflater.inflate(R.layout.quiz_fragment, container, false);

        // get a reference to question text view
        mQuestionField = v.findViewById(R.id.question_TextView);

        // get a reference to true button
        mTrueButton = v.findViewById(R.id.answer_question_true_Button);

        // get a reference to false button
        mFalseButton = v.findViewById(R.id.answer_question_false_Button);

        // get a reference to questions left text box
        mQuestionsLeftField = v.findViewById(R.id.questions_left_TextView);

        // number of questions left (if one is already being displayed)
        mQuestionsLeft = mQuestions.size();

        // get a reference to questions correct text box
        mQuestionsCorrectField = v.findViewById(R.id.questions_correct_TextView);

        // number of questions correct
        mQuestionsCorrect = 0;

        // set index
        i = 0;

        // get a reference to background
        mBackground = v.findViewById(R.id.background_TextView);

        //==========================================================================================
        // If Device Rotated
        //==========================================================================================

        if (savedInstanceState != null) {
            // retrieve index
            i = savedInstanceState.getInt(KEY_QUESTION_INDEX);
            // retrieve left
            mQuestionsLeft = savedInstanceState.getInt(KEY_QUESTIONS_LEFT);
            // retrieve correct
            mQuestionsCorrect = savedInstanceState.getInt(KEY_QUESTIONS_CORRECT);

            // display final message or again display question if device has rotated
            // if index has been set outside of the list
            if (i == -1) {

                finalMessage();

            }
            // if we're still somewhere in the index
            else {
                updateQuestion();
            }
        }

        //==========================================================================================
        // Begin Quiz Mode Control
        //==========================================================================================

        // if above block does not execute, we must be on first time startup. So, do this.
        else {
            updateQuestion();
        }




        // true/false button listeners
        mTrueButton.setOnClickListener(new View.OnClickListener() {     // true button
            @Override
            public void onClick(View v) {
                // run getAnswer with user input if there are questions left
                if (i != -1) {
                    checkAnswer(true);
                }
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {    // false button
            @Override
            public void onClick(View v) {
                // run getAnswer with user input of false if there are questions left
                if (i != -1) {
                    checkAnswer(false);
                }
            }
        });

        // return view
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // store the index of the current question in the bundle
        savedInstanceState.putInt(KEY_QUESTION_INDEX, i);

        // store the number of questions left in bundle
        savedInstanceState.putInt(KEY_QUESTIONS_LEFT, mQuestionsLeft);

        // store the number of questions correct in bundle
        savedInstanceState.putInt(KEY_QUESTIONS_CORRECT, mQuestionsCorrect);

    }
}
