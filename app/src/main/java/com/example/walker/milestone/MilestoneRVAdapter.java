package com.example.walker.milestone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class MilestoneRVAdapter extends RecyclerView.Adapter<MilestoneRVAdapter.MilestoneViewHolder> {

    public static class MilestoneViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView milestoneName;
        TextView milestoneDate;
        TextView milestoneDaysLeft;

        MilestoneViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            milestoneName = (TextView)itemView.findViewById(R.id.milestone_name);
            milestoneDate = (TextView)itemView.findViewById(R.id.milestone_date);
            milestoneDaysLeft = (TextView)itemView.findViewById(R.id.milestone_days_left);
        }
    }

    List<Milestone> milestones;

    MilestoneRVAdapter(List<Milestone> milestones){
        this.milestones = milestones;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MilestoneViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_milestone, viewGroup, false);
        MilestoneViewHolder mvh = new MilestoneViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(final MilestoneViewHolder mvh, int i) {
        mvh.milestoneName.setText(milestones.get(i).name.toUpperCase());
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        Date date = milestones.get(i).date;
        Date curr = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long timeLeft = (long) date.getTime() - curr.getTime();
        int daysLeft = (int) TimeUnit.DAYS.convert(timeLeft, TimeUnit.MILLISECONDS) + 1;
        mvh.milestoneDate.setText(dateFormat.format(milestones.get(i).date));
        if (daysLeft == 1) {
            mvh.milestoneDaysLeft.setText(Integer.toString(daysLeft) + " day left");
        } else {
            mvh.milestoneDaysLeft.setText(Integer.toString(daysLeft) + " days left");
        }

    }

    @Override
    public int getItemCount() {
        return milestones.size();
    }
}