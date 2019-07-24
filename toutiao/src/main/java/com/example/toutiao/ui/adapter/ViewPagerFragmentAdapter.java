package com.example.toutiao.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.base.base.BaseFragment;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is ViewPager的通用Adapter
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragmentList;
    private List<String> titleList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<BaseFragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }


    @Override
    public Fragment getItem(int position) {
        if (fragmentList != null) {
            return fragmentList.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        }
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList != null) {
            return titleList.get(position);
        }
        return null;
    }
}
