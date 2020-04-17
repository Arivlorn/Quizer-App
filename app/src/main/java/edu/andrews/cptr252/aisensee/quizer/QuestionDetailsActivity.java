package edu.andrews.cptr252.aisensee.quizer;

import androidx.fragment.app.Fragment;

import java.util.UUID;

/**
 * Activity that hosts a QuestionDetailsFragment
 */
public class QuestionDetailsActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {

        // QuestionListFragment now launches QuestionDetailsActivity with a specific question id.
        // Get the intent sent to this activity from the QuestionListFragment.
        UUID questionID = (UUID) getIntent().getSerializableExtra(QuestionAdapter.EXTRA_QUESTION_ID);

        // create a new instance of the QuestionDetailsFragment with the Question id as an argument
        return new QuestionDetailsFragment().newInstance(questionID);
    }
}