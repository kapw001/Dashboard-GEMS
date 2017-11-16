package rs.com.dashboardgems;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.*;

import java.util.ArrayList;
import java.util.Collections;

import rs.com.dashboardgems.databinding.HomeBinding;


/**
 * Created by yasar on 9/11/17.
 */

public class FragmentHome extends BaseFragment {
    private float[] yData = {25.0f, 30.0f, 20.0f, 15.0f, 10.0f};
    private String[] xData = {"UK", "INDIA", "UAE", "USA", "FRANCE"};
    private PieChart pieChart;
    private LineChart mChart;
    private HomeBinding homeBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View v = inflater.inflate(R.layout.home, container, false);

        homeBinding = DataBindingUtil.inflate(inflater, R.layout.home, container, false);

        View view = homeBinding.getRoot();
//        pieChart = (PieChart) v.findViewById(R.id.idPieChart);
//        mChart = (LineChart) v.findViewById(R.id.linechart);
        pieChart = homeBinding.idPieChart;
        mChart = homeBinding.linechart;

        addPieChartData();
        addLineChartData();

        return view;
    }

    private void addLineChartData() {
        ArrayList<Entry> entries = new ArrayList();
        entries.add(new Entry(0f, 30));
        entries.add(new Entry(1f, 50));
        entries.add(new Entry(2f, 40));
        entries.add(new Entry(3f, 60));
//        entries.add(new Entry(4f, 80));
//        entries.add(new Entry(3f, 90));
//        entries.add(new Entry(4f, 20));
//        entries.add(new Entry(5f, 5));


        ArrayList<String> labels = new ArrayList();
        labels.add("2014");
        labels.add("2015");
        labels.add("2016");
        labels.add("2017");


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
//        xAxis.setXOffset(10f);
//        xAxis.setAxisLineWidth(.5f);
//        xAxis.setAxisMaximum(3);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        mChart.setDescription(null);
        mChart.getLegend().setEnabled(false);
        mChart.getAxisRight().setEnabled(false);


        mChart.setPinchZoom(false);
        mChart.setDoubleTapToZoomEnabled(false);

        Collections.sort(entries, new EntryXComparator());
        LineDataSet dataset = new LineDataSet(entries, "Label");

//        dataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        dataset.setCubicIntensity(0.3f);
        dataset.setDrawFilled(true);
        dataset.setDrawCircles(true);
        dataset.setLineWidth(1.8f);
        dataset.setCircleRadius(6f);
        dataset.setCircleHoleRadius(3f);
        dataset.setCircleColor(Color.BLUE);
        dataset.setHighLightColor(Color.rgb(244, 117, 117));
        dataset.setColor(Color.BLUE);
        dataset.setFillColor(Color.BLUE);
        dataset.setFillAlpha(100);
        dataset.setDrawHorizontalHighlightIndicator(false);
        dataset.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return -10;
            }
        });

        LineData data = new LineData(dataset);
        data.setValueFormatter(new LargeValueFormatter());
        data.setDrawValues(false);
        mChart.getAxisLeft().setDrawZeroLine(false);
        mChart.setDrawGridBackground(false);
        mChart.getAxisRight().setDrawZeroLine(false);
        mChart.getAxisLeft().setDrawAxisLine(false);
//        mChart.getAxisLeft().setDrawGridLines(false);
//        mChart.getAxisLeft().setEnabled(false);
//        mChart.getAxisRight().setEnabled(false);
//        mChart.getXAxis().setEnabled(false);

//        mChart.getAxisLeft().setDrawLabels(false);
        mChart.animateXY(1000, 1000);
        mChart.setData(data);
        mChart.invalidate();
    }


    private void addPieChartData() {

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

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

//        pieChart.setCenterTextTypeface(mTfLight);
//        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleRadius(38f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
//            Resources res = getResources();
//            Drawable drawable = res.getDrawable(R.drawable.ic_menu_camera);
            yEntrys.add(new PieEntry(yData[i], xData[i]));
        }

        for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
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
        colors.add(Utils.MY_COLORS[0]);
        colors.add(Utils.MY_COLORS[1]);

//        for (int c : ColorTemplate.MATERIAL_COLORS)
//            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
//
//        colors.add(ColorTemplate.getHoloBlue());
//
        pieDataSet.setColors(colors);

//        final int[] MY_COLORS = {Color.parseColor("#ff8f00"), Color.parseColor("#1976d2"), Color.parseColor("#558b2f"),
//                Color.rgb(127, 127, 127), Color.rgb(146, 208, 80), Color.rgb(0, 176, 80), Color.rgb(79, 129, 189)};
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//
//        for (int c : MY_COLORS) colors.add(c);
//
//        pieDataSet.setColors(colors);

//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(10f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(5f);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(8f);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelTextSize(8f);
        pieChart.setData(pieData);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        pieChart.invalidate();
    }
}
