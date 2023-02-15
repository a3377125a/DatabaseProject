package Entity;

import java.util.Date;

public class Log {
    private  int log_id,campusId;
    private  String s_id,action;
    private  Date time;

    public Log() {
    }

    public Log(int log_id, int campusId, String s_id, String action, Date time) {
        this.log_id = log_id;
        this.campusId = campusId;
        this.s_id = s_id;
        this.action = action;
        this.time = time;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" +
                "log_id=" + log_id +
                ", campusId=" + campusId +
                ", s_id='" + s_id + '\'' +
                ", action='" + action + '\'' +
                ", time=" + time +
                '}';
    }
}
