package edu.pku.canoe.yuta.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

public class ButtonAnimation {
    public static void initButton(Button bt){
        Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setDuration(300);
        bt.startAnimation(animation);
    }
}
