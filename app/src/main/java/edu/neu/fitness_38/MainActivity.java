package edu.neu.fitness_38;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.neu.fitness_38.StepCounter.StepService;
import edu.neu.fitness_38.view.CircleView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    TextView tvDate;
    CircleView calProgress;
    CircleView stepProgress;
    TextView stepTvBottom, calTvBottom;
    public static String Today = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.main_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    private List<StepBean> beans;
    private int targetStep;
    private int targetCalorie;
    private StepService stepService;
    private boolean isFinishStepTask = false;
    private boolean isSelectToday = true;

    private int dateIndex;
    private List<String> dates;
    private String today;


    private void updateHistoryData(String date) {
        String stepList = String.valueOf(SharePreferenceUtil.getInstance().get(this, "stepList", ""));
        beans = new Gson().fromJson(stepList, new TypeToken<ArrayList<StepBean>>() {
        }.getType());
        if (beans == null || beans.size() <= 0) {
            return;
        }
        boolean isSelect = false;
        for (int i = 0; i < beans.size(); i++) {
            if (TextUtils.equals(date, beans.get(i).getDate())) {
                int stepCount = beans.get(i).getStepCount();
                float calories = beans.get(i).getCalories();
                isSelect = true;
                updateStepCircleProgress(stepCount);
                updateCalCircleProgress(calories);
            }
        }
        if (!isSelect) {
            updateStepCircleProgress(0);
            updateCalCircleProgress(0);
        }
    }

    private void updateCalCircleProgress(float calories) {
        float vv = (float) calories / (float) targetCalorie * 100;
        calProgress.SetCurrent(vv);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        calTvBottom.setText(decimalFormat.format(calories) + "/" + targetCalorie);
    }

    private void updateStepCircleProgress(int stepCount) {
        float vv = (float) stepCount / (float) targetStep * 100;
        stepProgress.SetCurrent(vv);
        stepTvBottom.setText(stepCount + "/" + targetStep);
        isFinishStepTask = vv >= 100;
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            StepService.StepBinder ssBinder = (StepService.StepBinder) iBinder;
            stepService = ssBinder.getService();
            stepService.setCallback(step -> {
                if (isFinishStepTask) {
                    return;
                }
                if (!isSelectToday) {
                    return;
                }
                for (StepBean bean : beans) {
                    if (TextUtils.equals(today, bean.getDate())) {
                        bean.setStepCount(step);
                    }
                }
                String s = new Gson().toJson(beans);
                SharePreferenceUtil.getInstance().put(MainActivity.this, "stepList", s);
                updateStepCircleProgress(step);
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private void initStepService() {
        Intent intent = new Intent(this, StepService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
        startService(intent);
    }


    private void initDate() {
        dates =
                Date.getData();

        today = (String) DateFormat.format("MM-dd", System.currentTimeMillis());
        Today = today;
        tvDate.setText(today);

        for (int i = 0; i < dates.size(); i++) {
            String date = dates.get(i);
            if (TextUtils.equals(date, today)) {
                dateIndex = i;
            }
        }

        String stepList = String.valueOf(SharePreferenceUtil.getInstance().get(this, "stepList", ""));
        beans = new Gson().fromJson(stepList, new TypeToken<ArrayList<StepBean>>() {
        }.getType());
        if (beans == null || beans.size() <= 0) {
            beans = new ArrayList<>();

            for (int i = 0; i < dates.size(); i++) {
                StepBean stepsBean = new StepBean();
                stepsBean.setDate(dates.get(i));
                beans.add(stepsBean);
            }
            String s = new Gson().toJson(beans);
            SharePreferenceUtil.getInstance().put(MainActivity.this, "stepList", s);
        }

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("zzzzzzzz", "onResume");
        updateHistoryData(today);
    }


    private void updateDate() {
        String s = dates.get(dateIndex);
        tvDate.setText(s);
        updateHistoryData(s);

        isSelectToday = TextUtils.equals(s, today);
    }


    @SuppressLint("NonConstantResourceId")
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
                Toast.makeText(MainActivity.this, "User information is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                break;
            case R.id.reminder:
                Toast.makeText(MainActivity.this, "User information is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Reminder.class));
                break;
            case R.id.home:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.Share:
                // Toast.makeText(MainActivity.this, "This feature is under development.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "Today's burnt calorie";
                //TODO: fetch the calorie that the user has burnt today and present it (Currently 123456)
                String Sub = getResources().getString(R.string.calorie);
                String calorie = String.format(Sub, 123456);
                intent.putExtra(Intent.EXTRA_SUBJECT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, calorie);
                startActivity(Intent.createChooser(intent, "Share Calorie"));
                break;
            case R.id.foodList:
                Toast.makeText(MainActivity.this, "Food List is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FoodList.class));
                break;
            case R.id.foodSummary:
                Toast.makeText(MainActivity.this, "Food summary is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), FoodSummary.class));
                break;
        }
        return true;
    }

}