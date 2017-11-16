package rs.com.dashboardgems.firemuster;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.com.dashboardgems.R;
import rs.com.dashboardgems.model.PersonData;
import rs.com.dashboardgems.model.VisitorInfo;

public class Utils {

    private static final String TAG = "Utils";
    private static ProgressDialog progressDialog;

    public static void showProgress(Context context, String msg) {
        if (!((Activity) context).isFinishing()) {
            //show dialog

            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
    }

    public static void hideProgress() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    public static Bitmap decodeBitmap(Context context, String encodedImage) {
        Log.e(TAG, "decodeBitmap: " + encodedImage);
        try {
            if (!encodedImage.equalsIgnoreCase("") || !encodedImage.equalsIgnoreCase("null")) {
                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            } else {
                return BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getTimeAMPM() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        return dateFormat.format(new Date());
    }

    public static List<VisitorInfo> getAll() {
        List<VisitorInfo> list = new ArrayList<>();

        list.addAll(getVisitor());
        list.addAll(getStaff());
        list.addAll(getStudent());


        return list;
    }

    public static List<PersonData> getPersonList() {
        List<PersonData> list = new ArrayList<>();
        PersonData personData1 = new PersonData();
        personData1.setName("QQQQQ");
        personData1.setStudClass("Grade IV");
        personData1.setType("student");
        personData1.setIsSafe(false);

        list.add(personData1);


        personData1 = new PersonData();
        personData1.setName("EEEEE");
        personData1.setStudClass("Grade IV");
        personData1.setType("student");
        personData1.setIsSafe(false);

        list.add(personData1);


        personData1 = new PersonData();
        personData1.setName("He<zcx<z");
        personData1.setStudClass("Grade V");
        personData1.setType("student");
        personData1.setIsSafe(false);

        list.add(personData1);

        personData1 = new PersonData();
        personData1.setName("AAAAA");
        personData1.setStudClass("Grade V");
        personData1.setType("student");
        personData1.setIsSafe(false);

        list.add(personData1);

        personData1 = new PersonData();
        personData1.setName("BBBBBB");
        personData1.setStudClass("Grade V");
        personData1.setType("student");
        personData1.setIsSafe(false);

        list.add(personData1);

        return list;
    }

    public static List<VisitorInfo> getStudent() {

        List<VisitorInfo> visitorInfosList = new ArrayList<>();
        VisitorInfo visitorInfo = new VisitorInfo("Smith", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Jones", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Taylor", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Williams", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Brown", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Davies", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Evans", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Wilson", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Thomas", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Roberts", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Johnson", "Student", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Lewis", "Student", "In", "");
        visitorInfosList.add(visitorInfo);


        return visitorInfosList;
    }


    public static List<VisitorInfo> getVisitor() {

        List<VisitorInfo> visitorInfosList = new ArrayList<>();
        VisitorInfo visitorInfo = new VisitorInfo("Bailey", "Visitor", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Parker", "Visitor", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Miller", "Visitor", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Davis", "Visitor", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Murphy", "Visitor", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Kelly", "Visitor", "In", "");
        visitorInfosList.add(visitorInfo);

        return visitorInfosList;
    }

    public static List<VisitorInfo> getStaff() {

        List<VisitorInfo> visitorInfosList = new ArrayList<>();
        VisitorInfo visitorInfo = new VisitorInfo("Bailey", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Parker", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Miller", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Davis", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Murphy", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);
        visitorInfo = new VisitorInfo("Kelly", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);

        visitorInfo = new VisitorInfo("Simpson", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);

        visitorInfo = new VisitorInfo("Reynolds", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);

        visitorInfo = new VisitorInfo("Russell", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);

        visitorInfo = new VisitorInfo("Reid", "Staff", "In", "");
        visitorInfosList.add(visitorInfo);

        return visitorInfosList;
    }


}
