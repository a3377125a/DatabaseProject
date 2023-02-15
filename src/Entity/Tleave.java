package Entity;

import java.util.Date;

public class Tleave {
    private int t_id;
    private String s_id, reason, destAdress;
    private Date leavedate, comedate, date;
    private int state; //1，2，3，4代表未审批/辅导员已审批/管理员已审批(已完成）/已拒绝三个状态
    private String comment;

    public Tleave() {

    }

    public Tleave(int t_id, String s_id, String reason, String destAdress, Date leavedate, Date comedate, Date date, int state, String comment) {
        this.t_id = t_id;
        this.s_id = s_id;
        this.reason = reason;
        this.destAdress = destAdress;
        this.leavedate = leavedate;
        this.comedate = comedate;
        this.date = date;
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

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState1() {
        String s = null;
        switch (state) {
            case 1:
                s = "待辅导员审批";
                break;
            case 2:
                s = "待院系审批";
                break;
            case 3:
                s = "申请已成功";
                break;
            case 4:
                s = "申请被拒绝";
                break;
        }
        return s;
    }

    @Override
    public String toString() {
        String s = "--------------------------\n" + "    离校申请\n" +
                "申请ID:" + t_id + '\n' +
                "学生ID:" + s_id + '\n' +
                "出校原因：" + reason + '\n' +
                "目的地：" + destAdress + '\n' +
                "预期离开日期:" + leavedate + '\n' +
                "拟返校日期:" + comedate + '\n' +
                "申请日期：" + date + '\n' +
                "当前申请状态：" + getState1() + '\n';
        if ((state == 4) && (comment != null))
            s += "拒绝理由为：" + comment + "\n";
        return s;
    }
}
