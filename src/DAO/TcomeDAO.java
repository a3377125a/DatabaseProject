package DAO;

import Entity.Student;
import Entity.Tcome;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TcomeDAO implements TcomeDAOImplement {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Boolean addTcome(Connection conn, String s_id, String reason, String Addresses, Date comedate, Date date) {
        String sql = "INSERT into  t_come(s_id,reason,Addresses,comedate,state,date)  VALUES (?,?,?,?,0,?)";
        try {
            queryRunner.update(conn, sql, s_id, reason, Addresses, comedate, date);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tcome> getTcome(Connection conn, String s_id, int state) {
        String sql = "select * from t_come where s_id=? and state=?";
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
        String sql = "update t_come set state=4,comment=? where t_id=? ";
        try {
            queryRunner.update(conn, sql, comment, t_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //    班级范围查询前 n 个提交入校申请最多的学生
    public List<Student> getNMostStudent(Connection conn, int n, String className, int deptID) {
        String sql = "SELECT\n" +
                "t_come.s_id,\n" +
                "student.name\n" +
                "FROM\n" +
                "t_come\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "student.className = ? AND\n" +
                "student.deptId = ?\n" +
                "GROUP BY\n" +
                "t_come.s_id\n" +
                "ORDER BY\n" +
                "COUNT(*) DESC\n" +
                "LIMIT ?\n";
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), className, deptID, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    院系范围查询前 n 个提交入校申请最多的学生
    public List<Student> getNMostStudent(Connection conn, int n, int deptID) {
        String sql = "SELECT\n" +
                "t_come.s_id,\n" +
                "student.name\n" +
                "FROM\n" +
                "t_come\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "student.deptId = ?\n" +
                "GROUP BY\n" +
                "t_come.s_id\n" +
                "ORDER BY\n" +
                "COUNT(*) DESC\n" +
                "LIMIT ?\n";
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), deptID, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    全校范围查询前 n 个提交入校申请最多的学生
    public List<Student> getNMostStudent(Connection conn, int n) {
        String sql = "SELECT\n" +
                "t_come.s_id,\n" +
                "student.name\n" +
                "FROM\n" +
                "t_come\n" +
                "NATURAL JOIN student\n" +
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







    public Tcome getTcomeByID(Connection conn, int t_id) {
        String sql = "select * from t_come where t_id=?";
        try {
            return queryRunner.query(conn, sql, new BeanHandler<>(Tcome.class), t_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        TcomeDAO tcomeDAO = new TcomeDAO();

        Connection conn = DBUtil.getConnection();
        List<Student> result = tcomeDAO.getNMostStudent(conn, 3, "1班", 1);
        System.out.println("1");


        DBUtil.closeResource(conn);
    }

    //    班级范围查询过去 n 天尚未批准的入校申请
    public List<Tcome> getNTcome(Connection conn, int n, String className, int deptID) {

        String sql = "SELECT\n" +
                "*\n" +
                "FROM\n" +
                "t_come\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "t_come.date >= ? AND\n" +
                "student.className = ? AND\n" +
                "student.deptId = ? AND\n" +
                "t_come.state <= 2";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Tcome.class), sdf.format(calendar.getTime()), className, deptID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //    院系范围查询
    public List<Tcome> getNTcome(Connection conn, int n, int deptID) {

        String sql = "SELECT\n" +
                "*\n" +
                "FROM\n" +
                "t_come\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "t_come.date >= ? AND\n" +
                "student.deptId = ? AND\n" +
                "t_come.state <= 2";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Tcome.class), sdf.format(calendar.getTime()), deptID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //    全校范围查询
    public List<Tcome> getNTcome(Connection conn, int n) {

        String sql = "SELECT\n" +
                "*\n" +
                "FROM\n" +
                "t_come\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "t_come.date >= ? AND\n" +
                "t_come.state <= 2";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Tcome.class), sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
