package com.eco.entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderList {

    @Expose
    @SerializedName("data")
    private List<Data> data;
    @Expose
    @SerializedName("pageNumber")
    private String pageNumber;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }


    public static class Data {
        @Expose
        @SerializedName("isDelivered")
        private int isDelivered;
        @Expose
        @SerializedName("bagCount")
        private int bagCount;
        @Expose
        @SerializedName("region")
        private String region;
        @Expose
        @SerializedName("editDate")
        private String editDate;
        @Expose
        @SerializedName("runDate")
        private String runDate;
        @Expose
        @SerializedName("enable")
        private int enable;
        @Expose
        @SerializedName("period")
        private int period;
        @Expose
        @SerializedName("createDate")
        private String createDate;
        @Expose
        @SerializedName("address")
        private String address;
        @Expose
        @SerializedName("location")
        private Location location;
        @Expose
        @SerializedName("Request")
        private Map<String, String> Request = new HashMap<>();

        @Expose
        @SerializedName("userId")
        private int userId;
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("rowId")
        private int rowId;

        @Expose
        @SerializedName("costType")
        private int costType = 1;

        public int getIsDelivered() {
            return isDelivered;
        }

        public void setIsDelivered(int isDelivered) {
            this.isDelivered = isDelivered;
        }

        public int getBagCount() {
            return bagCount;
        }

        public void setBagCount(int bagCount) {
            this.bagCount = bagCount;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getEditDate() {
            return editDate;
        }

        public void setEditDate(String editDate) {
            this.editDate = editDate;
        }

        public String getRunDate() {
            return runDate;
        }

        public void setRunDate(String runDate) {
            this.runDate = runDate;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }


        public Map<String, String> getRequest() {
            return Request;
        }

        public void setRequest(Map<String, String> request) {
            Request = request;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRowId() {
            return rowId;
        }

        public void setRowId(int rowId) {
            this.rowId = rowId;
        }

        public int getCostType() {
            return costType;
        }

        public void setCostType(int costType) {
            this.costType = costType;
        }
    }

    public static class Location {
        public Location(double lng, double lat) {
            this.lng = lng;
            this.lat = lat;
        }

        public Location() {
        }

        @Expose
        @SerializedName("lng")
        private double lng;
        @Expose
        @SerializedName("lat")
        private double lat;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }

    public static class rowItem {
        private String type;
        private String cnt;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCnt() {
            return cnt;
        }

        public void setCnt(String cnt) {
            this.cnt = cnt;
        }
    }


}
