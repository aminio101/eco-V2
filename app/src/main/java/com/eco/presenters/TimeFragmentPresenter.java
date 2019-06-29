package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.R;
import com.eco.entitys.AdvertisingEntity;
import com.eco.entitys.DayEntity;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.LocationEntity;
import com.eco.entitys.RunDatePeriodsEntity;
import com.eco.entitys.TimeStampEntity;
import com.eco.entitys.UserNumberEntity;
import com.eco.interfaces.ITimeFragmentPresenter;
import com.eco.interfaces.ITimeFragmentView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class TimeFragmentPresenter extends BasePresenter<ITimeFragmentView> implements ITimeFragmentPresenter {
    int hour;
    int dayNumber;
    String nowDate;
    String timeStamp;
    public TimeFragmentPresenter(ITimeFragmentView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void getTimes() {
        MethodApi.getInstance().getTimes(new LocationEntity().setFirstLng(
                PV.requestEntity.location.get("lng")).
                        setFirstLat(PV.requestEntity.location.get("lat")),
                new IRemoteCallback<ArrayList<RunDatePeriodsEntity>>() {
                    @Override
                    public void onResponse(Boolean answer) {

                    }

                    @Override
                    public void onSuccess(ArrayList<RunDatePeriodsEntity> result) {
                        DayEntity list[] = new DayEntity[8];
                        if (isViewAvailable()) {
                            for (int i = 0; i < result.size(); i++) {
                                if (result.get(i).runDate >= dayNumber) { // todo change
                                    if (list[result.get(i).runDate] == null)
                                        list[result.get(i).runDate] = new DayEntity();
                                    list[result.get(i).runDate].list.add(result.get(i));
                                }
                            }
                            for (int i = 0; i < result.size(); i++) {
                                if (result.get(i).runDate < dayNumber) { // todo change
                                    if (list[result.get(i).runDate] == null)
                                        list[result.get(i).runDate] = new DayEntity();
                                    list[result.get(i).runDate].list.add(result.get(i));
                                }
                            }

                        }


                        ArrayList<DayEntity> arrayList = new ArrayList<>();
                        for (DayEntity dayEntity : list) {
                            if (dayEntity != null && dayEntity.list.get(0).runDate >= dayNumber) {
                                try {
                                    String date = "0";
                                    if (dayEntity.list.get(0).runDate - dayNumber >= 0)
                                        date = PV.addDay(nowDate, dayEntity.list.get(0).runDate - dayNumber);
                                    else
                                        date = PV.addDay(nowDate, dayNumber - dayEntity.list.get(0).runDate);

                                    Date date1 = PV.stringToDate(date);
                                    dayEntity.date = date1;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                arrayList.add(dayEntity);
                            }
                        }
                        for (DayEntity dayEntity : list) {
                            if (dayEntity != null && dayEntity.list.get(0).runDate < dayNumber) {
                                try {
                                    String date = "0";
                                    if (dayEntity.list.get(0).runDate - dayNumber >= 0)
                                        date = PV.addDay(nowDate, dayEntity.list.get(0).runDate - dayNumber);
                                    else
                                        date = PV.addDay(nowDate, 7 - (dayNumber - dayEntity.list.get(0).runDate));

                                    Date date1 = PV.stringToDate(date);
                                    dayEntity.date = date1;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                arrayList.add(dayEntity);
                            }
                        }
                        mView.get().showTimes(arrayList, timeStamp);


                        stopProgress();

                    }

                    @Override
                    public void onFail(ErrorEntity errorObject) {
                        if (isViewAvailable()) showMsg(errorObject);
                    }

                    @Override
                    public void onFinish(Boolean answer, boolean connection) {
                        if (isViewAvailable()) {
                            if (!answer)
                                mView.get().rGetTimes();
                        }
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
                try {
                    timeStamp = result.get(0).timestamp;
                    hour = PV.getHour(result.get(0).timestamp);
                    dayNumber = PV.getDayNumber(result.get(0).timestamp, 0);
                    nowDate = result.get(0).timestamp.substring(0, 4) + "-" + result.get(0).timestamp.substring(4, 6) + "-" + result.get(0).timestamp.substring(6, 8);
                    PV.timeStamp = result.get(0).timestamp;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                getTimes();
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rGetNow();
                }
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
                    getNow();
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer) mView.get().rGetAdvertising();
                }
            }
        });
    }

    @Override
    public void nextFragment(RunDatePeriodsEntity runDatePeriods) {
        if (runDatePeriods == null)
            showMsg("انتخاب کردن زمان ارسالی الزامی است ");
        else {
            mView.get().nextFragment();
            PV.endPeriod = runDatePeriods.endPeriod;
        }
    }


    @Override
    public void detach() {
        detachView();
    }
}
