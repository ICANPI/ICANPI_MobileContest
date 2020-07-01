package com.example.danjam.data;

import android.widget.ImageView;
import android.widget.TextView;

public class RankData {
    private TextView Achievements;
    private ImageView check;


    public TextView getAchievements() {
        return Achievements;
    }

    public void setAchievements(TextView achievements) {
        Achievements = achievements;
    }

    public ImageView getCheck() {
        return check;
    }

    public void setCheck(ImageView check) {
        this.check = check;
    }
}
