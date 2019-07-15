package com.eco.activityes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.eco.CoustomTextView;
import com.eco.PV;
import com.eco.entitys.AcceptDriverEntity;
import com.eco.fragments.AboutUsFragment;
import com.eco.fragments.FragmentComment;
import com.eco.fragments.MoreFragment;
import com.eco.views.DialogAcceptDriver;
import com.eco.views.DialogConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.Gravity.RIGHT;

public class MainActivity extends AppCompatActivity {
    Fragment currentFragment = null;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    boolean firstLoad;

    @BindView(R.id.eco_name)
    CoustomTextView tollbarName;

    @OnClick(R.id.main_activity_image_back)
    public void back() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (currentFragment instanceof MainFragment)
            Log.i("a", "a");
        else
            super.onBackPressed();
    }

    public void setTollbarName(String name) {
        tollbarName.setText(name);
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
        PV.activity = this;
        firstLoad = true;

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
                    case R.id.more:
                        if (!firstLoad)
                            loadMoreFragment();
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

    public void showDriverDialog(AcceptDriverEntity acceptDriverEntitiy) {
        DialogAcceptDriver dialogAcceptDriver = new DialogAcceptDriver(acceptDriverEntitiy);
        dialogAcceptDriver.show(getSupportFragmentManager(), "DialogAcceptDriver");
    }

    public boolean onClick(int id) {
        switch (id) {

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
    }

    public void loadXChangeFragment() {
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

    public void AboutUs() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new AboutUsFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "AboutUsFragment");
        fragmentTransaction.addToBackStack("AboutUsFragment");
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

    public void loadMoreFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new MoreFragment();
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "MoreFragment");
        fragmentTransaction.addToBackStack("MoreFragment");
        fragmentTransaction.commit();
    }


    public void loadCommentFragment(String number) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        currentFragment = new FragmentComment();
        Bundle bundle = new Bundle();
        bundle.putString("number", number);
        currentFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_container, currentFragment, "FragmentComment");
        fragmentTransaction.addToBackStack("FragmentComment");
        fragmentTransaction.commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
