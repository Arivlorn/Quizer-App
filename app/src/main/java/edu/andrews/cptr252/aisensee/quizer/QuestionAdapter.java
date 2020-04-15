package edu.andrews.cptr252.aisensee.quizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter responsible for creating the view for a question in the questions list.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    /** Used to store reference to list of questions */
    private ArrayList<Question> mQuestions;

    /**
     * Constructor for QuestionsAdapter. Initialize adapter with given list of questions.
     * @param questions list of questions to display
     */
    public QuestionAdapter(ArrayList<Question> questions) {
        mQuestions = questions;
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
            }

        }

    } // end of viewholder

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
}
