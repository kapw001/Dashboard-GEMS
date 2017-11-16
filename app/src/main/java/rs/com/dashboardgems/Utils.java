package rs.com.dashboardgems;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rs.com.dashboardgems.models.SchoolDetailsResult;

/**
 * Created by yasar on 10/11/17.
 */

public class Utils {


    final static int[] MY_COLORS = {Color.parseColor("#2ecc71"), Color.parseColor("#aa00ff")};
//

    public static String getFirstLetter(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] words = s.split("\\W+");

        for (String s1 : words
                ) {
//            s2 = "" + String.valueOf(s1.charAt(0));
            stringBuilder.append(String.valueOf(s1.charAt(0)));

        }

        return stringBuilder.toString();

    }

    public static List<SchoolDetailsResult> getSchoolDetailsResultList(Context context) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<SchoolDetailsResult>>() {
        }.getType();
        return gson.fromJson(Utils.loadJSONFromAsset(context), listType);
    }

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("schooljson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static String loadJSONFromAssetRecords(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("WMSchool.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

//    private void addBarChartTest() {
//        float barWidth;
//        float barSpace;
//        float groupSpace;
//
//        barWidth = 0.3f;
//        barSpace = 0f;
//        groupSpace = 0.4f;
//
//        binding.barchart1.setDescription(null);
//        binding.barchart1.setPinchZoom(false);
//        binding.barchart1.setScaleEnabled(false);
//        binding.barchart1.setDrawBarShadow(false);
//        binding.barchart1.setDrawGridBackground(false);
//
//        int groupCount = 6;
//
//        ArrayList xVals = new ArrayList();
//
//        xVals.add("Jan");
//        xVals.add("Feb");
//        xVals.add("Mar");
////        xVals.add("Apr");
////        xVals.add("May");
////        xVals.add("Jun");
//
//        ArrayList yVals1 = new ArrayList();
//        ArrayList yVals2 = new ArrayList();
//
//        yVals1.add(new BarEntry(1, (float) 1));
//        yVals2.add(new BarEntry(1, (float) 2));
//        yVals1.add(new BarEntry(2, (float) 3));
//        yVals2.add(new BarEntry(2, (float) 4));
//        yVals1.add(new BarEntry(3, (float) 5));
//        yVals2.add(new BarEntry(3, (float) 6));
//        yVals1.add(new BarEntry(4, (float) 7));
//        yVals2.add(new BarEntry(4, (float) 8));
//        yVals1.add(new BarEntry(5, (float) 9));
//        yVals2.add(new BarEntry(5, (float) 10));
//        yVals1.add(new BarEntry(6, (float) 11));
//        yVals2.add(new BarEntry(6, (float) 12));
//
//        //X-axis
//        XAxis xAxis = binding.barchart1.getXAxis();
//        xAxis.setGranularity(1f);
//        xAxis.setGranularityEnabled(true);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setDrawGridLines(false);
//        xAxis.setAxisMaximum(6);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
////Y-axis
//        binding.barchart1.getAxisRight().setEnabled(false);
//        YAxis leftAxis = binding.barchart1.getAxisLeft();
//        leftAxis.setValueFormatter(new LargeValueFormatter());
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setSpaceTop(35f);
//        leftAxis.setAxisMinimum(0f);
//
//        Legend l = binding.barchart1.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(true);
//        l.setYOffset(20f);
//        l.setXOffset(0f);
//        l.setYEntrySpace(0f);
//        l.setTextSize(8f);
//
//        BarDataSet set1, set2;
//        set1 = new BarDataSet(yVals1, "A");
//        set1.setColor(Color.RED);
//        set2 = new BarDataSet(yVals2, "B");
//        set2.setColor(Color.BLUE);
//        BarData data = new BarData(set1, set2);
//        data.setValueFormatter(new LargeValueFormatter());
//        binding.barchart1.setData(data);
//        binding.barchart1.getBarData().setBarWidth(barWidth);
//        binding.barchart1.getXAxis().setAxisMinimum(0);
//        binding.barchart1.getXAxis().setAxisMaximum(0 + binding.barchart1.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
//        binding.barchart1.groupBars(0, groupSpace, barSpace);
//        binding.barchart1.getData().setHighlightEnabled(false);
//        binding.barchart1.invalidate();
//
//
//    }

}
