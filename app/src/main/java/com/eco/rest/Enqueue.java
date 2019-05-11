package com.eco.rest;

import com.eco.entitys.ErrorEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eco.enums.InteractorResponse.BAD_GATEWAY;
import static com.eco.enums.InteractorResponse.BAD_REQUEST;
import static com.eco.enums.InteractorResponse.FORBIDDEN;
import static com.eco.enums.InteractorResponse.GATEWAY_TIMEOUT;
import static com.eco.enums.InteractorResponse.INTERNAL_SERVER_ERROR;
import static com.eco.enums.InteractorResponse.METHOD_NOT_ALLOWED;
import static com.eco.enums.InteractorResponse.NOT_ACCEPTABLE;
import static com.eco.enums.InteractorResponse.NOT_FOUND;
import static com.eco.enums.InteractorResponse.SERVICE_UNAVAILABLE;
import static com.eco.enums.InteractorResponse.SOCKET_TIMEOUT;
import static com.eco.enums.InteractorResponse.TIMEOUT;
import static com.eco.enums.InteractorResponse.UNARUTHORIZED;
import static com.eco.enums.InteractorResponse.UNKNOWN;
import static com.eco.enums.InteractorResponse.UNSUPPORTED_MEDIA_TYPE;

public class Enqueue<T> implements Callback<T> {
    private final IRemoteCallback<T> callback;


    public Enqueue(IRemoteCallback<T> callback) {
        this.callback = callback;
    }

    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            this.callback.onResponse(false);
            this.callback.onFail(this.parseError(response));
            this.callback.onFinish(false,true);
        } else {
            this.callback.onResponse(true);
            this.callback.onSuccess(response.body());
            this.callback.onFinish(true,true);
        }
    }

    private ErrorEntity parseError(Response<T> response) {
        ErrorEntity error = new ErrorEntity();
        if (response.body() instanceof SocketTimeoutException) {
            error.setStatus("Error");
            error.setUiErrorMessage("Socket TimeOut UiError Msg");
            error.setExceptionMessage("Socket TimeOut Error Msg");
            error.setInteractorResponse(SOCKET_TIMEOUT);
        } else if (response.body() instanceof TimeoutException) {
            error.setStatus("Error");
            error.setUiErrorMessage("TimeOut UiError Msg");
            error.setExceptionMessage("TimeOut Error Msg");
            error.setInteractorResponse(TIMEOUT);
        } else {
            try {
                String jsonObject;
                Gson gson = new Gson();
                jsonObject = gson.toJson(response.errorBody().string());
                error.setExceptionMessage(response.message());
                error. setStatus(String.valueOf(response.code()));
                error.setUiErrorMessage(jsonObject.substring(jsonObject.lastIndexOf("message") + 12, jsonObject.length() - 4));
            } catch (NullPointerException ignored) {

            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (response.code()) {

                case 400:
                    error.setInteractorResponse(BAD_REQUEST);
                    break;

                case 401:
                    error.setInteractorResponse(UNARUTHORIZED);
                    break;

                case 403:
                    error.setInteractorResponse(FORBIDDEN);
                    break;

                case 404:
                    error.setInteractorResponse(NOT_FOUND);
                    break;

                case 405:
                    error.setInteractorResponse(METHOD_NOT_ALLOWED);
                    break;

                case 406:
                    error.setInteractorResponse(NOT_ACCEPTABLE);
                    break;

                case 415:
                    error.setInteractorResponse(UNSUPPORTED_MEDIA_TYPE);
                    break;

                case 500:
                    error.setInteractorResponse(INTERNAL_SERVER_ERROR);
                    break;

                case 502:
                    error.setInteractorResponse(BAD_GATEWAY);
                    break;

                case 503:
                    error.setInteractorResponse(SERVICE_UNAVAILABLE);
                    break;

                case 504:
                    error.setInteractorResponse(GATEWAY_TIMEOUT);
                    break;

                default:
                    error.setInteractorResponse(UNKNOWN);
                    break;

            }

        }
        return error;

    }


    public void onFailure(Call<T> call, Throwable t) {

        this.callback.onResponse(false);
        if (t instanceof SocketTimeoutException) {

            ErrorEntity error = new ErrorEntity();
            error.setStatus("Error");
            error.setUiErrorMessage("Socket TimeOut UiError Msg");
            error.setExceptionMessage("Socket TimeOut Error Msg");
            error.setInteractorResponse(SOCKET_TIMEOUT);
            this.callback.onFail(error);
            this.callback.onFinish(false,false);

        } else if (t instanceof TimeoutException) {

            ErrorEntity error = new ErrorEntity();
            error.setStatus("Error");
            error.setUiErrorMessage("TimeOut UiError Msg");
            error.setExceptionMessage("TimeOut Error Msg");
            error.setInteractorResponse(TIMEOUT);
            this.callback.onFail(error);
            this.callback.onFinish(false,false);

        }
        else {
            this.callback.onFinish(false,false);
        }


    }
}
