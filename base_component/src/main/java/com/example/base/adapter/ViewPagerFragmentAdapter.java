package com.example.base.adapter;


import com.example.base.base.BaseMvpFragment;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is ViewPager的通用Adapter
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private List<BaseMvpFragment> fragmentList;
    private List<String> titleList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<BaseMvpFragment> fragmentList, List<String> titleList) {
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
