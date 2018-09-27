package neo.vn.test365home.Models;

public class Game {
    private String sName;
    private String sImage;
    private boolean isOff;

    public Game(String sName, String sImage, boolean isOff) {
        this.sName = sName;
        this.sImage = sImage;
        this.isOff = isOff;
    }

    public Game() {
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
