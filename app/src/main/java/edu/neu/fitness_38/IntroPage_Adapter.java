package edu.neu.fitness_38;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroPage_Adapter extends PagerAdapter {

    Context myContext;
    List<IntroItem> myListScreen;


    public IntroPage_Adapter(Context myContext, List<IntroItem> myListScreen) {
        this.myContext = myContext;
        this.myListScreen = myListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);
        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView descip = layoutScreen.findViewById(R.id.intro_descrip);

        title.setText(myListScreen.get(position).getTitle());
        descip.setText(myListScreen.get(position).getDescrip());
        imgSlide.setImageResource(myListScreen.get(position).getScreenImg());

        container.addView(layoutScreen);
        return layoutScreen;

    }

    @Override
    public int getCount() {
        return myListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object );
    }
}
