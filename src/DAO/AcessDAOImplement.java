package DAO;

import java.sql.Connection;
import java.util.List;

public interface AcessDAOImplement {
    //查询学生入校权限，得到出入校校区名称
    List<String> accesses(Connection conn, String s_id);
    //关闭某个学生所有入校权限
    Boolean closeAccess(Connection conn,String s_id);
    //开启某个学生全部入校权限
    Boolean openAccess(Connection conn,String s_id);
}
