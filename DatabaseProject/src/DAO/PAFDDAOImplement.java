package DAO;

import Entity.PAFD;

import java.sql.Connection;

import java.util.Date;
import java.util.List;

public interface PAFDDAOImplement {
    //查询当日日报是否已填写
    public Boolean isAdd(Connection conn,String s_id,Date date);
    //填写当日健康日报
    public Boolean addPAFD(Connection conn, String s_id, String address, Date date, float temper) ;
    //查询过去n天的每日填报信息
    public List<PAFD> getPAFDs(Connection conn,String s_id, int n);

}
