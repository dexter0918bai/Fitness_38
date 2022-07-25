package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class IntroPage extends AppCompatActivity {

    private ViewPager screenPage;
    IntroPage_Adapter introViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        screenPage =  findViewById(R.id.screen_viewpager);

        List<IntroItem> myList = new ArrayList<>();
        myList.add(new IntroItem("Type R","this is my dream car", R.drawable.pic1));
        myList.add(new IntroItem("human","this is a human" ,R.drawable.pic2));
        myList.add(new IntroItem("Bob","this is my movie", R.drawable.pic3));



        introViewPagerAdapter = new IntroPage_Adapter(this,myList);
        screenPage.setAdapter(introViewPagerAdapter);
        findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}