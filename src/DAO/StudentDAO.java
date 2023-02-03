package DAO;

import Entity.Student;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

    @Override
    public Boolean IsStudent(Connection conn, String id) {
        String sql="select count(*)  from student where s_id=?";
        long m= 0;
        try {
            m = queryRunner.query(conn,sql,new ScalarHandler<>(),id);
            if(m==0) return false;
            else  return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        String sql = "insert into student () values(?, ?, ?, ?,?,?,?,?,?,?)";
        try {
            queryRunner.update(conn,sql,student.getAdress(),student.getClassName(),student.getName(),
                    student.getDepartment(),student.getEmail(),student.getFamilyAdress(),student.getS_id(),student.getTellphone(),student.getType(),student.getIdNumber());
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
