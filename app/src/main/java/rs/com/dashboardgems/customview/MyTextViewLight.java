package rs.com.dashboardgems.customview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by yasar on 9/8/17.
 */

public class MyTextViewLight extends android.support.v7.widget.AppCompatTextView {

    public MyTextViewLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewLight(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
//            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/slight.ttf");
//            setTypeface(tf);
        }
    }

}