package com.eco.entitys;



import com.eco.enums.InteractorResponse;

import static com.eco.enums.InteractorResponse.UNKNOWN;


public class ErrorEntity   {

    private String status, uiErrorMessage, exceptionMessage;
    private InteractorResponse interactorResponse;

    public ErrorEntity(String status, String uiErrorMessage, String exceptionMessage, InteractorResponse interactorResponse) {

        this.status = status;
        this.uiErrorMessage = uiErrorMessage;
        this.exceptionMessage = exceptionMessage;
        this.interactorResponse = interactorResponse;

    }

    public ErrorEntity(String status, String uiErrorMessage, String exceptionMessage) {

        this.status = status;
        this.uiErrorMessage = uiErrorMessage;
        this.exceptionMessage = exceptionMessage;
        interactorResponse = UNKNOWN;

    }

    public ErrorEntity(String status, String uiErrorMessage) {

        this.status = status;
        this.uiErrorMessage = uiErrorMessage;
        exceptionMessage = "";
        interactorResponse = UNKNOWN;

    }

    public ErrorEntity(String status) {

        this.status = status;
        uiErrorMessage = "";
        exceptionMessage = "";
        interactorResponse = UNKNOWN;

    }

    public ErrorEntity() {

        status = "";
        uiErrorMessage = "";
        exceptionMessage = "";
        interactorResponse = UNKNOWN;

    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;

    }

    public String getUiErrorMessage() {

        return uiErrorMessage;

    }

    public void setUiErrorMessage(String uiErrorMessage) {

        this.uiErrorMessage = uiErrorMessage;

    }

    public String getExceptionMessage() {

        return exceptionMessage;

    }

    public void setExceptionMessage(String exceptionMessage) {

        this.exceptionMessage = exceptionMessage;

    }

    public InteractorResponse getInteractorResponse() {

        return interactorResponse;

    }

    public void setInteractorResponse(InteractorResponse interactorResponse) {

        this.interactorResponse = interactorResponse;

    }

}
