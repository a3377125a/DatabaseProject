package DAO;

import Entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LogDAOImplement {
    //过去 n 天每个院系学生产生最多出入校记录的校区。
    Map<String,String> getCampus(Connection conn, int n) ;

    //已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间，全校范围内
    List<Object[]> getLeaveStudents(Connection conn) ;
    //已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间，院系范围内即院系管理员查询
    List<Object[]> getLeaveStudents1(Connection conn,String a_id) ;

    //    班级范围内
    List<Object[]> getLeaveStudents2(Connection conn, String className, int deptID);

    //过去 n 天一直在校未曾出校的学生，全校进行筛选
    List<String> UnLeaveStudent(Connection conn, int n) ;

    //过去 n 天一直在校未曾出校的学生，全系进行筛选(输入的是院系名)
    List<Student> UnLeaveStudent1(Connection conn, int n, int deptID);

    //过去 n 天一直在校未曾出校的学生，全班进行筛选(需要院系名和班级名共同确定一个班级）
    List<Student> UnLeaveStudent2(Connection conn, int n, String className, int deptID);

    //查询学生（从当天算起）过去一年的离校总时长。
    String LeaveTime(Connection conn,String s_id);

    //前 n 个平均离校时间最长的学生，支持按多级范围（全校、院系、班级）进行筛选；

}
