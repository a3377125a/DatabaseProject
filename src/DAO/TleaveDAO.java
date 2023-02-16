package DAO;

import Entity.Student;
import Entity.Tcome;
import Entity.Tleave;
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

public class TleaveDAO implements TleaveDAOImplement {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Boolean addTleave(Connection conn, String s_id, String reason, String destAdress, Date leavedate, Date comedate, Date date) {
        String sql = "insert into t_leave(s_id,reason,destAdress,leavedate,comedate,state,date) values(?,?,?,?,?,0,?)";
        try {
            queryRunner.update(conn, sql, s_id, reason, destAdress, leavedate, comedate, date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tleave> getTleave(Connection conn, String s_id, int state) {
        String sql = "select * from t_leave where s_id=? and state=?";
        try {
            List<Tleave> tleaves = queryRunner.query(conn, sql, new BeanListHandler<>(Tleave.class), s_id, state);
            return tleaves;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean changeState(Connection conn, String t_id, int state) {
        String sql = "update t_leave set state=? where t_id=? ";
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
        String sql = "update t_leave set state=4,comment=? where t_id=? ";
        try {
            queryRunner.update(conn, sql, comment, t_id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Tleave getTleaveByID(Connection conn, int t_id) {
        String sql="select * from t_leave where t_id=?";
        try {
            return queryRunner.query(conn, sql, new BeanHandler<>(Tleave.class), t_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    班级范围查询N天内的离校申请。
    public List<Tleave> getNTleave(Connection conn, int n, String className, String deptID) {
        String sql = "SELECT\n" +
                "*\n" +
                "FROM\n" +
                "t_leave\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "student.deptId = ? AND\n" +
                "student.className = ? AND\n" +
                "t_leave.date >= ? AND\n" +
                "t_leave.state <= 2";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Tleave.class), deptID, className, sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //    院系范围查询。
    public List<Tleave> getNTleave(Connection conn, int n, String deptID) {
        String sql = "SELECT\n" +
                "*\n" +
                "FROM\n" +
                "t_leave\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "student.deptId = ? AND\n" +
                "t_leave.date >= ? AND\n" +
                "t_leave.state <= 2";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Tleave.class), deptID, sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //    全校范围查询。
    public List<Tleave> getNTleave(Connection conn, int n) {
        String sql = "SELECT\n" +
                "*\n" +
                "FROM\n" +
                "t_leave\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "t_leave.date >= ? AND\n" +
                "t_leave.state <= 2";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Tleave.class), sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    //    班级范围查询未提交出校申请但离校状态超过 24h 的学生数量、个人信息。
    public List<Student> getOver24H(Connection conn, String className, String deptID) {

        String sql = "SELECT DISTINCT\n" +
                "ss1.s_id,student.name\n" +
                "FROM\n" +
                "student_state AS ss1\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "student.deptId = ? AND\n" +
                "student.className = ? AND\n" +
                "ss1.action = \"离校\" AND\n" +
                "ss1.time1 <= ? AND\n" +
                "ss1.s_id NOT IN \n" +
                "(\n" +
                "SELECT\n" +
                "student_state.s_id\n" +
                "FROM\n" +
                "student_state\n" +
                "NATURAL JOIN t_leave\n" +
                "WHERE\n" +
                "t_leave.state = 3 AND\n" +
                "student_state.action = \"离校\" AND\n" +
                "student_state.time1 >= t_leave.leavedate AND\n" +
                "t_leave.comedate >= ?\n" +
                ")\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar2.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), deptID, className, sdf.format(calendar.getTime()), sdf.format(calendar2.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    院系范围查询
    public List<Student> getOver24H(Connection conn, String deptID) {

        String sql = "SELECT DISTINCT\n" +
                "ss1.s_id,student.name\n" +
                "FROM\n" +
                "student_state AS ss1\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "student.deptId = ? AND\n" +
                "ss1.action = \"离校\" AND\n" +
                "ss1.time1 <= ? AND\n" +
                "ss1.s_id NOT IN \n" +
                "(\n" +
                "SELECT\n" +
                "student_state.s_id\n" +
                "FROM\n" +
                "student_state\n" +
                "NATURAL JOIN t_leave\n" +
                "WHERE\n" +
                "t_leave.state = 3 AND\n" +
                "student_state.action = \"离校\" AND\n" +
                "student_state.time1 >= t_leave.leavedate AND\n" +
                "t_leave.comedate >= ?\n" +
                ")\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar2.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), deptID, sdf.format(calendar.getTime()), sdf.format(calendar2.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    全校范围查询
    public List<Student> getOver24H(Connection conn) {

        String sql = "SELECT DISTINCT\n" +
                "ss1.s_id,student.name\n" +
                "FROM\n" +
                "student_state AS ss1\n" +
                "NATURAL JOIN student\n" +
                "WHERE\n" +
                "ss1.action = \"离校\" AND\n" +
                "ss1.time1 <= ? AND\n" +
                "ss1.s_id NOT IN \n" +
                "(\n" +
                "SELECT\n" +
                "student_state.s_id\n" +
                "FROM\n" +
                "student_state\n" +
                "NATURAL JOIN t_leave\n" +
                "WHERE\n" +
                "t_leave.state = 3 AND\n" +
                "student_state.action = \"离校\" AND\n" +
                "student_state.time1 >= t_leave.leavedate AND\n" +
                "t_leave.comedate >= ?\n" +
                ")\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar2.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), sdf.format(calendar.getTime()), sdf.format(calendar2.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    //    班级范围查询已提交出校申请但未离校的学生数量、个人信息；
    public List<Student> getStays(Connection conn, String className, String deptID){

        String sql = "SELECT DISTINCT\n" +
                "student_state.s_id,student.name\n" +
                "FROM\n" +
                "student_state\n" +
                "NATURAL JOIN student\n" +
                "NATURAL JOIN t_leave\n" +
                "WHERE\n" +
                "student.deptId = ? AND\n" +
                "student.className = ? AND\n" +
                "student_state.action = \"入校\" AND\n" +
                "t_leave.state = 3 AND\n" +
                "t_leave.leavedate <= ?\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), deptID, className, sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    //    院系范围查询
    public List<Student> getStays(Connection conn,String deptID){

        String sql = "SELECT DISTINCT\n" +
                "student_state.s_id,student.name\n" +
                "FROM\n" +
                "student_state\n" +
                "NATURAL JOIN student\n" +
                "NATURAL JOIN t_leave\n" +
                "WHERE\n" +
                "student.deptId = ? AND\n" +
                "student_state.action = \"入校\" AND\n" +
                "t_leave.state = 3 AND\n" +
                "t_leave.leavedate <= ?\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), deptID, sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    //    全校范围查询
    public List<Student> getStays(Connection conn){

        String sql = "SELECT DISTINCT\n" +
                "student_state.s_id,student.name\n" +
                "FROM\n" +
                "student_state\n" +
                "NATURAL JOIN student\n" +
                "NATURAL JOIN t_leave\n" +
                "WHERE\n" +
                "student_state.action = \"入校\" AND\n" +
                "t_leave.state = 3 AND\n" +
                "t_leave.leavedate <= ?\n";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), sdf.format(calendar.getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

}
