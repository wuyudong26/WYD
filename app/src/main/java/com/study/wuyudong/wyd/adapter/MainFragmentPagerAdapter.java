package com.study.wuyudong.wyd.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.study.wuyudong.wyd.activity.MainActivity;
import com.study.wuyudong.wyd.fragment.MainFirstFragment;
import com.study.wuyudong.wyd.fragment.MainForthFragment;
import com.study.wuyudong.wyd.fragment.MainSecondFragment;
import com.study.wuyudong.wyd.fragment.MainThirdFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private MainFirstFragment mainFirstFragment = null;
    private MainSecondFragment mainSecondFragment = null;
    private MainThirdFragment mainThirdFragment = null;
    private MainForthFragment mainForthFragment = null;



    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mainFirstFragment = new MainFirstFragment();
        mainSecondFragment = new MainSecondFragment();
        mainThirdFragment = new MainThirdFragment();
        mainForthFragment = new MainForthFragment();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = mainFirstFragment;
                break;
            case MainActivity.PAGE_TWO:
                fragment = mainSecondFragment;
                break;
            case MainActivity.PAGE_THREE:
                fragment = mainThirdFragment;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = mainForthFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
