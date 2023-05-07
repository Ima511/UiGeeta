package com.learn_android.uigeeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SCROLL_POSITION_KEY = "scroll_position";
    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = findViewById(R.id.scroll_view);
        String geeta = "श्रीमद् भगवद् गीता";
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        materialToolbar.setTitleTextAppearance(this, R.style.MyMaterialToolbarStyle);
        materialToolbar.setTitle(geeta);
        if (savedInstanceState != null) {
            int scrollPosition = savedInstanceState.getInt(SCROLL_POSITION_KEY);
            mScrollView.post(() -> mScrollView.scrollTo(0, scrollPosition));
        }

        List<String> data = new ArrayList<>();

        for(int i =1 ;i <= 18; i++){
            data.add("Chapter " + i);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mainactivity);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        MyAdapter adapter = new MyAdapter(data); // data is a List<String> with the data to display
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCROLL_POSITION_KEY, mScrollView.getScrollY());
    }

}