package com.learn_android.uigeeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SloakActivity extends AppCompatActivity {

    private static final String SCROLL_POSITION_KEY = "scroll_position";
    private NestedScrollView mScrollViewSloak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sloak);
        mScrollViewSloak = findViewById(R.id.scroll_view_sloak);


        if (savedInstanceState != null) {
            int scrollPosition = savedInstanceState.getInt(SCROLL_POSITION_KEY);
            mScrollViewSloak.post(() -> mScrollViewSloak.scrollTo(0, scrollPosition));
        }
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        int value = getIntent().getIntExtra("chapter_number",0);
        materialToolbar.setTitle(value+" ");
//        Log.d("Arraylist", myArrayList.get(position));
//        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();


// Implement this code when data extraction completed and get the sloakes in sanskrit and hindi.
//        int index = input.indexOf("।।");
//
//// Extract the Sanskrit text
//        String sanskritText = input.substring(0, index + 2);
//
//// Extract the Hindi translation
//        String hindiTranslation = input.substring(index + 4);
//        textView.setText(sanskritText.substring(0,sanskritText.length()-2));
//        String hindi =   hindiTranslation.replaceAll("[a-zA-Z]+", "").replaceAll("\\d+", "").replace("©" , "").replace(",", "");
////        hindiText.setText(hindi.trim().substring(8));
//        System.out.println("Sanskrit text: " + sanskritText);
//        System.out.println("Hindi translation: " + hindiTranslation);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_two);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SloakAdapter adapter = new SloakAdapter(slokasArray()); // data is a List<String> with the data to display
        recyclerView.setAdapter(adapter);
//MyAdapter adapter2 = new MyAdapter(slokasArray());
//recyclerView.setAdapter(adapter2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCROLL_POSITION_KEY, mScrollViewSloak.getScrollY());
    }
    private  ArrayList slokasArray(){
        ArrayList<String> sb = new ArrayList<>();
        try {

                int chapter = getIntent().getIntExtra("chapter_number",0);
                String fileName = "chapter_" + chapter + ".json";

                // Load JSON data from assets folder
                InputStream is = getAssets().open(fileName);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, StandardCharsets.UTF_8);

                // Parse JSON data
                JSONObject jsonObject = new JSONObject(json);
                JSONArray data = jsonObject.getJSONArray("data");

                // Extract contentSanskrit attribute from each object in the data array
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    JSONObject attributes = obj.getJSONObject("attributes");
                    String contentSanskrit = attributes.getString("contentSanskrit");
                    contentSanskrit = contentSanskrit.replaceFirst("[^\\p{Alnum}]+", "");
                    sb.add(contentSanskrit);
                }



        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Log.d("Sloaks_Array",sb.size() + " ");
        Log.d("sb",sb + " ");
        return sb;
    }

}