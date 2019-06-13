package com.eco.entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteAddressEntity {

    @Expose
    @SerializedName("editDate")
    private String editDate;
    @Expose
    @SerializedName("createDate")
    private String createDate;
    @Expose
    @SerializedName("location")
    private LocationEntity location;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public String getEditDate () {
        return editDate;
    }

    public void setEditDate (String editDate) {
        this.editDate = editDate;
    }

    public String getCreateDate () {
        return createDate;
    }

    public void setCreateDate (String createDate) {
        this.createDate = createDate;
    }

    public LocationEntity getLocation () {
        return location;
    }

    public void setLocation (LocationEntity location) {
        this.location = location;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

}
