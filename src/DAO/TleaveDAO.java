package DAO;

import Entity.Tcome;
import Entity.Tleave;
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
        String sql = "update t_leave set state=3,comment=? where t_id=? ";
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


}
