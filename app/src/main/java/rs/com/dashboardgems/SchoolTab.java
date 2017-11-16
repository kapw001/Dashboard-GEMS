package rs.com.dashboardgems;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rs.com.dashboardgems.adapter.SchoolAdapter;
import rs.com.dashboardgems.databinding.RegionBinding;
import rs.com.dashboardgems.databinding.SchoolsBinding;
import rs.com.dashboardgems.models.SchoolDetailsResult;
import rs.com.dashboardgems.models.SchoolList;
import rs.com.dashboardgems.models.Schools;

import static rs.com.dashboardgems.Utils.getSchoolDetailsResultList;

/**
 * Created by yasar on 9/11/17.
 */

public class SchoolTab extends BaseFragment implements SchoolAdapter.SchoolInterface {
    private static final String TAG = "SchoolTab";
    private SchoolsBinding binding;
    private List<Schools> schoolsList;
    private SchoolAdapter schoolAdapter;
    private int countSelected = 2;
    private List<SchoolList> allSchoolList = new ArrayList<>();

    private List<SchoolDetailsResult> resultList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.schools, container, false);
        View view = binding.getRoot();
        resultList.addAll(getSchoolDetailsResultList(getActivity()));

        schoolsList = getSchoolList();
        schoolAdapter = new SchoolAdapter(getActivity(), this, schoolsList);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(getSpan(), StaggeredGridLayoutManager.VERTICAL);


//        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.HORIZONTAL, false);
        binding.recyleView.setLayoutManager(layoutManager);
        binding.recyleView.setItemAnimator(new DefaultItemAnimator());
        binding.recyleView.setAdapter(schoolAdapter);


//        binding.recyleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                //get first visible item
////                View firstVisibleItem = layoutManager.findViewByPosition(layoutManager.findFirstVisibleItemPosition());
////
////                int leftScrollXCalculated = 0;
////                if (firstItemPosition == 0) {
////                    //if first item, get width of headerview (getLeft() < 0, that's why I Use Math.abs())
////                    leftScrollXCalculated = Math.abs(firstVisibleItem.getLeft());
////                } else {
////
////                    //X-Position = Gap to the right + Number of cells * width - cell offset of current first visible item
////                    //(mHeaderItemWidth includes already width of one cell, that's why I have to subtract it again)
////                    leftScrollXCalculated = (mHeaderItemWidth - mCellWidth) + firstItemPosition * mCellWidth + firstVisibleItem.getLeft();
////                }
//
////                Log.i("asdf", "calculated X to left = " + leftScrollXCalculated);
//
//            }
//        });


        addBarChart1(schoolsList, countSelected);
        addBarChart2(schoolsList, countSelected);
        addBarChart3(schoolsList, countSelected);
        addPieChartData(schoolsList, countSelected);

        return view;
    }

    public int getSpan() {
        int orientation = getScreenOrientation(getActivity());
        if (isTV(getActivity())) return 4;
        if (isTablet(getActivity()))
            return orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3;
        return orientation == Configuration.ORIENTATION_PORTRAIT ? 2 : 3;
    }


    public static boolean isTV(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2 && ((UiModeManager) context
                .getSystemService(Context.UI_MODE_SERVICE))
                .getCurrentModeType() == Configuration.UI_MODE_TYPE_TELEVISION;
    }

    public static int getScreenOrientation(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels <
                context.getResources().getDisplayMetrics().heightPixels ?
                Configuration.ORIENTATION_PORTRAIT : Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void addPieChartData(List<Schools> list, int count) {

        // pieChart.setDescription("Sales by employee (In Thousands $) ");
//        pieChart.setRotationEnabled(true);
//        //pieChart.setUsePercentValues(true);
//        //pieChart.setHoleColor(Color.BLUE);
//        //pieChart.setCenterTextColor(Color.BLACK);
//        pieChart.setHoleRadius(25f);
//        pieChart.setTransparentCircleAlpha(0);
//        pieChart.setCenterText("Revenue by region");
//        pieChart.setCenterTextSize(10);
        //pieChart.setDrawEntryLabels(true);
//pieChart.setEntryLabelTextSize(20);

        binding.piechart.setUsePercentValues(true);
        binding.piechart.getDescription().setEnabled(false);
        binding.piechart.setExtraOffsets(5, 10, 5, 5);

        binding.piechart.setDragDecelerationFrictionCoef(0.95f);

//        pieChart.setCenterTextTypeface(mTfLight);
//        pieChart.setCenterText(generateCenterSpannableText());

        binding.piechart.setDrawHoleEnabled(false);
        binding.piechart.setHoleColor(Color.WHITE);

        binding.piechart.setTransparentCircleColor(Color.WHITE);
        binding.piechart.setTransparentCircleAlpha(110);

        binding.piechart.setHoleRadius(35f);
        binding.piechart.setTransparentCircleRadius(38f);

        binding.piechart.setDrawCenterText(true);

        binding.piechart.setRotationAngle(0);
        // enable rotation of the chart by touch
        binding.piechart.setRotationEnabled(true);
        binding.piechart.setHighlightPerTapEnabled(true);

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        List<String> xEntrys = getSchoolName(list);
        xEntrys.add("Others");


        for (int i = 0; i < count + 1; i++) {

            Random random = new Random();
            int n = random.nextInt(500);
//            Resources res = getResources();
//            Drawable drawable = res.getDrawable(R.drawable.ic_menu_camera);
            yEntrys.add(new PieEntry(n, xEntrys.get(i)));
        }


        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");

        pieDataSet.setSliceSpace(3f);
        pieDataSet.setIconsOffset(new MPPointF(0, 30));
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setValueTextColor(Color.BLUE);

//        pieDataSet.setSliceSpace(5);
//        pieDataSet.setValueTextSize(12);
//        pieDataSet.setValueTextColor(Color.BLACK);
//        pieDataSet.setVisible(true);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = binding.piechart.getLegend();
        legend.setEnabled(true);
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(8f);
        pieData.setValueTextColor(Color.WHITE);
        binding.piechart.setEntryLabelColor(Color.WHITE);
        binding.piechart.setEntryLabelTextSize(8f);
        binding.piechart.setDrawEntryLabels(false);
        binding.piechart.setData(pieData);
        binding.piechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        binding.piechart.invalidate();
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
        String[] s = {"Budget", "Expense"};
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        for (int i = 0; i < allSchoolList.size(); i++) {
            SchoolList schoolList = allSchoolList.get(i);

            int l = (int) Double.parseDouble(String.valueOf(schoolList.getBudget()));
            int l2 = (int) Double.parseDouble(String.valueOf(schoolList.getExpense()));

            Log.e(TAG, "addBarChart1: " + l + "   " + l2);

            yVals1.add(new BarEntry(i, l));
            yVals2.add(new BarEntry(i, l2));
        }

        iBarDataSets.add(getBarDataSet(yVals1, 0, s[0]));
        iBarDataSets.add(getBarDataSet(yVals2, 1, s[1]));

//            Random random = new Random();
//            int budget = random.nextInt(500);
//            int expense = random.nextInt(100);
//
//            if (j == 1) {
//                iBarDataSets.add(getBarDataSet(getEntries(count, 300), j, s[j]));
//            } else {
//                iBarDataSets.add(getBarDataSet(getEntries(count, 600), j, s[j]));
//            }

//        }


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
            SchoolList schoolList = allSchoolList.get(i);

            int l = (int) Double.parseDouble(String.valueOf(schoolList.getRevenue()));
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


    private void addBarChart3(List<Schools> list, int count) {
        float barWidth = 0.25f;
        List<String> xAxisLabels = getSchoolName(list);
        XAxis xAxis = binding.barchart3.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabels));

        binding.barchart3.setDescription(null);
        binding.barchart3.getLegend().setEnabled(false);
        binding.barchart3.getAxisRight().setEnabled(false);
        binding.barchart3.setPinchZoom(false);
        binding.barchart3.setDoubleTapToZoomEnabled(false);

        binding.barchart3.getAxisLeft().setAxisMinimum(0f);

        binding.barchart3.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            private DecimalFormat mFormat;

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                mFormat = new DecimalFormat("###,###,##0.0");
                return mFormat.format(value) + " %";
            }
        });

        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            SchoolList schoolList = allSchoolList.get(i);

            int l = (int) Double.parseDouble(String.valueOf(schoolList.getRetention()));
            yVals1.add(new BarEntry(i, l));

        }

        iBarDataSets.add(getBarDataSet2(yVals1, 0, ""));

//        for (int j = 0; j < 1; j++) {
//            Random random = new Random();
//            int budget = random.nextInt(500);
//            int expense = random.nextInt(100);
//            String[] s = {"Budget", "Expense"};
//            iBarDataSets.add(getBarDataSet3(getEntries(count, 500), j, s[j]));
//
//
//        }


        BarData data1 = binding.barchart3.getData();
        if (data1 != null) {
            binding.barchart3.clearValues();
            binding.barchart3.clear();
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
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(0f);


        binding.barchart3.setData(data);
        binding.barchart3.getBarData().setBarWidth(barWidth);
//        binding.barchart1.getXAxis().setAxisMinimum(0);
//        binding.barchart1.getXAxis().setAxisMaximum(0 + binding.barchart1.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
//        binding.barchart1.groupBars(0, groupSpace, barSpace);
//        binding.barchart1.getData().setHighlightEnabled(false);
        binding.barchart3.invalidate();
    }

    private BarDataSet getBarDataSet(List<BarEntry> barEntry, int color, String s) {
        int[] co = ColorTemplate.JOYFUL_COLORS;
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


    private BarDataSet getBarDataSet3(List<BarEntry> barEntry, int color, String s) {
//        int[] co = ColorTemplate.MATERIAL_COLORS;
        BarDataSet set1 = new BarDataSet(barEntry, s);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
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


    private List<BarEntry> getEntriesBudgetExpense(int count, int values) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            int budget = random.nextInt(500) + values;
            yVals1.add(new BarEntry(i, budget));
        }
        return yVals1;
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
                list.add(sc.getShortName());
                Log.e(TAG, "getSchoolName: " + sc.getShortName());
            }
        }

        return list;
    }


    private List<Schools> getSchoolList() {

        List<Schools> list = new ArrayList<>();

//        Log.e(TAG, "getSchoolList: " + resultList.size());
        for (int i = 0; i < resultList.size(); i++) {

            List<SchoolList> schoolLists = resultList.get(i).getSchoolList();
            allSchoolList.addAll(schoolLists);

            for (int j = 0; j < schoolLists.size(); j++) {
                SchoolList schoolList = schoolLists.get(j);
                String s = schoolList.getName();
//                Log.e(TAG, "getSchoolList: " + s);
                list.add(new Schools(s, Utils.getFirstLetter(s), false));
            }

        }

        list.get(0).setTrue(true);
        list.get(1).setTrue(true);


//        list.add(new Schools("GEMS International School - Karnal", Utils.getFirstLetter("GEMS International School - Karnal"), true));
//        list.add(new Schools("GEMS International School (ISP)-Gurgaon", Utils.getFirstLetter("GEMS International School (ISP)-Gurgaon"), true));
//        list.add(new Schools("GEMS Modern Academy- Gurgaon", Utils.getFirstLetter("GEMS Modern Academy- Gurgaon")));
//        list.add(new Schools("GEMS Public School - Anantnag", Utils.getFirstLetter("GEMS Public School - Anantnag")));
//        list.add(new Schools("GEMS Public School - Bathinda", Utils.getFirstLetter("GEMS Public School - Bathinda")));
//        list.add(new Schools("GEMS Public School - Bhopal", Utils.getFirstLetter("GEMS Public School - Bhopal")));
//        list.add(new Schools("GEMS Public School - Guntur", Utils.getFirstLetter("GEMS Public School - Guntur")));
//        list.add(new Schools("GEMS Didcot Primary Academy-London, United Kingdom", Utils.getFirstLetter("GEMS Didcot Primary Academy-London, United Kingdom")));
//        list.add(new Schools("Sherfield School-Sherfield-on-Loddon, United Kingdom", Utils.getFirstLetter("Sherfield School-Sherfield-on-Loddon, United Kingdom")));
//        list.add(new Schools("GEMS Twickenham Primary Academy", Utils.getFirstLetter("GEMS Twickenham Primary Academy")));
//        list.add(new Schools("Al Khaleej National School", Utils.getFirstLetter("Al Khaleej National School")));
//        list.add(new Schools("Bradenton Preparatory Academy", Utils.getFirstLetter("Bradenton Preparatory Academy")));
//        list.add(new Schools("Dubai American Academy", Utils.getFirstLetter("Dubai American Academy")));
//        list.add(new Schools("GEMS Al Barsha National School", Utils.getFirstLetter("GEMS Al Barsha National School")));
//        list.add(new Schools("GEMS FirstPoint School", Utils.getFirstLetter("GEMS FirstPoint School")));
//        list.add(new Schools("GEMS International School - Al Khail", Utils.getFirstLetter("GEMS International School - Al Khail")));
//        list.add(new Schools("GEMS Millennium School", Utils.getFirstLetter("GEMS Millennium School")));
//        list.add(new Schools("GEMS Modern Academy (GMA)", Utils.getFirstLetter("GEMS Modern Academy (GMA)")));
//        list.add(new Schools("GEMS New Millennium School", Utils.getFirstLetter("GEMS New Millennium School")));
//        list.add(new Schools("GEMS Royal Dubai School", Utils.getFirstLetter("GEMS Royal Dubai School")));
//        list.add(new Schools("GEMS Public School - Rajkot", Utils.getFirstLetter("GEMS Public School - Rajkot")));

//        for (int i = 0; i < 40; i++) {
//
//            if (i > 1) {
//                String s = "School " + i;
//                list.add(new Schools(s, Utils.getFirstLetter(s)));
//            } else {
//                String s = "School " + i;
//                list.add(new Schools(s, Utils.getFirstLetter(s), true));
////                list.add(new Schools("School " + i, true));
//            }
//
//        }

        return list;
    }


    @Override
    public void position(int pos, Schools schools, int count) {

        countSelected = countSelected + count;

        addBarChart1(schoolsList, countSelected);
        addBarChart2(schoolsList, countSelected);
        addBarChart3(schoolsList, countSelected);
        addPieChartData(schoolsList, countSelected);

        Log.e(TAG, "position: " + countSelected);

    }
}
