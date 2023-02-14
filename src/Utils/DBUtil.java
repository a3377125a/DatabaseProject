package Utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    /*static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MYSQL_URL = "jdbc:mysql://localhost:3306/database_project?serverTimezone=Asia/Shanghai";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";*/

    static {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        try {
            pros.load(is);
            source= (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static  DruidDataSource source;

    public static Connection getConnection() {
        try {
            Connection conn=source.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return   null;
    }

    public static void closeResource(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void setPreparedStatementWithArgs(PreparedStatement preparedStatement, Object... args) {
        try {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
