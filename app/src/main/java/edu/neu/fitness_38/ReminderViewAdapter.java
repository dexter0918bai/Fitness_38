package edu.neu.fitness_38;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderViewAdapter extends RecyclerView.Adapter<ReminderViewAdapter.MyViewHolder> {

    List<ReminderObj> reminderList;
    Context context;

    public ReminderViewAdapter(List<ReminderObj> reminderList, Context context) {
        this.reminderList = reminderList;
        this.context = context;
    }

    // manage each item of a list
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // implement the recycleView to layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_reminder, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    // bind the dataSet and the holder(target Widget), pass the data being bind
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_description.setText(reminderList.get(position).getDescription());
        holder.tv_time.setText(reminderList.get(position).getTime());
        holder.tv_date.setText(String.valueOf(reminderList.get(position).getDate()));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send the control to the EditOnePage
                Intent intent = new Intent(context, AddReminderPage.class);
                intent.putExtra("id", reminderList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }


    // pass all property to target widget
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_description;
        TextView tv_date;
        TextView tv_time;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_description = itemView.findViewById(R.id.tv_desItem);
            tv_time = itemView.findViewById(R.id.tv_timeItem);
            tv_date = itemView.findViewById(R.id.tv_dateItem);
            // click any oneLinePresidentLayout would have listener
            parentLayout = itemView.findViewById(R.id.oneLineReminderLayout);
        }
    }
}