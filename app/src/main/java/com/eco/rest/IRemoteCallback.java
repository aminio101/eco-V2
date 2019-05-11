package com.eco.rest;


import com.eco.entitys.ErrorEntity;

public interface IRemoteCallback<T> {
    void onResponse(Boolean answer);

    void onSuccess(T result);

    void onFail(ErrorEntity errorObject);

    void onFinish(Boolean answer, boolean connection);
}