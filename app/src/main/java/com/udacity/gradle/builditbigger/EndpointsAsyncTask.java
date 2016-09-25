package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.builditbigger.backend.myApi.MyApi;
import com.builditbigger.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jokelibraryandroid.JokeActivity;
import com.jokelibraryandroid.ParcelableString;
import com.udacity.gradle.builtitbigger.MainActivityFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurpreet on 8/18/2016.
 */

public class EndpointsAsyncTask extends AsyncTask<Activity, Void, List<MyBean>> {

    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;
    private Activity activity;


    @Override
    protected List<MyBean> doInBackground(Activity... params) {

        try {
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        //.setRootUrl("http://10.0.3.2:8080/_ah/api/"); //address of the Genymotion emulator
                        .setRootUrl("https://build-it-bigger-123123.appspot.com/_ah/api/");

                // end options for devappserver
                myApiService = builder.build();
            }

            activity = params[0];
            //return myApiService.sayHi(name).execute().getData();
            ///return myApiService.getJoke().execute().getData();

            return myApiService.getAllJokes().execute().getItems();


        } catch (IOException e) {
            //  snackbar here
            Toast.makeText(activity.getBaseContext(), "There was a problem in retrieving jokes. " +
                    "Please try again later", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
            //return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(List<MyBean> result) {

        if(MainActivityFragment.progressDialog.isShowing()) {
            MainActivityFragment.progressDialog.dismiss();
        }
        Intent myIntent = new Intent(activity, JokeActivity.class);
        Log.d(TAG, "onPostExecute: Results: "+result);
        if (result!=null&&!result.isEmpty()){
            ArrayList<ParcelableString> strings = new ArrayList<>(result.size());
            for (MyBean bean : result) {
                strings.add(new ParcelableString(bean.getData()));
            }
            myIntent.putParcelableArrayListExtra(JokeActivity.KEY_JOKES,strings);
            activity.startActivity(myIntent);
        }
    }

}
