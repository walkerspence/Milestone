package com.example.walker.milestone;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<Integer> mImages = new ArrayList<>();
    private Context context;
    private View clickedIcon;

    public RecyclerViewAdapter(Context context, ArrayList<Integer> mImages) {
        this.mImages = mImages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Glide.with(context).load(mImages.get(i)).into(viewHolder.image);
        viewHolder.image.setImageResource(mImages.get(i));
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hi", "clicked");
                if (v.getBackground() == null) {
                    if (clickedIcon == null) {
                        clickedIcon = v;
                    } else {
                        clickedIcon.setBackground(null);
                    }
                    v.setBackgroundResource(R.drawable.image_border);
                    clickedIcon = v;
                } else {
                    v.setBackground(null);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
