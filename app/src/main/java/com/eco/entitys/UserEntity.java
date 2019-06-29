package com.eco.entitys;

public class UserEntity {
    private LocationEntity location;
    public int provinceId;
    public int cityId;
    public int countryId;
    public String username;
    public String work;
    public String name;
    public String mobileNumber;
    public String family;
    public String email;
    public int id ;
    public String address;
    public String grade;
    public String shabaNumber;
    public String gender;
    public String familyNumber;
    public int roleId;
    public String token;
    public int score;
    public UserEntity(){

    }
    public UserEntity(LocationEntity location,
                      int provinceId,
                      int cityId,
                      int countryId,
                      String username,
                      String name,
                      String family,
                      String email,
                      String address,
                      String grade,
                      String shabaNumber,
                      String gender,
                      String familyNumber,
                      int roleId, String token) {
        this.provinceId = provinceId;
        this.location =location;
        this.cityId = cityId;
        this.countryId = countryId;
        this.username = username;
        this.name = name;
        this.family = family;
        this.email = email;
        this.address = address;
        this.grade = grade;
        this.shabaNumber = shabaNumber;
        this.gender = gender;
        this.familyNumber = familyNumber;
        this.roleId = roleId;
        this.token = token;
    }
    public UserEntity(int score){
        this.score=score;
    }
}
