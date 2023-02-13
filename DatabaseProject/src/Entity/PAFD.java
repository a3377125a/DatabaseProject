package Entity;


import java.util.Date;

public class PAFD {
    private String s_id;
    private Date date;
    private String address;
    private float temper;

    public PAFD() {
    }

    public PAFD(String s_id, String address, Date date, float temper) {
        this.s_id = s_id;
        this.address = address;
        this.date = date;
        this.temper = temper;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTemper(float temper) {
        this.temper = temper;
    }

    public String getS_id() {
        return s_id;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public float getTemper() {
        return temper;
    }

    @Override
    public String toString() {
        return "PAFD{" +
                "s_id='" + s_id + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", temper=" + temper +
                '}';
    }
}
