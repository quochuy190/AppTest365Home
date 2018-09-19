package neo.vn.test365home.Models;

import java.io.Serializable;

public class ObjMenu implements Serializable {
    private String sName;
    private int iAvata;
    private String sDescription;

    public ObjMenu(String sName, int iAvata, String sDescription) {
        this.sName = sName;
        this.iAvata = iAvata;
        this.sDescription = sDescription;
    }

    public ObjMenu() {
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getiAvata() {
        return iAvata;
    }

    public void setiAvata(int iAvata) {
        this.iAvata = iAvata;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }
}
