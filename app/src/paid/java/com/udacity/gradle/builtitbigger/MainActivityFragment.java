package com.udacity.gradle.builtitbigger;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Jokes;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.ConnectivityUtil;
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

                if(new ConnectivityUtil().isConnected(getContext())) {
                    if(progressDialog != null) {
                        progressDialog.show();
                    }
                    //  fetch jokes
                    new EndpointsAsyncTask().execute(getActivity());
                }
                else {
                    Toast.makeText(getContext(), "Please checkyour internet connection " +
                            "and try again", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //  loader
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fun is about to begin...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return root;
    }
}
