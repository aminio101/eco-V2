package com.eco.entitys;

public class VerifiCodeEntity {
    private String username, otpCode;

    public VerifiCodeEntity(String mobile, String code) {

        this.username = mobile;
        this.otpCode = code;

    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }
}
