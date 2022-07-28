package edu.neu.fitness_38;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class IntroPage extends AppCompatActivity {

    private ViewPager screenPage;
    IntroPage_Adapter introViewPagerAdapter;
    Button next,getStart;
    int position = 0;
    Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (watched()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_intro_page);


        getSupportActionBar().hide();

        next = findViewById(R.id.next);
        getStart = findViewById(R.id.getStart);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.get_startbutton);

        screenPage =  findViewById(R.id.screen_viewpager);

        List<IntroItem> myList = new ArrayList<>();
        myList.add(new IntroItem("Type R","this is my dream car", R.drawable.pic1));
        myList.add(new IntroItem("human","this is a human" ,R.drawable.pic2));
        myList.add(new IntroItem("Bob","this is my movie", R.drawable.pic3));



        introViewPagerAdapter = new IntroPage_Adapter(this,myList);
        screenPage.setAdapter(introViewPagerAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position =  screenPage.getCurrentItem();
                if(position < myList.size()) {
                    position++;
                    screenPage.setCurrentItem(position);
                }

                if(position == myList.size() - 1) {
                    loadLastScreen();
                }
            }
        });

        getStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                alreadyWatch();
            }
        });

        findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    private boolean watched() {
        SharedPreferences pre = getApplicationContext().getSharedPreferences("watched", MODE_PRIVATE);
        Boolean isIntroWatched = pre.getBoolean("Already Watch", false);
        return isIntroWatched;
    }

    private void alreadyWatch() {
        SharedPreferences pre = getApplicationContext().getSharedPreferences("watched" , MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putBoolean("Already Watch ", true);
        editor.commit();
    }


    private void loadLastScreen() {

        next.setVisibility(View.INVISIBLE);
        getStart.setVisibility(View.VISIBLE);
        getStart.setAnimation(animation);

    }
}