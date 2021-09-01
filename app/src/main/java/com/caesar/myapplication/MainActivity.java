package com.caesar.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.caesar.myapplication.ui.main.HistoryFragment;
import com.caesar.myapplication.ui.main.LinkFragment;
import com.caesar.myapplication.ui.main.SectionsPagerAdapter;
import com.caesar.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //调用生成的绑定类中包含的静态 inflate() 方法
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // 获取对根视图的引用,使其成为屏幕上的活动视图
        setContentView(binding.getRoot());
        // init fragments and add them to the fragment list
        Fragment linkFragment = LinkFragment.newInstance(1);
        Fragment historyFragment = HistoryFragment.newInstance(1);
        mFragments = new ArrayList<>();
        mFragments.add(linkFragment);
        mFragments.add(historyFragment);
        //put the fragments into the adapter
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),mFragments);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //悬浮按钮
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}