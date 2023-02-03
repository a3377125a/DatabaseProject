package DAO;

import Entity.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminDAO implements  AdminImplement{
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Admin GetAdmin(Connection conn, String id) {
        String sql = "SELECT a_id id,name,dept_name from admin  NATURAL join department where a_id=?";
        try {
            Admin admin = queryRunner.query(conn, sql, new BeanHandler<>(Admin.class), id);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean IsAdmin(Connection conn, String id) {
        String sql="select count(*)  from admin where a_id=?";
        long m= 0;
        try {
            m = queryRunner.query(conn,sql,new ScalarHandler<>(),id);
            if(m==0) return false;
            else  return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
