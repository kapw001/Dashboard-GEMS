package rs.com.dashboardgems;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rs.com.dashboardgems.adapter.SchoolAdapter;
import rs.com.dashboardgems.databinding.AcademicsregionBinding;
import rs.com.dashboardgems.databinding.RegionBinding;
import rs.com.dashboardgems.models.SchoolDetailsResult;
import rs.com.dashboardgems.models.SchoolList;
import rs.com.dashboardgems.models.Schools;

import static rs.com.dashboardgems.Utils.getSchoolDetailsResultList;

/**
 * Created by yasar on 9/11/17.
 */

public class AcademicsRegionTab extends BaseFragment implements SchoolAdapter.SchoolInterface {
    private static final String TAG = "SchoolTab";
    private List<Schools> schoolsList;
    private SchoolAdapter schoolAdapter;
    private AcademicsregionBinding binding;
    public int countSelected = 2;

    private List<SchoolList> allSchoolList = new ArrayList<>();

    private List<SchoolDetailsResult> resultList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.academicsregion, container, false);

        View view = binding.getRoot();

        resultList.addAll(getSchoolDetailsResultList(getActivity()));

        schoolsList = getSchoolList();
        schoolAdapter = new SchoolAdapter(getActivity(), this, schoolsList);

        binding.recyleView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        binding.recyleView.setItemAnimator(new DefaultItemAnimator());
        binding.recyleView.setAdapter(schoolAdapter);

        addBarChart1(schoolsList, countSelected);
        addBarChart2(schoolsList, countSelected);

        yearlyView();

        return view;
    }


    private void addBarChart1(List<Schools> list, int count) {
        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset
// (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;
        int groupCount = count;
        List<String> xAxisLabels = getSchoolName(list);
        XAxis xAxis = binding.barchart1.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

        binding.barchart1.setDescription(null);
        binding.barchart1.getLegend().setEnabled(true);
        binding.barchart1.getAxisRight().setEnabled(true);
        binding.barchart1.setPinchZoom(false);
        binding.barchart1.setDoubleTapToZoomEnabled(false);

        binding.barchart1.getAxisRight().setEnabled(false);
        YAxis leftAxis = binding.barchart1.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);

        binding.barchart1.getAxisLeft().setAxisMinimum(0f);
        binding.barchart1.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            private DecimalFormat mFormat;

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                mFormat = new DecimalFormat("###,###,##0.0");
                return "$" + mFormat.format(value) + " M";
            }
        });
        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();

//        for (int j = 0; j < 2; j++) {
//            Random random = new Random();
//            int budget = random.nextInt(500);
//            int expense = random.nextInt(100);
//            String[] s = {"Budget", "Expense"};
//            if (j == 1) {
//                iBarDataSets.add(getBarDataSet(getEntries(count, 300), j, s[j]));
//            } else {
//                iBarDataSets.add(getBarDataSet(getEntries(count, 600), j, s[j]));
//            }
//
//        }

        String[] s = {"Budget", "Expense"};
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            List<SchoolList> schoolList = resultList.get(i).getSchoolList();

            int l = (int) Double.parseDouble(String.valueOf(getBudgetByRegion(schoolList)));
            int l2 = (int) Double.parseDouble(String.valueOf(getBudgetByExpense(schoolList)));

            yVals1.add(new BarEntry(i, l));
            yVals2.add(new BarEntry(i, l2));
        }

        iBarDataSets.add(getBarDataSet(yVals1, 0, s[0]));
        iBarDataSets.add(getBarDataSet(yVals2, 1, s[1]));

        BarData data1 = binding.barchart1.getData();
        if (data1 != null) {
            binding.barchart1.clearValues();
            binding.barchart1.clear();
        }


        Legend l = binding.barchart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        BarData data = new BarData(iBarDataSets);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(0f);


        binding.barchart1.setData(data);
        binding.barchart1.getBarData().setBarWidth(barWidth);
        binding.barchart1.getXAxis().setAxisMinimum(0);
        binding.barchart1.getXAxis().setAxisMaximum(0 + binding.barchart1.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        binding.barchart1.groupBars(0, groupSpace, barSpace);
        binding.barchart1.getData().setHighlightEnabled(false);
        binding.barchart1.invalidate();
    }

    private void addBarChart2(List<Schools> list, int count) {
        float barWidth = 0.25f;
        List<String> xAxisLabels = getSchoolName(list);
        XAxis xAxis = binding.barchart2.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

        binding.barchart2.setDescription(null);
        binding.barchart2.getLegend().setEnabled(false);
        binding.barchart2.getAxisRight().setEnabled(false);
        binding.barchart2.setPinchZoom(false);
        binding.barchart2.setDoubleTapToZoomEnabled(false);

        binding.barchart2.getAxisLeft().setAxisMinimum(0f);
        binding.barchart2.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            private DecimalFormat mFormat;

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                mFormat = new DecimalFormat("###,###,##0.0");
                return "$" + mFormat.format(value) + " M";
            }
        });
        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            List<SchoolList> schoolList = resultList.get(i).getSchoolList();

            int l = (int) Double.parseDouble(String.valueOf(getBudgetByRevenue(schoolList)));
            yVals1.add(new BarEntry(i, l));

        }

        iBarDataSets.add(getBarDataSet2(yVals1, 0, ""));

//        for (int j = 0; j < 1; j++) {
//            Random random = new Random();
//            int budget = random.nextInt(500);
//            int expense = random.nextInt(100);
//            String[] s = {"Budget", "Expense"};
//            iBarDataSets.add(getBarDataSet2(getEntries(count, 500), j, s[j]));
//
//
//        }


        BarData data1 = binding.barchart2.getData();
        if (data1 != null) {
            binding.barchart2.clearValues();
            binding.barchart2.clear();
        }


//        Legend l = binding.barchart2.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(true);
//        l.setYOffset(20f);
//        l.setXOffset(0f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);

        BarData data = new BarData(iBarDataSets);
        data.setValueFormatter(new LargeValueFormatter());
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(0f);


        binding.barchart2.setData(data);
        binding.barchart2.getBarData().setBarWidth(barWidth);
//        binding.barchart1.getXAxis().setAxisMinimum(0);
//        binding.barchart1.getXAxis().setAxisMaximum(0 + binding.barchart1.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
//        binding.barchart1.groupBars(0, groupSpace, barSpace);
//        binding.barchart1.getData().setHighlightEnabled(false);
        binding.barchart2.invalidate();
    }

    private BarDataSet getBarDataSet(List<BarEntry> barEntry, int color, String s) {
        int[] co = ColorTemplate.MATERIAL_COLORS;
        BarDataSet set1 = new BarDataSet(barEntry, s);
        set1.setColor(co[color]);
        return set1;
    }

    private BarDataSet getBarDataSet2(List<BarEntry> barEntry, int color, String s) {
//        int[] co = ColorTemplate.MATERIAL_COLORS;
        BarDataSet set1 = new BarDataSet(barEntry, s);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        set1.setColors(colors);
        return set1;
    }

    private List<BarEntry> getEntries(int count, int values) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            int budget = random.nextInt(500) + values;
            yVals1.add(new BarEntry(i, budget));
        }
        return yVals1;
    }

    private List<String> getSchoolName(List<Schools> schoolsList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < schoolsList.size(); i++) {
            Schools sc = schoolsList.get(i);
            if (sc.isTrue()) {
                list.add(sc.getName());
            }
        }

        return list;
    }


    private List<Schools> getSchoolList() {

        List<Schools> list = new ArrayList<>();

        list.add(new Schools("INDIA", true, R.mipmap.ic_indiaflag, true));
        list.add(new Schools("UK", true, R.mipmap.ic_ukflag, true));
        list.add(new Schools("UAE", false, R.mipmap.ic_uaeflag, true));
        list.add(new Schools("FRANCE", false, R.mipmap.ic_franceflag, true));
        list.add(new Schools("USA", false, R.mipmap.ic_usaflag, true));

        return list;
    }


    private int getBudgetByRevenue(List<SchoolList> schoolLists) {
        int total = 0;
        for (int i = 0; i < schoolLists.size(); i++) {
            total = (int) (total + schoolLists.get(i).getRevenue());
        }
        return total;
    }

    private int getBudgetByRegion(List<SchoolList> schoolLists) {
        int total = 0;
        for (int i = 0; i < schoolLists.size(); i++) {
            total = (int) (total + schoolLists.get(i).getBudget());
        }
        return total;
    }

    private int getBudgetByExpense(List<SchoolList> schoolLists) {
        int total = 0;
        for (int i = 0; i < schoolLists.size(); i++) {
            total = (int) (total + schoolLists.get(i).getExpense());
        }
        return total;
    }


    @Override
    public void position(int pos, Schools schools, int count) {
        countSelected = countSelected + count;
        addBarChart1(schoolsList, countSelected);
        addBarChart2(schoolsList, countSelected);
        yearlyView();
        Log.e(TAG, "position: " + countSelected);
    }

    private void yearlyView() {
        List<String> list = getSchoolName(schoolsList);

        binding.indiabudget.setVisibility(View.GONE);
        binding.ukbudget.setVisibility(View.GONE);
        binding.uaebudget.setVisibility(View.GONE);


        int ukspent = getBudgetByExpense(resultList.get(1).getSchoolList());
        int indiaspent = getBudgetByExpense(resultList.get(0).getSchoolList());
        int uaespent = getBudgetByExpense(resultList.get(2).getSchoolList());

        int ukremain = getBudgetByRegion(resultList.get(1).getSchoolList()) - ukspent;
        int indiaremain = getBudgetByRegion(resultList.get(0).getSchoolList()) - indiaspent;
        int uaeremain = getBudgetByRegion(resultList.get(2).getSchoolList()) - uaespent;

        binding.ukspent.setText("$" + ukspent + "");
        binding.indiaspent.setText("$" + indiaspent + "");
        binding.uaespent.setText("$" + uaespent + "");

        binding.ukremaining.setText("$" + ukremain + "");
        binding.indiaremaining.setText("$" + indiaremain + "");
        binding.uaeremaining.setText("$" + uaeremain + "");

        for (int i = 0; i < list.size(); i++) {

            String name = list.get(i);

            if (name.toLowerCase().equalsIgnoreCase("uk".toLowerCase())) {
                binding.ukbudget.setVisibility(View.VISIBLE);
            }
            if (name.toLowerCase().equalsIgnoreCase("india".toLowerCase())) {
                binding.indiabudget.setVisibility(View.VISIBLE);
            }

            if (name.toLowerCase().equalsIgnoreCase("uae".toLowerCase())) {
                binding.uaebudget.setVisibility(View.VISIBLE);
            }

        }
    }
}
