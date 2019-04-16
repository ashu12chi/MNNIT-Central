package com.npdevs.drivermnnitcentral;

public class Drivers {
    private String name,mob,cab,pwd,lat,lng,isRiding;
    public Drivers(){}
    public Drivers(String name,String pwd,String cab,String mob,String lat,String lng,String isRiding) {
        this.name = name;
        this.mob = mob;
        this.pwd = pwd;
        this.cab=cab;
        this.lat=lat;
        this.lng=lng;
        this.isRiding=isRiding;
    }

    public String getName() {
        return name;
    }
    public String getPwd() {
        return pwd;
    }
    public String getCab() {
        return cab;
    }
    public String getMob() {
        return mob;
    }
    public String getLat() {
        return lat;
    }
    public String getLng() {
        return lng;
    }
    public String getIsRiding() {
        return isRiding;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsRiding(String isRiding) {
        this.isRiding = isRiding;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
