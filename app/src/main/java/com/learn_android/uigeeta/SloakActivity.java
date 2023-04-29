package com.learn_android.uigeeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class SloakActivity extends AppCompatActivity {

    private static final String SCROLL_POSITION_KEY = "scroll_position";
    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sloak);
        mScrollView = findViewById(R.id.scroll_view_sloak);

        if (savedInstanceState != null) {
            int scrollPosition = savedInstanceState.getInt(SCROLL_POSITION_KEY);
            mScrollView.post(() -> mScrollView.scrollTo(0, scrollPosition));
        }
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        TextView textView = (TextView) findViewById(R.id.text);
        String value = getIntent().getStringExtra("chapter_data");
        ArrayList<String> myArrayList = getIntent().getStringArrayListExtra("myList");
        int position = getIntent().getExtras().getInt("position");
        materialToolbar.setTitle(value);
        Log.d("Arraylist", myArrayList.get(position));
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCROLL_POSITION_KEY, mScrollView.getScrollY());
    }
}