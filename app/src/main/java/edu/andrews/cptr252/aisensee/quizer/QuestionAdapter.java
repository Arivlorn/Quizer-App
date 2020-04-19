package edu.andrews.cptr252.aisensee.quizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Adapter responsible for creating the view for a question in the questions list.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    /** Used to store reference to list of questions */
    private ArrayList<Question> mQuestions;

    /** Key used to pass the id of a specific question. */
    public static final String EXTRA_QUESTION_ID = "edu.andrews.cptr252.aisensee.quizer.question_id";

    /** Context hosting the view. */
    public Context mContext;

    /** Activity hosting the list fragment */
    private Activity mActivity;     // for swipe to delete

    /**
     * Constructor for QuestionsAdapter. Initialize adapter with given list of questions.
     * @param questions list of questions to display
     */
    public QuestionAdapter(ArrayList<Question> questions, Activity activity) {
        mQuestions = questions;
        mActivity = activity;   // for swipe to delete
    }

    /** Return reference to activity hosting the question list fragment */
    public Context getActivity() {
        return mActivity;   // for swipe to delete
    }

    /**
     * Class to hold references to widgets on a given view.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        /** TextView that displays the question. */
        public TextView questionTextView;

        /** Create a new view holder for a given view item in the question list */
        public ViewHolder(View itemView) {
            super(itemView);

            // store references to the widget on the view item
            questionTextView = itemView.findViewById(R.id.question_list_item_textView);
            itemView.setOnClickListener(this);

            // get the context of the view. This will be the activity hosting the view.
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        /**
         * OnClick listener for question in the question list.
         * Triggered when user clicks on a question in the list.
         * @param view for question that was clicked
         */
        @Override
        public void onClick(View view) {
            // get index of question that was clicked
            int position = getAdapterPosition();
            // for now, just display the selected question.
            // TODO: open the selected question.
            if (position != RecyclerView.NO_POSITION) {
                Question question = mQuestions.get(position);

                // start an instance of QuestionDetails fragment
                Intent i = new Intent(mContext, QuestionDetailsActivity.class);

                // pass the id of the question as an extra in the intent
                i.putExtra(QuestionAdapter.EXTRA_QUESTION_ID, question.getID());

                // actually start the activity
                mContext.startActivity(i);

            }

        }

    } // end of ViewHolder

    /**
     * Create a new view to display a question.
     * Return the VieHolder that stores references to the widgets on the new view.
     */
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // get the layout inflater from parent that contains the question view item
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate the layout to display a question in the list
        View questionView = inflater.inflate(R.layout.list_item_question, parent, false);

        // create a view holder to store references to the widgets on the new view
        ViewHolder viewHolder = new ViewHolder(questionView);

        return viewHolder;
    }

    /**
     * Display given question in the view referenced by the given ViewHolder.
     * @param viewHolder contains references to widgets used to display question.
     * @param position Index of the question in the list.
     */
    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder viewHolder, int position) {
        // get question for given index in question list
        Question question = mQuestions.get(position);

        // get references to widgets stored in the ViewHolder
        TextView questionTextView = viewHolder.questionTextView;

        // updated widgets on view with question details
        questionTextView.setText(question.getQuestion());

    }

    /**
     * Get number of questions in question list.
     */
    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    /** Create snackbar with ability to undo question deletion.*/
    private void showUndoSnackbar(final Question question, final int position) {
        // get root view for activity hosting question list fragment
        View view = mActivity.findViewById(android.R.id.content);

        // build message stating which question was deleted
        String questionDeletedText = mActivity.getString(R.string.deleted_message, question.getQuestion());

        // create the snackbar
        Snackbar snackbar = Snackbar.make(view, questionDeletedText, Snackbar.LENGTH_LONG);

        // add the Undo option to the snackbar
        snackbar.setAction(R.string.undo_option, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // undo is slected, restore the deleted item
                restoreQuestion(question, position);
            }
        });

        // text for UNDO set to yellow
        snackbar.setActionTextColor(Color.YELLOW);

        // display snackbar
        snackbar.show();
    }

    /**
     * Remove question from list.
     * @param position index of question to remove
     */
    public void deleteQuestion(int position) {

        // save deleted question so we can undo deletion if we need to
        final Question question = mQuestions.get(position);

        // delete question from list
        QuestionList.getInstance(mActivity).deleteQuestion(position);

        // update list of questions in RecyclerView
        notifyItemRemoved(position);

        // display snackbar so user may undo delete
        showUndoSnackbar(question, position);
    }

    /**
     * Put deleted question back into the list.
     * @param question to restore
     * @param position in list where question will go
     */
    public void restoreQuestion(Question question, int position) {

        // grabs an instance of our questions list, then runs the method on it to add a question
        QuestionList.getInstance(mActivity).addQuestion(position, question);

        // notify RecyclerView that data has changed
        notifyItemInserted(position);
    }
}
