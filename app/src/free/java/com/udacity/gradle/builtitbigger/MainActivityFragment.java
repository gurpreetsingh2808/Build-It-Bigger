package com.udacity.gradle.builtitbigger;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.example.Jokes;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private InterstitialAd mInterstitialAd;
    private RelativeLayout rlCard;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        rlCard = (RelativeLayout) root.findViewById(R.id.rlCard);
        rlCard.setOnClickListener(this);

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


        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("BC6C6B77CEF61830841859B30835E10C")
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rlCard :
                ///////rlCard.setEnabled(false);
                //  check whether app is loaded or not
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    fetchJokes();
                }
        }
    }

    private void fetchJokes() {
        new EndpointsAsyncTask().execute(getActivity());
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("BC6C6B77CEF61830841859B30835E10C")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
