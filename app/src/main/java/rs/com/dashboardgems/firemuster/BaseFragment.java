package rs.com.dashboardgems.firemuster;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

import rs.com.dashboardgems.model.PersonData;


/**
 * Created by yasar on 18/8/17.
 */

public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    public PassListInterface passListInterface;

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            passListInterface = (PassListInterface) activity;
//
//        }catch (ClassCastException e){
//            Log.e(TAG, "onAttach: "+e.getMessage() );
//        }
//
//    }

    public void setPassListInterface(PassListInterface passListInterface) {
        this.passListInterface = passListInterface;
    }

    //
    public abstract void loadData(List<PersonData> list);
}
