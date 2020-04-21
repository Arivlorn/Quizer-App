package edu.andrews.cptr252.aisensee.quizer;

import androidx.fragment.app.Fragment;

public class QuizActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() { return new QuizFragment(); }

}
