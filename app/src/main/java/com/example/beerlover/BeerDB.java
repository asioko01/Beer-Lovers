package com.example.beerlover;


import java.io.Serializable;
import android.graphics.Bitmap;

public class BeerDB implements Serializable{
    private String beerCode;
    private String beerName;
    private int beerCheck;
    private String beerPic;
    private String beerDet;

    public BeerDB(){}
    public BeerDB(String beerCode,String beerName,String beerDet,int beerCheck,String beerPic){
        this.beerCode=beerCode;
        this.beerName=beerName;
        this.beerCheck=beerCheck;
        this.beerPic=beerPic;
        this.beerDet=beerDet;
    }

    public void setCode(String Code){
        this.beerCode=Code;
    }
    public String getCode(){
        return this.beerCode;
    }
    public void setName(String Name){
        this.beerName=Name;
    }
    public String getName(){
        return this.beerName;
    }


    public void setCheck(int beerCheck){this.beerCheck=beerCheck;}
    public int getCheck(){ return this.beerCheck;}
    public void setPic(String beerPic){this.beerPic=beerPic;}
    public String getPic(){return this.beerPic;}
    public void setDet(String det){this.beerDet=det;}
    public String getDet(){return this.beerDet;}
}
