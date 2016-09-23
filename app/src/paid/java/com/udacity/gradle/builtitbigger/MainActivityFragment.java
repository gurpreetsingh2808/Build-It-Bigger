package com.udacity.gradle.builtitbigger;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Jokes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RelativeLayout rlCard;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        rlCard = (RelativeLayout) root.findViewById(R.id.rlCard);
        rlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////rlCard.setEnabled(false);
                //  fetch jokes
                new EndpointsAsyncTask().execute(MainActivity.this);
            }
        });

        return root;
    }
}
