package edu.neu.fitness_38;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;

import edu.neu.fitness_38.FoodBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class FoodList extends AppCompatActivity {

    private RecyclerView rv;
    private FoodBean foodBean;
    private EditText editText;
    private List<FoodBean> allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        //设置标题
        setTitle("Food List");
        editText = findViewById(R.id.editText);

        allData = new Gson().fromJson(AllFood.foodList, new TypeToken<ArrayList<FoodBean>>() {
        }.getType());

        FoodAdapter listFoodAdapter = new FoodAdapter(allData, foodBean -> {
            //点击事件
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Your select " + foodBean.getFoodName())//设置对话框的内容
                    //设置对话框的按钮
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Integer foodCalorie = foodBean.getFoodCalorie();

                            Double fat = foodBean.getFat();
                            Double protein = foodBean.getProtein();
                            Double carb = foodBean.getCarb();

                            saveFoodCalorie(foodCalorie, fat, protein, carb);
                            Toast.makeText(FoodList.this, "You add one or 100g : " + foodBean.getFoodName(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        });

        rv = findViewById(R.id.foodItem);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(listFoodAdapter);
        //EditText 监听事件
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //
                if (editable.toString().equals("")) {
                    listFoodAdapter.refreshData(allData);
                } else {
                    String search = editable.toString();
                    List<FoodBean> searchFoodBeans = new ArrayList<>();
                    for (FoodBean foodBean : allData) {
                        if (foodBean.getFoodName().toUpperCase().contains(search.toUpperCase())) {
                            searchFoodBeans.add(foodBean);
                        }
                    }
                    listFoodAdapter.refreshData(searchFoodBeans);
                }
            }
        });

    }

    private void saveFoodCalorie(Integer foodCalorie, Double fat, Double protein, Double carb) {
        String calorie = MMKV.defaultMMKV().decodeString("calorie");
        Type type = new TypeToken<ArrayList<CalorieBean>>() {
        }.getType();
        List<CalorieBean> beans = new Gson().fromJson(calorie, type);

        for (int i = 0; i < beans.size(); i++) {
            String date = beans.get(i).getDate();
            if (TextUtils.equals(date, AllFood.TODAY)) {
                CalorieBean calorieBean = beans.get(i);
                Double food = calorieBean.getCalorie();
                Log.d("calorie", "calorie1:" + calorie);
                calorie = calorie + foodCalorie;
                calorieBean.setCalorie(food);
                Log.d("calorie", "calorie2:" + calorie);


                Double fat1 = calorieBean.getFat();
                fat1 = fat + fat1;
                calorieBean.setFat(fat1);

                Double carb1 = calorieBean.getCarb();
                carb1 = carb + carb1;
                calorieBean.setCarb(carb1);

                Double protein1 = calorieBean.getProtein();
                protein1 = protein + protein1;
                calorieBean.setProtein(protein1);

            }
        }
        String s = new Gson().toJson(beans);
        MMKV.defaultMMKV().encode("calorie", s);
    }
}