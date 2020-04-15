package edu.andrews.cptr252.aisensee.quizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionDetailsFragment extends Fragment {

    /** Question that is being viewed or edited */
    private Question mQuestion;

    /** Reference to title field for question */
    private EditText mQuestionField;

    public QuestionDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = new Question(); // TODO: come back and link to question list
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.question_details_fragment, container, false);

        // get a reference to EditTExt box for question title
        mQuestionField = v.findViewById(R.id.question_question);
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
