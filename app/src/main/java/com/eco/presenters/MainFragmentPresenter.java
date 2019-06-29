package com.eco.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.eco.PV;
import com.eco.PrefManager;
import com.eco.entitys.ErrorEntity;
import com.eco.entitys.MUserEntity;
import com.eco.entitys.RubbishEntity;
import com.eco.entitys.UserEntity;
import com.eco.interfaces.IMainFragmentPresenter;
import com.eco.interfaces.IMainFragmentView;
import com.eco.rest.IRemoteCallback;
import com.eco.rest.MethodApi;
import com.eco.viewHolder.MainListViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragmentPresenter extends BasePresenter<IMainFragmentView> implements IMainFragmentPresenter {
    public MainFragmentPresenter(IMainFragmentView view, Context context, ProgressBar progressBars,  View views) {
        super(view, context, progressBars, views);
    }

    @Override
    public void detach() {
        detachView();
    }

    @Override
    public void getList() {
        MethodApi.getInstance().getRubbishList(new IRemoteCallback<ArrayList<RubbishEntity>>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(ArrayList<RubbishEntity> result) {
                if (isViewAvailable()) mView.get().showList(result);
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable()) showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()) {
                    if (!answer) mView.get().rGetList();
                    stopProgress();
                }
            }
        });
    }

    @Override
    public void save(ArrayList<MainListViewHolder> viewHolders) {
        HashMap<String,Integer> hashMap = new HashMap<>();
        for (int i =0 ;i < viewHolders.size();i++){
            if (viewHolders.get(i).num!=0){
                hashMap.put(String.valueOf(viewHolders.get(i).rubbishEntity.id),viewHolders
                        .get(i).num);
                RubbishEntity rubbishEntity = new RubbishEntity();
                rubbishEntity = viewHolders.get(i).rubbishEntity;
                rubbishEntity.number = viewHolders
                        .get(i).num;
                PV.list.add(rubbishEntity);
            }
        }
        PV.requestEntity.request = hashMap;
        if (PV.requestEntity.request.size()==0)
            showMsg("لطفا حداقل یک پسماند انتخاب کنید");
        else
            mView.get().loadMapFragment();
    }

    @Override
    public void getUser() {
        startProgress();
        MethodApi.getInstance().getUser(new IRemoteCallback<MUserEntity>() {
            @Override
            public void onResponse(Boolean answer) {

            }

            @Override
            public void onSuccess(MUserEntity result) {
                if (isViewAvailable()) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.roleId = result.roleId;
                    userEntity.address = result.address;
                    userEntity.cityId = result.cityId;
                    userEntity.email = result.email;
                    userEntity.family = result.family;
                    userEntity.familyNumber = String.valueOf(result.familyCount);
                    userEntity.gender = String.valueOf(result.gender);
                    userEntity.grade = result.grade;
                    userEntity.id = result.id;
                    userEntity.mobileNumber = String.valueOf(result.userName);
                    userEntity.name = result.name;
                    userEntity.provinceId = result.provinceId;
                    userEntity.score = result.citizenScore;
                    userEntity.shabaNumber = result.shabaNumber;
                    userEntity.username = result.userName;
                    PrefManager.getInstance().setUser(userEntity);
                    getList();
                }
            }

            @Override
            public void onFail(ErrorEntity errorObject) {
                if (isViewAvailable())
                    showMsg(errorObject);
            }

            @Override
            public void onFinish(Boolean answer, boolean connection) {
                if (isViewAvailable()){
                    if (!answer)
                        mView.get().rGetUser();
                }
            }
        });
    }
}
