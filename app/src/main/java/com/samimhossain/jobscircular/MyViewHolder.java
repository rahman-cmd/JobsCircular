package com.samimhossain.jobscircular;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView titleText;
    TextView deadlineText;
    TextView appStartText;
    SpinKitView spinKitView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.titleId);
        deadlineText = (TextView) itemView.findViewById(R.id.dadelineId);
        appStartText = (TextView) itemView.findViewById(R.id.startDateId);

    }
}
