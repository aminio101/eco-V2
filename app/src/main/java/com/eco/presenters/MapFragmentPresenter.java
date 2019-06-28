package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.FavoriteAddressEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.interfaces.IMapPresenter;
import com.eco.interfaces.IMapView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class MapFragmentPresenter extends BasePresenter<IMapView> implements IMapPresenter {
    public MapFragmentPresenter(IMapView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void getFavoriteLocation() {
        startProgress();
        MethodApi.getInstance().getFavoriteLocation(new IRemoteCallback<ArrayList<FavoriteAddressEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<FavoriteAddressEntity> result) {
                mView.get().showFavoriteLocation(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    stopProgress();
                    if (!connection)
                        mView.get().rGetFavoriteLocation();
                }
            }
        });
    }

    @Override
    public void editLocation(FavoriteAddressEntity favoriteAddressEntity) {
        startProgress();
        MethodApi.getInstance().changeFavoriteLocation(favoriteAddressEntity.getId(), favoriteAddressEntity, new IRemoteCallback<FavoriteAddressEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(FavoriteAddressEntity result) {
                if (isViewAvailable()) {
                    showMsg("تغییرات با موفقیت دخیره شد");
                    mView.get().successChangeLocation();
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!connection)
                        mView.get().rChangeLocation(favoriteAddressEntity);
                    stopProgress();
                }
            }
        });
    }

    @Override
    public void addLocation( FavoriteAddressEntity favoriteAddressEntity) {

            startProgress();
            MethodApi.getInstance().addFavoriteLocation(favoriteAddressEntity, new IRemoteCallback<FavoriteAddressEntity>() {
                @Override
                public void onResponse(Boolean answer) {

                }

                @Override
                public void onSuccess(FavoriteAddressEntity result) {
                    if (isViewAvailable()){
                        mView.get().successAddLocation();
                    showMsg("مکان منتخب شما اضافه شد");
                    }}

                @Override
                public void onFail(ErrorEntity errorObject) {
                    if (isViewAvailable()) showMsg(errorObject);
                }

                @Override
                public void onFinish(Boolean answer, boolean connection) {
                    if (isViewAvailable()) {
                        if (!connection)
                            mView.get().rAddLocation(  favoriteAddressEntity);
                        stopProgress();

                    }
                }
            });
    }

    @Override
    public void sendLocation(Button buttonNextStep, FavoriteAddressEntity favoriteAddressEntity) {
        if (buttonNextStep.getText().toString().equals("ویرایش"))
            this.editLocation(favoriteAddressEntity);
        else
            this.addLocation( favoriteAddressEntity);
    }

    @Override
    public void checkData(String des, GoogleMap mMap) {
        if (!PV.checkText(des)) {
            showMsg("لطفا توضیحات آدرس خود را وارد کنید ");
            return;
        }
        checkLocation(des, mMap);
    }

    public void save(String des, GoogleMap googleMap) {
        PV.requestEntity.location.put("lat", googleMap.getCameraPosition().target.latitude);
        PV.requestEntity.location.put("lng", googleMap.getCameraPosition().target.longitude);
        PV.requestEntity.address = des;
        mView.get().successSaveLocation();
    }

    public void checkLocation(String des, GoogleMap googleMap) {
        startProgress();
        MethodApi.getInstance().getTimes(new LocationEntity().setFirstLat(googleMap.getCameraPosition().target.latitude).setFirstLng(googleMap.getCameraPosition().target.longitude), new IRemoteCallback<ArrayList<RunDatePeriodsEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<RunDatePeriodsEntity> result) {
                if (isViewAvailable()) {
                    save(des,googleMap);
                }
            }


            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    stopProgress();
                    if (!connection)
                        mView.get().rCheckLocation(des,googleMap);
                }
            }
        });
    }
}
