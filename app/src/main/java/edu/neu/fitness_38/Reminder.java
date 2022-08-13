package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Reminder extends AppCompatActivity {
    Button addReminder;
    List<ReminderObj> reminderList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        addReminder = findViewById(R.id.addReminder_btn);

        reminderList = MyApplication.getReminderList();

        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reminder.this, AddReminderPage.class);
                startActivity(intent);
            }
        });

        // bind recycleView id - Widget
        recyclerView = findViewById(R.id.lv_reminderList);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // render the presidentList - dataSet
        myAdapter = new ReminderViewAdapter(reminderList, this);
        recyclerView.setAdapter(myAdapter);
    }
}