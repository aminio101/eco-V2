package com.eco.entitys;

public class RequstUserUpdateEntity {
    public int provinceId;
    public int cityId;
    public int  countryId;
    public String name;
    public String family;
    public String email;
    public String address;
    public String grade;
    public String shabaNumber;
    public String gender;
    public String familyNumber;
    public String mobile;
    public LocationEntity location = new LocationEntity().setFirstLng(0).setFirstLat(0);
}
