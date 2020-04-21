package edu.andrews.cptr252.aisensee.quizer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    /** The questions correct text view */
    private TextView mQuestionsCorrectField;

    /** The number of questions answered correctly */
    private int mQuestionsCorrect;

    /** The true button */
    private Button mTrueButton;

    /** The false button */
    private Button mFalseButton;

    /** Instance of singleton questions list class */
    private QuestionList mQuestionsListClass;

    /** Array list of questions */
    private ArrayList<Question> mQuestions;

    /** Current question index */
    private int i;

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

        }
        // if user entered incorrectly
        else {

            // for now, do nothing

        }

        // check to see how many questions are left, as next action is dependent on being done or not
        // if there are questions left
        if (mQuestionsLeft != 0) {

            // set question as next question (advancing index by 1)
            mQuestionField.setText(mQuestions.get(++i).getQuestion());

            // take one away from the number of questions left and update TextView
            mQuestionsLeftField.setText(Integer.toString(--mQuestionsLeft));

        }
        // if there are no questions left
        else {

            // set i to -1 to signify that there are no questions left
            i = -1;

        }



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
        mQuestionsLeft = mQuestions.size() - 1;

        // get a reference to questions correct text box
        mQuestionsCorrectField = v.findViewById(R.id.questions_correct_TextView);

        // number of questions correct
        mQuestionsCorrect = 0;

        // set index
        i = 0;

        //==========================================================================================
        // Quiz Mode Control
        //==========================================================================================

        // set question to first question
        mQuestionField.setText(mQuestions.get(i).getQuestion());

        // set questions left and questions correct text box to initial values
        mQuestionsLeftField.setText(Integer.toString(mQuestionsLeft));
        mQuestionsCorrectField.setText(Integer.toString(mQuestionsCorrect));

        // true/false button listeners
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // run getAnswer with user input of true
                checkAnswer(true);

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // run getAnswer with user input of false
                checkAnswer(false);

            }
        });

        // return view
        return v;
    }
}
