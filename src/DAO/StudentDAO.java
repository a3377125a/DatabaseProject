package DAO;

import Entity.Student;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO implements StudentImplement {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public Student GetStudent(Connection conn, String id) {
        String sql = "SELECT  s_id,name,className, tellphone, dept_name as department , email,adress,familyAdress,IdNumber,type from student NATURAL JOIN department where s_id=?";
        try {
            Student student = queryRunner.query(conn, sql, new BeanHandler<>(Student.class), id);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        public static boolean insertStudent(Student student) {
            try {
                Connection connection = DBUtil.getConnection();
                String sql = "insert into student () values(?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                DBUtil.setPreparedStatementWithArgs(preparedStatement);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }*/
    public boolean InsertStudent(Connection conn,Student student) {
        String sql = "insert into student () values(?, ?, ?, ?)";
        return true;
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.GetStudent(connection, "20301234567");
            System.out.println(student);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(connection);
        }

    }
}
