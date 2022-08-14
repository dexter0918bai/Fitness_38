package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SimpleRun extends AppCompatActivity {

    private CalorieBean stepsBean;
    private float curCalories;
    private int second = 1;

    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_run);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView cal = findViewById(R.id.cal);
        TextView time = findViewById(R.id.time);
        Button start = findViewById(R.id.start);

        String stepList = String.valueOf(SharePreferenceUtil.getInstance().get(this, "stepList", ""));
        ArrayList<CalorieBean> beans = new Gson().fromJson(stepList, new TypeToken<ArrayList<CalorieBean>>() {
        }.getType());

        for (int i = 0; i < beans.size(); i++) {
            if (TextUtils.equals(MainActivity.Today, beans.get(i).getDate())) {
                stepsBean = beans.get(i);
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFinish) {
                    return;
                }
                isFinish = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (isFinish) {
                                Thread.sleep(1000);
                                runOnUiThread(() -> {
                                    int i = second++;
                                    time.setText("Time:" + i + "seconds");
                                    curCalories = curCalories + 0.12f;
                                    cal.setText("Calories:" + decimalFormat.format(curCalories) + "cal");
                                });
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curCalories > 0.01) {
                    isFinish = false;
                    float calories = stepsBean.getCalories();
                    stepsBean.setCalories(calories - curCalories);
                    String s = new Gson().toJson(beans);
                    SharePreferenceUtil.getInstance().put(SimpleRun.this, "stepList", s);
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}