package edu.neu.fitness_38;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Reminder extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button addReminder;
    List<ReminderObj> reminderList = new ArrayList<>();
    private DrawerLayout drawer;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        setActionBar(toolbar);

//        drawer = findViewById(R.id.main_drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.LogOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;
            case R.id.person:
                Toast.makeText(Reminder.this, "User information is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                break;
            case R.id.reminder:
                Toast.makeText(Reminder.this, "User information is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Reminder.class));
                break;
            case R.id.home:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.Share:
                Toast.makeText(Reminder.this, "This feature is under development.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.foodList:
                Toast.makeText(Reminder.this, "Food List is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FoodList.class));
                break;
            case R.id.foodSummary:
                Toast.makeText(Reminder.this, "Food summary is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FoodSummary.class));
                break;
        }
        return true;
    }
}