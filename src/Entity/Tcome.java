package Entity;

import java.util.Date;

public class Tcome {
    private String s_id, reason, Addresses;
    private Date comedate;
    private int state; //0，1，2,3代表未审批/辅导员已审批/管理员已审批（已完成）/已拒绝三个状态
    private String comment;

    public Tcome() {
    }

    public Tcome(String s_id, String reason, String addresses, Date comedate, int state, String comment) {
        this.s_id = s_id;
        this.reason = reason;
        Addresses = addresses;
        this.comedate = comedate;
        this.state = state;
        this.comment = comment;
    }


    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAddresses() {
        return Addresses;
    }

    public void setAddresses(String addresses) {
        Addresses = addresses;
    }

    public Date getComedate() {
        return comedate;
    }

    public void setComedate(Date comedate) {
        this.comedate = comedate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Tcome{" +
                "s_id='" + s_id + '\'' +
                ", reason='" + reason + '\'' +
                ", Addresses='" + Addresses + '\'' +
                ", comedate=" + comedate +
                ", state=" + state +
                ", comment='" + comment + '\'' +
                '}';
    }
}
