package edu.andrews.cptr252.aisensee.quizer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;


/**
 * Fragment to display a list of questions.
 */
public class QuestionListFragment extends Fragment {

    /** Reference to list of questions that will be displayed */
    private ArrayList<Question> mQuestions;

    /** RecyclerView that displays list of questions */
    private RecyclerView mRecyclerView;

    /** Adapter that generates/reuses views to display questions */
    private QuestionAdapter mQuestionAdapter;

    /** Reference to button to add a question. */
    private ImageButton mAddQuestionButton;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // sets the title of action bar
        getActivity().setTitle(R.string.questions_list_actionbar_label);

        // assigns an already created (or to be created) question list array to our local mQuestions array.
        // then retrieves all questions (the .getQuestions() part) and makes our local mQuestions equal to
        // the already created (or to be created) question list.
        // in other words, this could be creating (or not) while at the same time accessing and assigning a
        // reference to a local array list (mQuestions).
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        // use our custom question adapter for generating views for each question
        mQuestionAdapter = new QuestionAdapter(mQuestions, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // sets the view that we'll be returning for the onCreateView to take care of.
        View v = inflater.inflate(R.layout.question_list_fragment, container, false);

        //==========================================================================================
        // Recycler View for List Items
        //==========================================================================================

        // set to correct recycler view
        mRecyclerView = v.findViewById(R.id.question_list_recyclerView);
        // recyclerview will use our question adapter to create views for questions
        mRecyclerView.setAdapter(mQuestionAdapter);
        // use a linear layout when displaying questions
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //==========================================================================================
        // Add Question Button
        //==========================================================================================

        // set to correct button
        mAddQuestionButton = v.findViewById(R.id.add_question_button);
        // create an OnClickListener
        mAddQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on click, run addQuestion
                addQuestion();
            }
        });

        //==========================================================================================
        // For SwipeToDelete
        //==========================================================================================

        // create and attach touch helper for question swipes
        SwipeToDelete swipeToDelete = new SwipeToDelete(mQuestionAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDelete);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        // return the view to be created.
        return v;

    }

    /** Create a new question, add it to the list, and launch the details view. */
    private void addQuestion() {

        // create a new question
        Question question = new Question();

        // add it to the list
        QuestionList.getInstance(getActivity()).addQuestion(question);

        // create an intent to send to QuestionDetailsActivity
        Intent intent = new Intent(getActivity(), QuestionDetailsActivity.class);

        // add the question id as an extra so that QuestionDetailsFragment can actually edit it
        intent.putExtra(QuestionAdapter.EXTRA_QUESTION_ID, question.getID());

        // launch QuestionDetailsActivity, which launches QuestionDetailsFragment
        startActivityForResult(intent, 0);
    }

    /**
     * Question list fragment was paused (user was most likely editing a question).
     * Notify the adapter that the data set (Question list) may have changed.
     * The adapter will then update the changed view.
     */
    @Override
    public void onResume() {
        super.onResume();   // first execute parent's onResume method
        mQuestionAdapter.notifyDataSetChanged();    // notify adapter of data change

    }
}
