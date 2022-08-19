package spark.sample1;

import java.io.Serializable;

public class LogInfo implements Serializable {
    private static final long serialVersionUID = 5749943279909593929L;
    private long timeStamp;
    private String phoneNo;
    private long down;
    private long up;


    LogInfo(){}
    LogInfo(long timeStamp, String phoneNo, long down, long up){
        this.timeStamp = timeStamp;
        this.phoneNo = phoneNo;
        this.down = down;
        this.up = up;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public long getDown() {
        return down;
    }

    public long getUp() {
        return up;
    }
}