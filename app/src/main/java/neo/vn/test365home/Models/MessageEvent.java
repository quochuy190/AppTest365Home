package neo.vn.test365home.Models;

public class MessageEvent {
    public final String message;
    public final float point;
    public final long time;
    public final Childrens obj;

    public MessageEvent(String message, float point, long time, Childrens obj) {
        this.message = message;
        this.point = point;
        this.time = time;
        this.obj = obj;
    }
}
