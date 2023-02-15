package DAO;

import Entity.Log;
import Entity.Student;
import Utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogDAO implements LogDAOImplement {

    private QueryRunner queryRunner = new QueryRunner();

    //过去 n 天每个院系学生产生最多出入校记录的校区。
    public Map<String, String> getCampus(Connection conn, int n) {
        String sql = "SELECT dept_name from department";
        try {
            List<String> list = queryRunner.query(conn, sql, new ColumnListHandler<String>());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -n + 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date time = calendar.getTime();
            sql = "(SELECT cname from log_campus  where dept_name=? and time>=? group by cname  ORDER BY count(*) desc LIMIT 1)";
            Map<String, String> d_Campus = new HashMap<>();
            for (String s : list) {
                String cname = queryRunner.query(conn, sql, new ScalarHandler<>(), s, time);
                if (cname == null) cname = "无出入校区记录";
                //  System.out.println(s + "    " + cname);
                d_Campus.put(s, cname);
            }
            return d_Campus;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间(全校范围内)；
    public List<Object[]> getLeaveStudents(Connection conn) {
        String sql = "select s_id,time1 from student_state where action='离校'";
        List<Object[]> studentLists = null;
        try {
            studentLists = queryRunner.query(conn, sql, new ArrayListHandler());
            for (Object[] objects : studentLists) {
                System.out.println(objects[0] + "    " + (Date) objects[1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentLists;
    }

//    院系范围内
    @Override
    public List<Object[]> getLeaveStudents1(Connection conn, String a_id) {
        String sql = "select deptId from department where a_id=?";
        int deptId = 0;
        try {
            deptId = queryRunner.query(conn, sql, new ScalarHandler<>(), a_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "select s_id,time1 from student_state natural join student where action='离校'and deptId=?";
        List<Object[]> studentLists = null;
        try {
            studentLists = queryRunner.query(conn, sql, new ArrayListHandler(), deptId);
            for (Object[] objects : studentLists) {
                System.out.println(objects[0] + "    " + (Date) objects[1]);
            }
            return studentLists;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    班级范围内
    @Override
    public List<Object[]> getLeaveStudents2(Connection conn, String className, int deptID) {
        String sql = "select s_id,name,time1 from student_state natural join student where action='离校'and deptId=? and className=?";
        try {
            return queryRunner.query(conn, sql, new ArrayListHandler(), deptID, className);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //过去 n 天一直在校未曾出校的学生，全校进行筛选
    @Override
    public List<String> UnLeaveStudent(Connection conn, int n) {
        String sql = "select s_id FROM  student_state where s_id not in ( SELECT DISTINCT s_id from log where time >= ? ) and action='入校'";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -n + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        List<String> list = null;
        try {
            list = queryRunner.query(conn, sql, new ColumnListHandler<>(), time);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*for (String s : list) {
            System.out.println(s);
        }*/
        return list;
    }

    //过去 n 天一直在校未曾出校的学生，全系进行筛选
    public List<Student> UnLeaveStudent1(Connection conn, int n, int deptID) {
        String sql = "select s_id,name FROM  student_state natural join student  where s_id not in ( SELECT DISTINCT s_id from log where time >= ? ) and action='入校' and deptId=?";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -n + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), time, deptID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //过去 n 天一直在校未曾出校的学生，班级进行筛选
    @Override
    public List<Student> UnLeaveStudent2(Connection conn, int n, String className, int deptID) {
        String sql = "select s_id,name FROM  student_state natural join student  where s_id not in ( SELECT DISTINCT s_id from log where time >= ? ) and action='入校' and className=? and deptId=?";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -n + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<>(Student.class), time, className, deptID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //毫秒转化
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }
        return sb.toString();
    }

    @Override
    public String LeaveTime(Connection conn, String s_id) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date = new Date();
        try {
            Date newTime = format.parse(String.valueOf(date.getYear()));
            String sql = "select * from log where s_id=? and time>=?";
            List<Log> logs = queryRunner.query(conn, sql, new BeanListHandler<>(Log.class), s_id, newTime);
            long time = 0;
            if (logs.get(0).getAction() == "离校") {
                int size = logs.size();
                int i;
                for (i = 1; i < size; i += 2) {
                    time += logs.get(i).getTime().getTime() - logs.get(i - 1).getTime().getTime();
                }
                if (i == size) {
                    time += date.getTime() - logs.get(i - 1).getTime().getTime();
                }
            } else {
                int size = logs.size();
                int i;
                for (i = 2; i < size; i += 2) {
                    time += logs.get(i).getTime().getTime() - logs.get(i - 1).getTime().getTime();
                }
                if (i == size) {
                    time += date.getTime() - logs.get(i - 1).getTime().getTime();
                }
            }
            if (time != 0)
                return formatTime(time);
            else
                return "本年度未离校";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
