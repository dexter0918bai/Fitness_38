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
        myList.add(new IntroItem("Know your food better","Thirty-Eight fitness provides a clear pie chart that contains the most significant nutrient to help you know what you eat daily, it's easy to understand! "
                , R.drawable.nur));
        myList.add(new IntroItem("Not only Food","You could record each of your running, it will automatically transfer to step and calorie!"
                ,R.drawable.running));
        myList.add(new IntroItem("Reminder Yourself","Thirty-Eight also could help you remember the important thing will happen later ! Don't forget all you things! "
                , R.drawable.remi));



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