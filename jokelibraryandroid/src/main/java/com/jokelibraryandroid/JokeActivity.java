package com.jokelibraryandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    private static final String KEY_JOKES = "JOKES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView tvJoke = (TextView) findViewById(R.id.tvJoke);
        tvJoke.setText(getIntent().getStringExtra(KEY_JOKES));
    }
}
