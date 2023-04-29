package com.learn_android.uigeeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        data.add("Item 4");
        data.add("Item 5");
        data.add("Item 6");
        data.add("Item 7");
        data.add("Item 8");
        data.add("Item 9");
        data.add("Item 10");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        MyAdapter adapter = new MyAdapter(data); // data is a List<String> with the data to display
        recyclerView.setAdapter(adapter);
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