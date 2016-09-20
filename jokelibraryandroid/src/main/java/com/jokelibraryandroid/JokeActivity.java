package com.jokelibraryandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

public class JokeActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener {

    private static final String KEY_JOKES = "JOKES";
    private static final String TAG = JokeActivity.class.getSimpleName();
    private ArrayList<String> mData = new ArrayList<>();
    private SwipeStackAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        SwipeStack mSwipeStack = (SwipeStack) findViewById(R.id.swipeStack);

        //mData = new ArrayList<>();

        if (getIntent().hasExtra(KEY_JOKES)) {
            mData = (getIntent().getExtras().getStringArrayList(KEY_JOKES));
            if (mSwipeStack != null && mData != null) {
                mAdapter = new SwipeStackAdapter(mData);
                mSwipeStack.setAdapter(mAdapter);
                mSwipeStack.setListener(this);
            }
            else {
                Log.d(TAG, "onCreate: data is null "+mData);
            }
        }
        else {
            Toast.makeText(JokeActivity.this, "Something wrong happened. Please try " +
                    "again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewSwipedToRight(int position) {

        String swipedElement = mAdapter.getItem(position);
        Toast.makeText(this, "view_swiped_right",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewSwipedToLeft(int position) {

        String swipedElement = mAdapter.getItem(position);
        Toast.makeText(this, "view_swiped_left",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStackEmpty() {
        Toast.makeText(this, "stack_empty", Toast.LENGTH_SHORT).show();
    }


}
