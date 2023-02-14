package DAO;

import Entity.Student;
import Entity.Tcome;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TcomeDAO implements TcomeDAOImplement {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Boolean addTcome(Connection conn, String s_id, String reason, String Addresses, Date comedate) {
        String sql = "INSERT into  t_come(s_id,reason,Addresses,comedate,state)  VALUES (?,?,?,?,0)";
        try {
            queryRunner.update(conn, sql, s_id, reason, Addresses, comedate);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tcome> getTcome(Connection conn, String s_id, int state) {
        String sql = "select * from t_come where s_id=? and sate=?";
        try {
            List<Tcome> tcomes = queryRunner.query(conn, sql, new BeanListHandler<>(Tcome.class), s_id, state);
            return tcomes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean changeState(Connection conn, String t_id, int state) {
        String sql = "update t_come set state=? where t_id=? ";
        try {
            queryRunner.update(conn, sql, state, t_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean reject(Connection conn, String t_id, String comment) {
        String sql = "update t_come set state=3,comment=? where t_id=? ";
        try {
            queryRunner.update(conn, sql, comment, t_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> getNMostStudent(Connection conn, int n, String className, String deptID) {
        String sql = "SELECT\n" +
                "t_come.s_id,\n" +
                "Count(*)\n" +
                "FROM\n" +
                "t_come\n" +
                "INNER JOIN student\n" +
                "WHERE\n" +
                "t_come.s_id = student.s_id AND\n" +
                "\n" +
                "GROUP BY\n" +
                "t_come.s_id\n" +
                "ORDER BY\n" +
                "COUNT(*) DESC\n" +
                "LIMIT ?\n";
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), n);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
