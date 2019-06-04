package com.eco.activityes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.eco.R;
import com.eco.fragments.MainFragment;
import com.eco.fragments.MapFragment;
import com.eco.fragments.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    Fragment currentFragment = null;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    boolean firstLoad;
    @OnClick(R.id.main_activity_image_back)public void back(){
        onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        ButterKnife.bind(this);
        firstLoad = true;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mainFragment:
                        if (!firstLoad)
                            loadMainFragment();
                        break;
                    case R.id.cartFragment:
                        if (!firstLoad)
                            loadShopFragment();
                        break;

                    case R.id.xChangeList:
                        if (!firstLoad)
                            loadMapFragment();
                        break;

                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.mainFragment);
        loadMainFragment();
        firstLoad = false;
    }

    public void loadMainFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new MainFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "MainFragment");
        fragmentTransaction.addToBackStack("MainFragment");
        fragmentTransaction.commit();
    }

    public void loadMapFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new MapFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "MapFragment");
        fragmentTransaction.addToBackStack("MapFragment");
        fragmentTransaction.commit();
    }
    public void loadShopFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new ShopFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "ShopFragment");
        fragmentTransaction.addToBackStack("ShopFragment");
        fragmentTransaction.commit();
    }
}
