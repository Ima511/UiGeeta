package com.learn_android.uigeeta;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mData;

    public MyAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mData.get(position), R.drawable.lord_krihna);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform any action you want to do when the image is clicked

//                Toast.makeText(v.getContext(), "Clicked on " + mData.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),SloakActivity.class);
                intent.putExtra("chapter_data",mData.get(position));
                intent.putExtra("position",position);
                intent.putStringArrayListExtra("myList", (ArrayList<String>) mData);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_text);
            mImageView = itemView.findViewById(R.id.item_image);
        }

        public void bindData(String data, int imageResource) {
            mTextView.setText(data);
            mImageView.setImageResource(imageResource);
        }
    }


}
