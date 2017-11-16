package rs.com.dashboardgems;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.com.dashboardgems.databinding.AnalyticsBinding;

/**
 * Created by yasar on 9/11/17.
 */

public class AcademicsTabView extends BaseFragment {

    private AnalyticsBinding binding;
    private ViewPagerAdapter adapter;

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

                        binding.viewpager.setCurrentItem(tab.getPosition());

                        int selectedTabPosition = tab.getPosition();


                        View firstTab = ((ViewGroup) binding.tabs.getChildAt(0)).getChildAt(0);
                        View secondTab = ((ViewGroup) binding.tabs.getChildAt(0)).getChildAt(1);


//                        if (selectedTabPosition == 0)
//                        { // that means first tab
//                            firstTab.setBackground(getContext().getDrawable(R.drawable.selected));
//                            secondTab.setBackground(getDrawable(R.drawable.unselected));
//
//
//                        } else if (selectedTabPosition == 1)
//                        { // that means it's a last tab
//
//                            firstTab.setBackground(getDrawable(R.drawable.selected_tab_alternate));
//                            secondTab.setBackground(getDrawable(R.drawable.unselected_tab_alternate));
//
//
//                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        return view;
    }

    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.cardbackgroundcolor), PorterDuff.Mode.SRC_IN);
//        tabLayout.getTabAt(1).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AcademicsRegionTab(), "Region");
//        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new AcademicsSchoolTab(), "Schools");
        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(2);
    }
}
