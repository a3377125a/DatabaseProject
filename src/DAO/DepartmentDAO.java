package DAO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDAO {
    private final QueryRunner queryRunner = new QueryRunner();

    public List<Object[]> getDept(Connection conn) {
        String sql="select deptId,dept_name,a_id from department";
        try {
            return queryRunner.query(conn, sql, new ArrayListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
