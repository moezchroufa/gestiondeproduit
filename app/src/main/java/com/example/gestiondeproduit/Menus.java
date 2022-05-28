package com.example.gestiondeproduit;

public class Menus {
    private  String activity_name;
    private  int imgid;

    public Menus(String activity_name,int imgid) {
        this.activity_name = activity_name;
        this.imgid = imgid;
    }

    public  String getMenu_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public  int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
