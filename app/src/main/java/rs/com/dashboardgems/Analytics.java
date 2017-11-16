package rs.com.dashboardgems;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.com.dashboardgems.databinding.AnalyticsBinding;

/**
 * Created by yasar on 9/11/17.
 */

public class Analytics extends BaseFragment {

    private AnalyticsBinding binding;
    private ViewPagerAdapter adapter;
    private int[] tabIcons = {
            R.mipmap.ic_region,
            R.mipmap.ic_tabschool
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.analytics, container, false);

        View view = binding.getRoot();

        binding.tabs.setupWithViewPager(binding.viewpager);
        setupViewPager(binding.viewpager);

        binding.tabs.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(binding.viewpager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);

                        int tabIconColor = ContextCompat.getColor(getContext(), R.color.colorPrimary);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getContext(), R.color.black);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
        setupTabIcons();

        return view;
    }

    //
    private void setupTabIcons() {
        binding.tabs.getTabAt(0).setIcon(tabIcons[0]);
        binding.tabs.getTabAt(1).setIcon(tabIcons[1]);
        binding.tabs.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        binding.tabs.getTabAt(1).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.black), PorterDuff.Mode.SRC_IN);
//        binding.tabs.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.cardbackgroundcolor), PorterDuff.Mode.SRC_IN);
//        binding.tabs.getTabAt(1).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new RegionTab(), "Region");
//        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new SchoolTab(), "Schools");
        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(2);
    }
}
