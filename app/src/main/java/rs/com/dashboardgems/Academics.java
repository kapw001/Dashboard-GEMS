package rs.com.dashboardgems;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.com.dashboardgems.databinding.AcademicsBinding;

/**
 * Created by yasar on 9/11/17.
 */

public class Academics extends BaseFragment {

    private AcademicsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.academics, container, false);

        View view = binding.getRoot();

        return view;
    }
}
