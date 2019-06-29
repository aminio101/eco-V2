package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.RubbishEntity;
import com.eco.enums.RequstMode;
import com.eco.interfaces.IFinalFragmentPresenter;
import com.eco.interfaces.IFinalFragmentView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FinalFragmentPresenter extends BasePresenter<IFinalFragmentView> implements IFinalFragmentPresenter {
    public FinalFragmentPresenter(IFinalFragmentView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }


    @Override
    public void delete(RubbishEntity tag) {
        ArrayList<RubbishEntity> list = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < PV.list.size(); i++) {
            if (PV.list.get(i).id != tag.id) {
                list.add(PV.list.get(i));
                hashMap.put(
                        String.valueOf(PV.list.get(i).id),
                        PV.requestEntity.request.get(String.valueOf(PV.list.get(i).id)));
            }
        }
        PV.list = list;
        PV.requestEntity.request = hashMap;
        mView.get().showList(list);
    }

    @Override
    public void getList() {
        mView.get().showList(PV.list);
    }

    @Override
    public void sendRequest() {
        if (PrefManager.getInstance().getUser().roleId == 1) {
            sendRequestNormal();
        } else {
            if (PV.requstMode == RequstMode.NORMAL)
                sendRequestNormal();
            else
                sendFastRequest();
        }
    }

    private void sendFastRequest() {

    }

    private void sendRequestNormal() {
        startProgress();
        MethodApi.getInstance().addNormalRequest(PV.requestEntity, new IRemoteCallback<JsonObject>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(JsonObject result) {
                if (isViewAvailable()) {
                    PV.requestEntity = null;
                    PV.list.clear();
                    mView.get().success();
                    showMsg("در خواست شما با موفقیت ثیت شد");
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable())showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if(isViewAvailable()){
                    stopProgress();
                    if(!connection)
                        mView.get().rSendRequest();
                }
            }
        });
    }
}
