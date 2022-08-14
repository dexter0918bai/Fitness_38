package edu.neu.fitness_38;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.neu.fitness_38.StepCounter.StepService;
import edu.neu.fitness_38.view.CircleView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    TextView tvDate,userName,userEmail;
    CircleView calProgress;
    CircleView stepProgress;

    TextView stepTvBottom, calTvBottom;
    public static String Today = "";
    public static String TAG = "MainActivity";


    private List<CalorieBean> beans;
    private int targetStep;
    private int targetCalorie;
    private StepService stepService;
    private boolean isFinishStepTask = false;
    private boolean isSelectToday = true;

    private FirebaseUser currentUser;
    private int dateIndex;
    private List<String> dates;
    private String today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initStepService();


    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");

        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.main_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        calTvBottom = findViewById(R.id.caloriesProgress);
        tvDate = findViewById(R.id.tvDate);
        calProgress = findViewById(R.id.calCircleProgress);
        stepProgress = findViewById(R.id.stepCircleProgress);
        stepTvBottom = findViewById(R.id.stepprogress);





        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateIndex--;
                updateDate();

            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateIndex++;
                updateDate();
            }
        });

    }


    private void updateHistoryData(String date) {
        String stepList = String.valueOf(SharePreferenceUtil.getInstance().get(this, "stepList", ""));
        beans = new Gson().fromJson(stepList, new TypeToken<ArrayList<CalorieBean>>() {
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
                Log.e(TAG, "updateHistoryData: "+stepCount+"==="+calories );
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
                for (CalorieBean bean : beans) {
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


    private void initData() {
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
        beans = new Gson().fromJson(stepList, new TypeToken<ArrayList<CalorieBean>>() {
        }.getType());
        if (beans == null || beans.size() <= 0) {
            beans = new ArrayList<>();

            for (int i = 0; i < dates.size(); i++) {
                CalorieBean stepsBean = new CalorieBean();
                stepsBean.setDate(dates.get(i));
                beans.add(stepsBean);
            }
            String s = new Gson().toJson(beans);
            SharePreferenceUtil.getInstance().put(MainActivity.this, "stepList", s);
        }
        targetCalorie = (int) SharePreferenceUtil.getInstance().get(MainActivity.this, "targetCalorie", 0);
        targetStep = (int) SharePreferenceUtil.getInstance().get(MainActivity.this, "targetStep", 0);


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
        Log.d("z", "onResume");
        updateHistoryData(today);
    }


    private void updateDate() {
        String s = dates.get(dateIndex);
        tvDate.setText(s);
        updateHistoryData(s);

        isSelectToday = TextUtils.equals(s, today);
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
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "Today's burnt calorie";
                //TODO: fetch the calorie that the user has burnt today and present it (Currently 123456)

                String calorie =  "Today I'v burnt " + calTvBottom.getText().toString() +" calorie ! " + "I finished my target!";
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

            case R.id.running:
                Toast.makeText(MainActivity.this, "Running is Clicked.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SimpleRun.class));
                break;

        }
        return true;
    }

}