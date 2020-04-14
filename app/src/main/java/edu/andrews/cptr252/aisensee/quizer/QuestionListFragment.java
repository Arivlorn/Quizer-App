package edu.andrews.cptr252.aisensee.quizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Fragment to display a list of questions.
 */
public class QuestionListFragment extends Fragment {

    /** Reference to list of questions that will be displayed */
    private ArrayList<Question> mQuestions;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // sets the title of the above bar? TODO: find out
        getActivity().setTitle(R.string.question_list_label);

        // assigns an already created (or to be created) question list array to our local mQuestions array.
        // then retrieves all questions (the .getQuestions() part) and makes our local mQuestions equal to
        // the already created (or to be created) question list.
        // in other words, this could be creating (or not) while at the same time accessing and assigning a
        // reference to a local array list (mQuestions).
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        // doesn't do anything yet. soon. in buglistfragment displayed bug tags to log
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // sets the view that we'll be returning for the onCreateView to take care of.
        View v = inflater.inflate(R.layout.question_list_fragment, container, false);


        // return the view to be created.
        return v;
    }
}
