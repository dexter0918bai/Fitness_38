package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.neu.fitness_38.databinding.ActivityFoodSummaryBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;

public class FoodSummary extends AppCompatActivity {
    ActivityFoodSummaryBinding inflate;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_summary);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        pieChart = findViewById(R.id.pieChart);
        PieData pieData = getPieData();
        showChart(pieChart, pieData);
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

    private void showChart(PieChart pieChart, PieData pieData) {
        Description description = new Description();
        description.setTextSize(26);
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setHoleRadius(60f);
        pieChart.setCenterText("Your Daily Nutrients");
        pieChart.setCenterTextSize(16f);
        pieChart.setCenterTextColor(Color.BLUE);
        pieChart.setRotationAngle(90);
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);
        Legend legend = pieChart.getLegend();
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(15f);
        pieChart.animateXY(1000, 1000);
    }

    private PieData getPieData() {
        ArrayList<String> xValues = new ArrayList<>();
        xValues.add("Carb");
        xValues.add("Protein");
        xValues.add("Fat");
        ArrayList<PieEntry> yrrayList = new ArrayList();
        getFoodsData(yrrayList);
        PieDataSet pieDataSet = new PieDataSet(yrrayList, " ");
        pieDataSet.setSliceSpace(1f);
        ArrayList<Integer> colorList = new ArrayList<>();
        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
        pieDataSet.setColors(colorList);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(15f);
        DisplayMetrics mdisplayMetrics = getResources().getDisplayMetrics();
        float px = 5 * (mdisplayMetrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);
        PieData pie = new PieData(pieDataSet);
        return pie;
    }

    private void getFoodsData(ArrayList<PieEntry> yrrayList) {

        String stepList = String.valueOf(SharePreferenceUtil.getInstance().get(this, "stepList", ""));
        ArrayList<CalorieBean> beans = new Gson().fromJson(stepList, new TypeToken<ArrayList<CalorieBean>>() {
        }.getType());
        if (beans == null || beans.size() == 0) {
            return;
        }
        for (int i = 0; i < beans.size(); i++) {
            CalorieBean stepsBean = beans.get(i);
            if (TextUtils.equals(MainActivity.Today, stepsBean.getDate())) {
                double fat = stepsBean.getFat();
                double protein = stepsBean.getProtein();
                double carb = stepsBean.getCarb();
                showChartView(fat, protein, carb, yrrayList);
            }
        }
    }

    private void showChartView(double fat, double protein, double carb, ArrayList<PieEntry> yrrayList) {
        List<Double> list = new ArrayList<>();
        if (carb > 0) {
            list.add(carb);
        }
        if (fat > 0) {
            list.add(fat);
        }
        if (protein > 0) {
            list.add(protein);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            double sum = list.stream().mapToDouble(value -> value).sum();
            List<String> collect = list.stream().map(a -> getPercent2(a, sum)).collect(Collectors.toList());

            float fCardb = Float.parseFloat(collect.get(0).replace("%", ""));
            float fFat = Float.parseFloat(collect.get(1).replace("%", ""));
            float fProtein = Float.parseFloat(collect.get(2).replace("%", ""));

            yrrayList.add(new PieEntry(fCardb));
            yrrayList.add(new PieEntry(fFat));
            yrrayList.add(new PieEntry(fProtein));

            ((TextView) findViewById(R.id.fat)).setText("Fat:" + ((long) (fat * 100000)) / 100000.0 + " g");
            ((TextView) findViewById(R.id.pro)).setText("Protein:" + ((long) (protein * 100000)) / 100000.0 + " g");
            ((TextView) findViewById(R.id.car)).setText("Carb:" + ((long) (carb * 100000)) / 100000.0 + " g");


            ((TextView) findViewById(R.id.car)).setTextColor(Color.RED);
            ((TextView) findViewById(R.id.pro)).setTextColor(Color.YELLOW);
            ((TextView) findViewById(R.id.fat)).setTextColor(Color.BLUE);
        }
    }

    public static String getPercent2(double x, double y) {
        double d1 = x * 1.0;
        double d2 = y * 1.0;
        DecimalFormat decimalFormat = new DecimalFormat("##.00%");
        return decimalFormat.format(d1 / d2);
    }

}