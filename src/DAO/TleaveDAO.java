package DAO;

import Entity.Tleave;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TleaveDAO implements TleaveDAOImplement {
    private QueryRunner queryRunner=new QueryRunner();
    @Override
    public Boolean addTleave(Connection conn, String s_id, String reason, String destAdress, Date leavedate, Date comedate,Date date) {
        String sql="insert into t_leave(s_id,reason,destAdress,leavedate,comedate,state,date) values(?,?,?,?,?,0,?)";
        try {
            queryRunner.update(conn,sql,s_id,reason,destAdress,leavedate,comedate,date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tleave> getTleave(Connection conn, String s_id, int state) {
        String sql="select * from t_leave where s_id=? and state=?";
        try {
            List<Tleave> tleaves=  queryRunner.query(conn,sql,new BeanListHandler<>(Tleave.class),s_id,state);
            return  tleaves;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean changeState(Connection conn, String t_id, int state) {
        String sql="update t_leave set state=? where t_id=? ";
        try {
            queryRunner.update(conn,sql,state,t_id);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean reject(Connection conn, String t_id,String comment) {
        String sql="update t_leave set state=3,comment=? where t_id=? ";
        try {
            queryRunner.update(conn,sql,comment,t_id);
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
