package DAO;

import Entity.Tcome;
import Entity.Tleave;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public interface TleaveDAOImplement {
    //添加离校申请
    public Boolean addTleave(Connection conn, String s_id, String reason, String destAdress,
                             Date leavedate, Date comedate,Date date);

    //查看对应学生的对应状态申请
    public List<Tleave> getTleave(Connection conn, String s_id, int state);

    //修改对应申请表的对应状态
    public Boolean changeState(Connection conn, String t_id, int state);

    //拒绝申请表的申请
    public Boolean reject(Connection conn, String t_id,String comment);


}

