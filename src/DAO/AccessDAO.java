package DAO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccessDAO implements AcessDAOImplement {
    private QueryRunner queryRunner = new QueryRunner();

    //查询学生入校权限，得到出入校校区名称
    public List<String> accesses(Connection conn, String s_id) {
        String sql = "SELECT cname from access WHERE access=1 and s_id=?";
        try {
            List<String> cnames = queryRunner.query(conn, sql, new ColumnListHandler<String>(), s_id);
            return cnames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭某个学生所有入校权限
    public Boolean closeAccess(Connection conn, String s_id) {
        String sql = "UPDATE access SET access=0 WHERE s_id=?";
        try {
            queryRunner.update(conn, sql, s_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //开启某个学生全部入校权限
    public Boolean openAccess(Connection conn, String s_id) {
        String sql = "UPDATE access SET access=1 WHERE s_id=?";
        try {
            queryRunner.update(conn, sql, s_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
