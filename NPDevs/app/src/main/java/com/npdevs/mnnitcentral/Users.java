package com.npdevs.mnnitcentral;

public class Users {
    private String name,regno,hostel,grp,pwd,lat,lng,cab,isRiding,messbal,isEat;
    public Users()
    {

    }
    public Users(String name,String regno,String hostel,String grp,String pwd,String lat,String lng,String cab,String isRiding,String messbal,String isEat)
    {
        this.name=name;
        this.regno=regno;
        this.hostel=hostel;
        this.grp=grp;
        this.pwd=pwd;
        this.lat=lat;
        this.cab=cab;
        this.isRiding=isRiding;
        this.messbal=messbal;
        this.isEat=isEat;
        this.lng=lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrp() {
        return grp;
    }

    public String getCab() {
        return cab;
    }

    public String getHostel() {
        return hostel;
    }

    public String getIsEat() {
        return isEat;
    }

    public String getIsRiding() {
        return isRiding;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getMessbal() {
        return messbal;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRegno() {
        return regno;
    }

    public void setIsRiding(String isRiding) {
        this.isRiding = isRiding;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public void setIsEat(String isEat) {
        this.isEat = isEat;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setMessbal(String messbal) {
        this.messbal = messbal;
    }
}
