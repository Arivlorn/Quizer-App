package edu.andrews.cptr252.aisensee.quizer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Show the main menu, and allow navigation to its options.
 */
public class MenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // note that any views in a fragment are created in onCreateView, unlike an activity, where
    // they would be created in onCreate.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu, container, false);
        return v;
    }


    public void onPause() {
        super.onPause();
    }
}
