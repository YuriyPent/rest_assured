package data;

import java.sql.Timestamp;
import java.util.Date;

public class TimeStamp {

    public static Timestamp  getTimeStamp() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        System.out.println("Current Time Stamp: " + ts);
        return ts;
    }
}
