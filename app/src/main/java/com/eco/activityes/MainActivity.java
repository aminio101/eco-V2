package com.eco.activityes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.eco.R;
import com.eco.fragments.EditProfileFragment;
import com.eco.fragments.FinalFragment;
import com.eco.fragments.IntroduceFragment;
import com.eco.fragments.MainFragment;
import com.eco.fragments.MapFragment;
import com.eco.fragments.ShopFragment;
import com.eco.fragments.TimeFragment;
import com.eco.fragments.WalletFragment;
import com.eco.fragments.XChangeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Gravity.RIGHT;

public class MainActivity extends AppCompatActivity {
    Fragment currentFragment = null;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    boolean firstLoad;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @OnClick(R.id.main_activity_image_back)
    public void back() {
        onBackPressed();
    }
    @BindView(R.id.drawable_right)
    DrawerLayout drawerLayout;
    @OnClick(R.id.btn_menu)
    public void openMenu() {
        drawerLayout.openDrawer(RIGHT);
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return onClick(menuItem.getItemId());
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mainFragment:
                        if (!firstLoad)
                            loadMainFragment();
                        break;

                    case R.id.xChangeList:
                        if (!firstLoad)
                            loadXChangeFragment();
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

    public boolean onClick(int id) {
        switch (id) {
//            case R.id.invite:
//                loadIntroduceFragment();
//                drawerLayout.closeDrawer(RIGHT);
//                return true;
           case R.id.profile:
               loadEditProfileFragment();
                drawerLayout.closeDrawer(RIGHT);
                return true;
            case R.id.invite:
                loadIntroduceFragment();
                drawerLayout.closeDrawer(RIGHT);
                return true;
            case R.id.prices:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(RIGHT);
                return true;
           case R.id.wallet:
              loadFragmentWallet();
               drawerLayout.closeDrawer(RIGHT);
                return true;


           /*//  case R.id.about_us:
                loadFragmentAboutUs();
                drawerLayout.closeDrawer(RIGHT);
                return true;
            case R.id.support:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.support_number)));
                startActivity(intent);
                drawerLayout.closeDrawer(RIGHT);
                return true;
            case R.id.exit:
                logOut();
                return true;*/
        }
        return false;
    }

    public void loadShopFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new ShopFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "ShopFragment");
        fragmentTransaction.addToBackStack("ShopFragment");
        fragmentTransaction.commit();
    }  public void loadXChangeFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new XChangeFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "XChangeFragment");
        fragmentTransaction.addToBackStack("XChangeFragment");
        fragmentTransaction.commit();
    }

    public void loadTimeFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new TimeFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "TimeFragment");
        fragmentTransaction.addToBackStack("TimeFragment");
        fragmentTransaction.commit();
    }

    public void loadFinalFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new FinalFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "FinalFragment");
        fragmentTransaction.addToBackStack("FinalFragment");
        fragmentTransaction.commit();
    }

    public void loadIntroduceFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new IntroduceFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "IntroduceFragment");
        fragmentTransaction.addToBackStack("IntroduceFragment");
        fragmentTransaction.commit();
    }
    public void loadEditProfileFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new EditProfileFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "EditProfileFragment");
        fragmentTransaction.addToBackStack("EditProfileFragment");
        fragmentTransaction.commit();
    }
    public void loadFragmentWallet() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new WalletFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "WalletFragment");
        fragmentTransaction.addToBackStack("WalletFragment");
        fragmentTransaction.commit();
    }


}
