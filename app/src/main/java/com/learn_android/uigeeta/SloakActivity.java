package com.learn_android.uigeeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

//        String[] chapterNames = {"Arjuna Vishada Yoga", "Sankhya Yoga", "Karma Yoga", "Jnana Yoga", "Karma Vairagya Yoga",
//                "Abhyasa Yoga", "Dhyana Yoga", "Jnana Vijana Yoga", "Aksara Parabrahma Yoga", "Raja Vidya Raja Guhya Yoga",
//                "Vibhuti Yoga", "Visvarupa Darshana Yoga", "Bhakti Yoga", "Ksetra-Ksetrajna Vibhaga Yoga",
//                "Purushottama Prapti Yoga", "Daivasura Sampad Vibhaga Yoga", "Sraddhatraya Vibhaga Yoga", "Moksha Upadesa Yoga"};
//
        String[] chapterNames = {"अर्जुन विषाद योग", "सांख्य योग", "कर्म योग", "ज्ञान योग", "कर्म-वैराग्य योग",
                "अभ्यास योग", "ध्यान योग", "ज्ञान विज्ञान योग", "अक्षर परब्रह्म योग", "राज विद्या राज गुह्य योग",
                "विभूति योग", "विश्वरूप दर्शन योग", "भक्ति योग", "क्षेत्र-क्षेत्रज्ञ विभाग योग",
                "पुरुषोत्तम प्राप्ति योग", "दैवासुर सम्पद् विभाग योग", "श्रद्धात्रय विभाग योग", "मोक्ष उपदेश योग"};


        if (savedInstanceState != null) {
            int scrollPosition = savedInstanceState.getInt(SCROLL_POSITION_KEY);
            mScrollViewSloak.post(() -> mScrollViewSloak.scrollTo(0, scrollPosition));
        }
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        int value = getIntent().getIntExtra("chapter_number",0);
        materialToolbar.setTitle(chapterNames[value-1]+" ");
        materialToolbar.setTitleTextAppearance(this, R.style.MyMaterialToolbarStyle);


        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the color of the up button
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        upArrow.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_two);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SloakAdapter adapter = new SloakAdapter(slokasArray()); // data is a List<String> with the data to display
        recyclerView.setAdapter(adapter);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) { // Check if the up button was clicked
            onBackPressed(); // Navigate to the previous activity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}