package edu.pku.canoe.yuta;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import edu.pku.canoe.yuta.fragments.Dictionary;
import edu.pku.canoe.yuta.fragments.ShortVideo;
import edu.pku.canoe.yuta.fragments.Social;
import edu.pku.canoe.yuta.fragments.Video;
import edu.pku.canoe.yuta.fragments.Yuta;

public class ShowActivity extends AppCompatActivity {
    private int lastIndex = 0;
    private List<Fragment> mFragments;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        bottomNavigationView = findViewById(R.id.bottomNavigationView_new);
        initBottomNavigation();
        initData();
    }

    private void initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_yuta:
                        setFragmentPosition(0);
                        return true;
                    case R.id.menu_item_dictionary:
                        setFragmentPosition(1);
                        return true;
                    case R.id.menu_item_video:
                        setFragmentPosition(2);
                        return true;
                    case R.id.menu_item_short_video:
                        setFragmentPosition(3);
                        return true;
                    case R.id.menu_item_social:
                        setFragmentPosition(4);
                        return true;
                }
                return false;
            }
        });

    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new Yuta());
        mFragments.add(new Dictionary());
        mFragments.add(new Video());
        mFragments.add(new ShortVideo());
        mFragments.add(new Social());
        setFragmentPosition(0);
    }

    private void setFragmentPosition(int postion) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(postion);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = postion;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.ll_frameLayout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }



}
