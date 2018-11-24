package neo.vn.test365home.Models;

import java.io.Serializable;

public class Game implements Serializable {
    private String sName;
    private String sImage;
    private boolean isOff;
    private int iAvata;
    private int iContent;


    public Game(String sName, String sImage, boolean isOff, int iAvata, int iContent) {
        this.sName = sName;
        this.sImage = sImage;
        this.isOff = isOff;
        this.iAvata = iAvata;
        this.iContent = iContent;
    }

    public int getiContent() {
        return iContent;
    }

    public void setiContent(int iContent) {
        this.iContent = iContent;
    }

    public Game() {
    }

    public int getiAvata() {
        return iAvata;
    }

    public void setiAvata(int iAvata) {
        this.iAvata = iAvata;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsImage() {
        return sImage;
    }

    public void setsImage(String sImage) {
        this.sImage = sImage;
    }

    public boolean isOff() {
        return isOff;
    }

    public void setOff(boolean off) {
        isOff = off;
    }
}
