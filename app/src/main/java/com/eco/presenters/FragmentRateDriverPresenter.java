package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.entitys.CommentEntity;
import com.eco.entitys.DriverEntity;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.ItemEntity;
import com.eco.entitys.RequestEntity;
import com.eco.interfaces.IFragmentRateDriverPresenter;
import com.eco.interfaces.IFragmentRateDriverView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;

import java.util.ArrayList;
import java.util.Map;

public class FragmentRateDriverPresenter extends BasePresenter<IFragmentRateDriverView> implements IFragmentRateDriverPresenter {
    public FragmentRateDriverPresenter(IFragmentRateDriverView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }


    @Override
    public void getList(int id) {
        startProgress();
        MethodApi.getInstance().getRequstList(id, new IRemoteCallback<DriverEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(DriverEntity result) {
                if (isViewAvailable()) {

                    ArrayList<ItemEntity> items = new ArrayList<>();
                    for ( Map.Entry<String, Integer> entry : result.rubish.entrySet()) {
                        String key= entry.getKey();
                        int value=entry.getValue();
                        ItemEntity item = new ItemEntity();
                        item.setNumber(String.valueOf(value));
                        item.setName(key);
                        items.add(item);
                    }
                    mView.get().showList(result,items);
                    stopProgress();
                }}

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rGetList(id);
                }
            }
        });
    }

    @Override
    public void sendComment(float rating, int driverId) {
        startProgress();
        MethodApi.getInstance().sendComment(driverId, new CommentEntity(String.valueOf(rating), String.valueOf(driverId)), new IRemoteCallback<CommentEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(CommentEntity result) {
                if (isViewAvailable()) {
                    mView.get().success();
                    showMsg("نظر شما با موفقیت ثبت شد");
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
                        mView.get().rSendComment(rating, driverId);
                    stopProgress();
                }
            }
        });
    }
}
