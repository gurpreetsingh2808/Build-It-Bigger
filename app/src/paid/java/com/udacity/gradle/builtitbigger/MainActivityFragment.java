package com.udacity.gradle.builtitbigger;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.Jokes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static ProgressDialog progressDialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        RelativeLayout rlCard = (RelativeLayout) root.findViewById(R.id.rlCard);
        rlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(progressDialog != null) {
                    progressDialog.show();
                }
                //  fetch jokes
                new EndpointsAsyncTask().execute(getActivity());
            }
        });

        //  loader
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching jokes for you...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return root;
    }
}
