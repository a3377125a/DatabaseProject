package DAO;

import Entity.Instructor;
import Entity.Student;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class InstructorDAO implements InstructorImplement{
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Instructor GetInstructor(Connection conn, String id) {
        String sql = "SELECT i_id as id,name,className,dept_name from instructor NATURAL join class NATURAL join department where i_id=?";
        try {
            Instructor instructor = queryRunner.query(conn, sql, new BeanHandler<>(Instructor.class), id);
            return instructor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public Boolean IsInstructor(Connection conn, String id) {
        String sql="select count(*)  from instructor where i_id=?";
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
