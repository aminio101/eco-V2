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

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    Fragment currentFragment = null;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    boolean firstLoad;
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
                            loadMainFragment();
                        break;

                    case R.id.xChangeList:
                        if (!firstLoad)
                            loadMainMapFragment();
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

    public void loadMainMapFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new MapFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "MapFragment");
        fragmentTransaction.addToBackStack("MapFragment");
        fragmentTransaction.commit();
    }
}
