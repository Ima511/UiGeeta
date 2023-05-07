package com.learn_android.uigeeta;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SloakAdapter extends RecyclerView.Adapter<SloakAdapter.ViewHolder> {

    private List<String> mData;

    public SloakAdapter(List<String> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sloak_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String sloak = "श्लोक";
        Log.d("mData",mData + "");

        String input = mData.get(position);

        int index = input.indexOf("।।");

// Extract the Sanskrit text
        String sanskritText = input.substring(0, index + 2);

// Extract the Hindi translation
        String hindiTranslation = input.substring(index + 4);
        holder.textViewSanskrit.setText(sloak + " "+ (position + 1)+"\n"+ sanskritText.substring(0,sanskritText.length()-2));
        String hindi =   hindiTranslation.replaceAll("[a-zA-Z]+", "").replaceAll("\\d+", "").replace("©" , "").replace(",", "");
        System.out.println("Sanskrit text: " + sanskritText);
        System.out.println("Hindi translation: " + hindiTranslation);
        holder.textViewHindi.setText(hindi.trim().substring(8));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSanskrit,textViewHindi ;
        public ViewHolder(View itemView) {
            super(itemView);
             textViewSanskrit =  itemView.findViewById(R.id.sloak_item_sanskrit);
             textViewHindi = itemView.findViewById(R.id.sloak_item_hindi);
        }
    }
}

