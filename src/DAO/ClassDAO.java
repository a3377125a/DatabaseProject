package DAO;

import Entity.Tleave;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClassDAO {
    private final QueryRunner queryRunner = new QueryRunner();


    public List<String> getClassByDeptId(Connection conn, String deptId) {
        String sql="select className from class where deptId=?";
        try {
            return queryRunner.query(conn, sql, new ColumnListHandler<>(), deptId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }















}
