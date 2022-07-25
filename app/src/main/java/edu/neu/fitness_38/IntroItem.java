package edu.neu.fitness_38;

import android.util.EventLogTags;

public class IntroItem {
    String Title, Descrip;
    int ScreenImg;

    public IntroItem(String title,String descrip, int screenImg){
        Title = title;
        Descrip = descrip;
        ScreenImg = screenImg;

    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle(){
        return Title;
    }

    public String getDescrip(){
        return Descrip;
    }

    public int getScreenImg(){
        return ScreenImg;
    }
}
