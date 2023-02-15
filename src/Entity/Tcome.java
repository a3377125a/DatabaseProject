package Entity;

import java.util.Date;

public class Tcome {
    private String s_id, reason, Addresses;
    private Date comedate,date;
    private int state; //1，2，3,4代表未审批/辅导员已审批/管理员已审批（已完成）/已拒绝三个状态
    private String comment;

    public Tcome() {
    }

    public Tcome(String s_id, String reason, String addresses, Date comedate, Date date, int state, String comment) {
        this.s_id = s_id;
        this.reason = reason;
        Addresses = addresses;
        this.comedate = comedate;
        this.date = date;
        this.state = state;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getState1(){
        String s=null;
        switch (state){
            case 0:s="待辅导员审批";break;
            case 1:s="待院系审批";break;
            case 2:s="申请已成功";break;
            case 3:s="申请被拒绝";break;
        }
        return  s;
    }

    @Override
    public String toString() {
        String s = "------------------------\n"+"    入校申请\n"+
                "学生ID:" + s_id + '\n' +
                "入校原因：" + reason + '\n' +
                "近7日内途径地区：" + Addresses + '\n' +
                "拟返校日期：" + comedate + '\n' +
                "申请日期：" + date + '\n' +
                "当前申请状态：" + getState1()+'\n';
        if((state==3)&&(comment!=null))
            s+="拒绝理由为："+comment+"\n";
        return s;
    }
}
