package com.attep.lutemon_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.attep.lutemon_project.fragments.ArenaFragment;
import com.attep.lutemon_project.fragments.HomeFragment;
import com.attep.lutemon_project.fragments.TrainingFragment;

public class TabPagerAdapter extends FragmentStateAdapter {
    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new TrainingFragment();
        } else if (position == 1) {
            return new HomeFragment();
        } else if (position == 2){
            return new ArenaFragment();
        } else {
            return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
