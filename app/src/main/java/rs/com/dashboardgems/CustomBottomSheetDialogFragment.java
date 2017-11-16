package rs.com.dashboardgems;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import rs.com.dashboardgems.databinding.BottomBinding;
import rs.com.dashboardgems.models.SchoolList;

/**
 * Created by yasar on 15/11/17.
 */

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomBinding binding;


    public static CustomBottomSheetDialogFragment getInstance(SchoolList schoolList, boolean isRed) {
        CustomBottomSheetDialogFragment customBottomSheetDialogFragment = new CustomBottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("details", schoolList);
        bundle.putBoolean("isRed", isRed);
        customBottomSheetDialogFragment.setArguments(bundle);
        return customBottomSheetDialogFragment;
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.bottom, container, false);
        View view = binding.getRoot();
//        View view = inflater.inflate(R.layout.bottom, container, false);

        SchoolList schoolList = (SchoolList) getArguments().getSerializable("details");
        boolean isRed = getArguments().getBoolean("isRed");

        MapDetailsShow mapDetailsShow = new MapDetailsShow();
        binding.setMds(mapDetailsShow);

        mapDetailsShow.setSchoolName(schoolList.getName());
        mapDetailsShow.setTotalStudents(String.valueOf(schoolList.getTotalStudent()));
        mapDetailsShow.setRevenueforcurrentyear(String.valueOf(schoolList.getRevenue()) + " Mn");
        mapDetailsShow.setExpenses(String.valueOf(schoolList.getExpense()) + " Mn");

        double r = Double.parseDouble(String.valueOf(schoolList.getRevenue()));
        double e = Double.parseDouble(String.valueOf(schoolList.getExpense()));

        double t = r - e;

        long tt = Math.round(t);

        mapDetailsShow.setTotalProfit(String.valueOf(tt) + "%");

        Random random = new Random();
        int n = random.nextInt(100);

        mapDetailsShow.setVacantPositions(String.valueOf(n));
//        int n1 = random.nextInt(10);

        mapDetailsShow.setTeacherStudentRatio("12:1");

        if (isRed) {
            binding.expenseTitle.setTextColor(Color.RED);
            binding.expenses.setTextColor(Color.RED);
        } else {
            binding.expenseTitle.setTextColor(Color.BLACK);
            binding.expenses.setTextColor(Color.BLACK);
            binding.expenseTitle.setAlpha(.5f);
            binding.expenses.setAlpha(.5f);
        }

        return view;

    }
}
