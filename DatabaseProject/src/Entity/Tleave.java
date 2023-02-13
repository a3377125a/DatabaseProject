package Entity;

import java.util.Date;

public class Tleave {
    private  int t_id;
    private String s_id, reason, destAdress;
    private Date leavedate,comedate;
    private int state; //0，1，2，3代表未审批/辅导员已审批/管理员已审批(已完成）/已拒绝三个状态
    private  String comment;

    public Tleave() {
    }



    public Tleave(int t_id, String s_id, String reason, String destAdress, Date leavedate, Date comedate, int state, String comment) {
        this.t_id = t_id;
        this.s_id = s_id;
        this.reason = reason;
        this.destAdress = destAdress;
        this.leavedate = leavedate;
        this.comedate = comedate;
        this.state = state;
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
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

    public String getDestAdress() {
        return destAdress;
    }

    public void setDestAdress(String destAdress) {
        this.destAdress = destAdress;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getComedate() {
        return comedate;
    }

    public void setComedate(Date comedate) {
        this.comedate = comedate;
    }

    @Override
    public String toString() {
        return "Tleave{" +
                "s_id='" + s_id + '\'' +
                ", reason='" + reason + '\'' +
                ", destAdress='" + destAdress + '\'' +
                ", leavedate=" + leavedate +
                ", comedate=" + comedate +
                ", state=" + state +
                ", comment='" + comment + '\'' +
                '}';
    }
}
