package DAO;

import Entity.Student;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LogDAO implements LogDAOImplement{

   private QueryRunner queryRunner=new QueryRunner();
   //过去 n 天每个院系学生产生最多出入校记录的校区。
   public void getCampus(Connection conn,int n) throws SQLException {
       String sql="SELECT dept_name from department";
       List<String> list=queryRunner.query(conn,sql,new ColumnListHandler<String>());
       sql="(SELECT cname from log_campus  where dept_name=? group by cname  ORDER BY count(*) desc LIMIT 1)";
       for (String s:list) {
           String cname= queryRunner.query(conn,sql,new ScalarHandler<>(),s);
           System.out.println(s+"    "+cname);
       }
   }
    //已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间；
   public List<Object[]> getLeaveStudents(Connection conn) throws SQLException {
       String sql="select s_id,time1 from student_state where action='离校'";
       List<Object[]> studentLists=queryRunner.query(conn,sql,new ArrayListHandler());
       //总是差八个小时？？
      /* for (Object [] objects:studentLists) {
           System.out.println(objects[0]+"    "+(Date)objects[1]);
       }*/
       return studentLists;
   }
   //过去 n 天一直在校未曾出校的学生，支持按多级范围（全校、院系、班级）进行筛选
   public void UnLeaveStudent(Connection conn,int n) throws SQLException {
       String sql="select s_id FROM  student_state where s_id not in ( SELECT DISTINCT s_id from log where time > ? ) and action='入校'";
       SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(new Date());
       calendar.add(Calendar.DATE,-n);
       List<String> list=queryRunner.query(conn,sql,new ColumnListHandler<String>(),sdf.format(calendar.getTime()));
       for (String s:list) {
           System.out.println(s);
       }
   }
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            LogDAO logDAO=new LogDAO();
            logDAO.UnLeaveStudent(connection,1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(connection);
        }
    }
}
