package rs.com.dashboardgems;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by yasar on 9/11/17.
 */

public class TestFont {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName){
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }
}
