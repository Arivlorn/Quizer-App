package edu.andrews.cptr252.aisensee.quizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionDetailsFragment extends Fragment {


    /** Reference to title field for question */
    private EditText mQuestionField;

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

        // get a reference to EditText box for question title
        mQuestionField = v.findViewById(R.id.question_question);

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

        return v;
    }

}
