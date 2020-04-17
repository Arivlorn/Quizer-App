package edu.andrews.cptr252.aisensee.quizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class AddQuestionsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new QuestionListFragment();
    }
}
