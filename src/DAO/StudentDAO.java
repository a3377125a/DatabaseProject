package DAO;

import Entity.Student;
import Utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {


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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }











}
