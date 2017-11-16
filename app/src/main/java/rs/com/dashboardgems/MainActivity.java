package rs.com.dashboardgems;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import rs.com.dashboardgems.databinding.ActivityMainBinding;
import rs.com.dashboardgems.firemuster.FireMusterWhoIsINActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private BaseFragment baseFragment;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.appbar.toolbar);

//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setTitle("Dashboard");

        drawer = binding.drawerLayout;//(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = binding.navView;//(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.home).setChecked(true);
        callFragment(R.id.home);

        Menu m = navigationView.getMenu();

        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    SpannableString s = new SpannableString(subMenuItem.getTitle());
                    s.setSpan(new TypefaceSpan("fonts/Geneva.ttf"), 0, s.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    subMenuItem.setTitle(s);
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!(baseFragment instanceof FragmentHome)) {

//            ((MySchools) baseFragment).markerReset();
            callFragment(R.id.home);

        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        callFragment(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void callFragment(int id) {

        switch (id) {

            case R.id.home:
                getSupportActionBar().setTitle("Dashboard");
                baseFragment = new FragmentHome();
                break;
            case R.id.analytics:
                getSupportActionBar().setTitle("Financial Analytics");
                baseFragment = new Analytics();
                break;
            case R.id.academics:
                getSupportActionBar().setTitle("Academic Analytics");
                baseFragment = new AcademicsSchoolTab();
                break;
//            case R.id.locationprediction:
//                getSupportActionBar().setTitle("Location Prediction");
//                baseFragment = new LocationPrediction();
//                break;
            case R.id.myschools:
                getSupportActionBar().setTitle("My Schools");
                baseFragment = new MySchools();
                break;

            case R.id.whoismyschool:
                getSupportActionBar().setTitle("Who is in my school");
                baseFragment = new FireMustFragment();
//                Intent intent = new Intent(getApplicationContext(), FireMusterWhoIsINActivity.class);
//                startActivity(intent);
//                finish();
                break;
//
//            case R.id.logout:
//                return;
        }

        navigationView.getMenu().findItem(id).setChecked(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, baseFragment);
        fragmentTransaction.commit();


    }

}
