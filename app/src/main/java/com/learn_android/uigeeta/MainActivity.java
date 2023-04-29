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
        if (savedInstanceState != null) {
            int scrollPosition = savedInstanceState.getInt(SCROLL_POSITION_KEY);
            mScrollView.post(() -> mScrollView.scrollTo(0, scrollPosition));
        }

        List<String> data = new ArrayList<>();

        for(int i =1 ;i <= 18; i++){
            data.add("Chapter " + i);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        MyAdapter adapter = new MyAdapter(data); // data is a List<String> with the data to display
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCROLL_POSITION_KEY, mScrollView.getScrollY());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_item1);
        View actionView = MenuItemCompat.getActionView(menuItem);
        if (actionView != null) {
            TextView textView = actionView.findViewById(R.id.menu_item1);
            if (textView != null) {
                textView.setText(menuItem.getTitle());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textView.setTextColor(getColor(R.color.md_theme_dark_errorContainer));
                }
            }
        }

        return true;
    }

}