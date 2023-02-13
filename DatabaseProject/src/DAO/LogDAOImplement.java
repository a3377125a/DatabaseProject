package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LogDAOImplement {
    //过去 n 天每个院系学生产生最多出入校记录的校区。
    void getCampus(Connection conn, int n) throws SQLException;

    //已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间；
    List<Object[]> getLeaveStudents(Connection conn) throws SQLException;

    //过去 n 天一直在校未曾出校的学生，支持按多级范围（全校、院系、班级）进行筛选
    void UnLeaveStudent(Connection conn, int n) throws SQLException;

    //查询学生（从当天算起）过去一年的离校总时长。

    //前 n 个平均离校时间最长的学生，支持按多级范围（全校、院系、班级）进行筛选；

}
