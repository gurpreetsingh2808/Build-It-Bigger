package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.builditbigger.backend.myApi.MyApi;
import com.builditbigger.backend.myApi.model.MyBean;
import com.example.Jokes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.jokelibraryandroid.JokeActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurpreet on 8/18/2016.
 */

class EndpointsAsyncTask extends AsyncTask<Activity, Void, List<Jokes>> {

    private static final String KEY_JOKES = "JOKES";
    private static MyApi myApiService = null;
    private Activity activity;
    private ArrayList<Jokes> jokesList = new ArrayList<>();


    @Override
    protected List<Jokes> doInBackground(Activity... params) {

        try {
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        //.setRootUrl("http://10.0.3.2:8080/_ah/api/") //address of the Genymotion emulator
                        .setRootUrl("https://build-it-bigger-123123.appspot.com/_ah/api/");

                // end options for devappserver
                myApiService = builder.build();
            }

            activity = params[0];
            //return myApiService.sayHi(name).execute().getData();
            List<MyBean> beanList = myApiService.getJoke().execute().getItems();
            for (MyBean bean : beanList) {
                jokesList.add(new Jokes(bean.getData()));
            }
            return jokesList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
            //return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(List<Jokes> result) {

        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(activity, JokeActivity.class);
        myIntent.putExtra(KEY_JOKES, (ArrayList)result);
        activity.startActivity(myIntent);
    }

}
