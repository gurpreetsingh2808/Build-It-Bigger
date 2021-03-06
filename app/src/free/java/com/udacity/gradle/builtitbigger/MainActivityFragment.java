package com.udacity.gradle.builtitbigger;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.ConnectivityUtil;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private InterstitialAd mInterstitialAd;
    public static ProgressDialog progressDialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //  joker card
        RelativeLayout rlCard = (RelativeLayout) root.findViewById(R.id.rlCard);
        rlCard.setOnClickListener(this);

        //  interstitial ad
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                fetchJokes();
            }
        });


        //  mini ad at the bottom of the screen
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("BC6C6B77CEF61830841859B30835E10C")
                .build();
        mAdView.loadAd(adRequest);

        //  loader
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fun is about to begin...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rlCard :

                //  check network connection
                if(new ConnectivityUtil().isConnected(getContext())) {
                    //  check whether app is loaded or not
                    if(mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                    else {
                        fetchJokes();
                    }
                }
                else {
                    Toast.makeText(getContext(), "Please check your internet connection " +
                            "and try again", Toast.LENGTH_SHORT).show();
                }

        }
    }

    private void fetchJokes() {

        progressDialog.show();
        new EndpointsAsyncTask().execute(getActivity());
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("BC6C6B77CEF61830841859B30835E10C")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
