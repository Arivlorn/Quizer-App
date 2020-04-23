package edu.andrews.cptr252.aisensee.quizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.UUID;


/**
 * A fragment that allows the user to edit a question details.
 */
public class QuestionDetailsFragment extends Fragment {

    /** Reference to title field for question */
    private EditText mQuestionField;

    /** Reference to toggle button for true/false answer */
    private ToggleButton mToggleButton;

    /** Required empty public constructor */
    public QuestionDetailsFragment() {

    }

    /** Question that is being viewed or edited */
    private Question mQuestion;

    /**
     * Create a new QuestionDetailsFragment with a given Question id as an argument.
     * @param questionID
     * @return A reference to the new QuestionDetailsFragment
     */
    public static QuestionDetailsFragment newInstance(UUID questionID) {

        // create a new argument Bundle object
        Bundle args = new Bundle();

        // add the question id as an argument
        args.putSerializable(QuestionAdapter.EXTRA_QUESTION_ID, questionID);

        // create a new instance of QuestionDetailsFragment
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();

        // pass the bundle (containing the specific id of the question) to the fragment. Will be unpacked in the fragment's onCreate(Bundle) method
        fragment.setArguments(args);

        // return a reference to the newly created fragment
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // extract question id from the bundle
        UUID questionID = (UUID)getArguments().getSerializable(QuestionAdapter.EXTRA_QUESTION_ID);

        // get the question with the id from the Bundle.
        // this will be the question that the fragment displays.
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(questionID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_details_fragment, container, false);

        // sets the title of action bar
        getActivity().setTitle(R.string.question_details_actionbar_label);

        //==========================================================================================
        // Question Question Text Box
        //==========================================================================================

        // get a reference to EditText box for question question
        mQuestionField = v.findViewById(R.id.question_EditText);

        // set text of question edit text field
        mQuestionField.setText(mQuestion.getQuestion());

        // listen for information in the question field to change
        mQuestionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // left blank intentionally
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // user typed text, update the question with new CharSequence
                mQuestion.setQuestion(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // left blank on purpose

            }
        });

        //==========================================================================================
        // True/False ToggleButton
        //==========================================================================================

        // get a reference to true/false toggle button
        mToggleButton = v.findViewById(R.id.true_false_ToggleButton);

        // set original state of button to true/false based on question answer
        if (mQuestion.getAnswer() == true) {
            mToggleButton.setChecked(true);
        }
        else{ // (mQuestion.getAnswer() == false)
            mToggleButton.setChecked(false);    // redundant as default state is unchecked.
        }

        // listen for state change on the toggle button, and change data based on status.
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // toggle is enabled
                if(isChecked) {
                    mQuestion.setAnswer(true);
                }
                // toggle is disabled
                else {
                    mQuestion.setAnswer(false);
                }
            }
        });

        // return view
        return v;
    }

    /**
     * Save the question list to a JSON file when app is paused.
     */
    @Override
    public void onPause() {
        super.onPause();

        // only save questionslist if
        QuestionList.getInstance(getActivity()).saveQuestions();
    }

}
