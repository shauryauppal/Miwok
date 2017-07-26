package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by shaurya on 26/7/17.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    public SimpleFragmentPagerAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new NumberFragment();
        else if(position ==1)
            return new FamilyFragment();
        else if(position ==2)
            return new ColorFragment();
        else{
            return new PhraseFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
