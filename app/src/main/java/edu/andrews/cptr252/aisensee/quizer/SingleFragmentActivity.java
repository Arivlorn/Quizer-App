package edu.andrews.cptr252.aisensee.quizer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A generic activity designed to hold a single fragment. Eliminates repetitive coding.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    /** Returns instance of fragment that will be hosted by the activity. */
    protected abstract Fragment createFragment();   // all derived classes must implement abstract methods.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_activity); // creates a fragment with our frame layout as the parent.

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

}
