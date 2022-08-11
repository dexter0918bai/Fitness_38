package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;

import edu.neu.fitness_38.FoodBean;
import edu.neu.fitness_38.databinding.ActivityFoodSummaryBinding;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.os.Bundle;

public class FoodSummary extends AppCompatActivity {
    ActivityFoodSummaryBinding inflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflate = ActivityFoodSummaryBinding.inflate(getLayoutInflater());
        setContentView(inflate.getRoot());
        MMKV.initialize(this);
        initData();
    }

    private void initData() {
        String calorie = MMKV.defaultMMKV().decodeString("calorie");
        Type type = new TypeToken<ArrayList<CalorieBean>>() {
        }.getType();
        ArrayList<CalorieBean> fitnessBeans = new Gson().fromJson(calorie, type);
        if (fitnessBeans == null || fitnessBeans.size() <= 0) {
            return;
        }

        String today = AllFood.TODAY;
        for (int i = 0; i < fitnessBeans.size(); i++) {
            CalorieBean fitnessBean = fitnessBeans.get(i);
            if (TextUtils.equals(today, fitnessBean.getDate())) {
                Double carb = fitnessBean.getCarb();
                Double fat = fitnessBean.getFat();
                Double protein = fitnessBean.getProtein();

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
                    List<String> collect = list.stream().map(a -> getPercent(a, sum)).collect(Collectors.toList());

                    float fCard = Float.parseFloat(collect.get(0).replace("%", ""));
                    float fFat = Float.parseFloat(collect.get(1).replace("%", ""));
                    float fProtein = Float.parseFloat(collect.get(2).replace("%", ""));
                    inflate.pieChart.addItemType(new MyPieChartView.ItemType("Carb", fCard, getResources().getColor(R.color.gold)));
                    inflate.pieChart.addItemType(new MyPieChartView.ItemType("Fat", fFat, getResources().getColor(R.color.colorPrimary)));
                    inflate.pieChart.addItemType(new MyPieChartView.ItemType("Protein", fProtein, getResources().getColor(R.color.colorAccent)));

                    inflate.car.setText("Carb:" + fCard + "g");
                    inflate.fat.setText("Fat:" + fFat + "g");
                    inflate.pro.setText("Protein:" + fProtein + "g");

                }
            }
        }
    }


    public static String getPercent(double x, double y) {
        double d1 = x * 1.0;
        double d2 = y * 1.0;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        return percentInstance.format(d1 / d2);
    }
}