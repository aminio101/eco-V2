package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.R;
import com.eco.entitys.AdvertisingEntity;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.entitys.TimeStampEntity;
import com.eco.entitys.UserNumberEntity;
import com.eco.interfaces.ITimeFragmentPresenter;
import com.eco.interfaces.ITimeFragmentView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.util.ArrayList;

public class TimeFragmentPresenter extends BasePresenter<ITimeFragmentView> implements ITimeFragmentPresenter {
    public TimeFragmentPresenter(ITimeFragmentView view, Context context,  ProgressBar progressBars,  View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void getTimes() {
        MethodApi.getInstance().getTimes(PV.locationEntity, new IRemoteCallback<ArrayList<RunDatePeriodsEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<RunDatePeriodsEntity> result) {

            }

            @Override
            public void onFail(ErrorEntity errorObject) {

            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {

            }
        });
    }

    @Override
    public void getRequestNumber(final LocationEntity locationEntity) {
        startProgress();
        MethodApi.getInstance().getRequestNumber(locationEntity, new IRemoteCallback<ArrayList<UserNumberEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<UserNumberEntity> result) {
                if (isViewAvailable()) mView.get().showRequestNumber(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rGetRequestNumber(locationEntity);
                }
            }
        });
    }


    @Override
    public void getNow() {
        MethodApi.getInstance().getNow(new IRemoteCallback<ArrayList<TimeStampEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<TimeStampEntity> result) {

            }

            @Override
            public void onFail(ErrorEntity errorObject) {

            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {

            }
        });
    }

    @Override
    public void getAdvertising() {
        MethodApi.getInstance().getAdvertising(new IRemoteCallback<ArrayList<AdvertisingEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<AdvertisingEntity> result) {
                if (isViewAvailable()) {
                    if (result != null) {
                        if (result.size() != 0) {
                            int counter = 0;
                            String str = result.get(0).path;
                            for (int i = 0; i < str.length(); i++) {
                                counter++;
                            }
                            String type = str.substring(counter - 4, counter);
                            if (type.equals(".gif")) {
                                mView.get().showGif(PV.PROTOCOL + PV.URL + "/storage/" + result.get(0).path);

                            } else {
                                mView.get().showVideo(PV.PROTOCOL + PV.URL + "/storage/" + str);

                            }
                        }
                    } else {
                        mView.get().showGif(R.raw.ecoo);
                    }
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if(isViewAvailable()) {
                    if(!answer)mView.get().rGetAdvertising();
                    stopProgress();//todo
                }
            }
        });
    }


}
