package DAO;

import Entity.PAFD;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import java.util.List;

public class PAFDDAO implements PAFDDAOImplement {
    private QueryRunner queryRunner = new QueryRunner();

    public static void main(String[] args) throws ParseException {
        PAFDDAO pafddao = new PAFDDAO();
        Connection conn = DBUtil.getConnection();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newTime = format.parse("2023-2-10");
        System.out.println(pafddao.isAdd(conn, "20301234568", newTime));
        List<PAFD> pafds= pafddao.getPAFDs(conn,"20301234568",5);
        for (PAFD p: pafds ) {
            System.out.println(p);
        }
        pafddao.addPAFD(conn,"20301234568","shanghai",new Date(),36.7f);
    }

    @Override
    public Boolean isAdd(Connection conn, String s_id, Date date) {
        String sql = "SELECT count(*) FROM hjoutnal WHERE s_id=? AND date>=?";
        long m = 0;
        try {
            m = queryRunner.query(conn, sql, new ScalarHandler<>(), s_id, date);
            if (m == 0) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean addPAFD(Connection conn, String s_id, String address, Date date, float temper) {
        String sql = "INSERT INTO hjoutnal VALUES(?,?,?,?)";
        try {
            queryRunner.update(conn, sql, s_id, date, address, temper);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PAFD> getPAFDs(Connection conn, String s_id, int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -n);
        String sql = "select * from hjoutnal where s_id=? and  date >=? ";
        try {
            List<PAFD> PAFDs = queryRunner.query(conn, sql, new BeanListHandler<>(PAFD.class), s_id, sdf.format(calendar.getTime()));
            return PAFDs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
