package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.ListeEntity;
import com.eco.entitys.RubbishEntity;
import com.eco.entitys.XChangeEntity;
import com.eco.interfaces.IXChangePresenter;
import com.eco.interfaces.IXChangeView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;
import com.eco.viewHolder.XChangeViewHolder;

import java.util.ArrayList;

public class XChangePresenter extends BasePresenter<IXChangeView> implements IXChangePresenter {
    public XChangePresenter(IXChangeView view, Context context, ProgressBar progressBars, View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void getList(int id) {
if (id==1)
    startProgress();

        MethodApi.getInstance().getXChangeList(id, new IRemoteCallback<ListeEntity<XChangeEntity>>() {
            @Override
            public void onResponse(Boolean answer) {
            }

            @Override
            public void onSuccess(ListeEntity<XChangeEntity> result) {
                if (isViewAvailable()) {
                    if (PV.rubbishList.size() == 0)
                        getMinaList(result, id);
                    else
                    {
                        if (result.data.size()==0)
                            mView.get().showNull();
                        else
                            mView.get().showList(result);
                    }
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer)
                        mView.get().rGetList(id);
                    if (PV.rubbishList.size() != 0&&id==1)
                        stopProgress();
                }
            }
        });
    }

    @Override
    public void getMinaList(ListeEntity<XChangeEntity> list, int id) {
        MethodApi.getInstance().getRubbishList(new IRemoteCallback<ArrayList<RubbishEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<RubbishEntity> result) {
                if (isViewAvailable()) {
                    PV.rubbishList = result;
                    mView.get().showList(list);
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer) mView.get().rGetList(id);
                    stopProgress();
                }
            }
        });
    }

    @Override
    public void delete(XChangeEntity xChangeEntity, XChangeViewHolder xChangeViewHolder) {
        xChangeViewHolder.startProgress();
        if (xChangeEntity.type == 2) {
            MethodApi.getInstance().deleteSell(xChangeEntity.id, new IRemoteCallback<String>() {
                @Override
                public void onResponse(Boolean answer) {

                }

                @Override
                public void onSuccess(String result) {
                    if (isViewAvailable()) mView.get().delete(xChangeEntity);

                }

                @Override
                public void onFail(ErrorEntity errorObject) {
                    if (isViewAvailable()) showMsg(errorObject);
                }

                @Override
                public void onFinish(Boolean answer, boolean connection) {
                    if (isViewAvailable()) {
                        if (!connection)
                            mView.get().rDelete(xChangeEntity, xChangeViewHolder);
                        stopProgress();
                    }
                }
            });
        } else {
            MethodApi.getInstance().deleteRequest(xChangeEntity.id, new IRemoteCallback<String>() {
                @Override
                public void onResponse(Boolean answer) {

                }

                @Override
                public void onSuccess(String result) {
                    if (isViewAvailable()) mView.get().delete(xChangeEntity);
                }

                @Override
                public void onFail(ErrorEntity errorObject) {
                    if (isViewAvailable()) showMsg(errorObject);
                }

                @Override
                public void onFinish(Boolean answer, boolean connection) {
                    if (isViewAvailable()) {
                        if (!connection)
                            mView.get().rDelete(xChangeEntity, xChangeViewHolder);
                        stopProgress();
                    }
                }
            });
        }
    }
}
