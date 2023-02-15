package Operation;

import DAO.*;
import Entity.Student;
import Entity.Tcome;
import Entity.Tleave;
import Utils.DBUtil;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentOperation {
    //学生
    private Student student;

    public StudentOperation(String id) {
        StudentImplement stuImpl = new StudentDAO();
        Connection conn = DBUtil.getConnection();
        student = stuImpl.GetStudent(conn, id);
        DBUtil.closeResource(conn);
    }

    //展示
    public void show() {
        System.out.println("-------------------------------------------");
        System.out.println("                  欢迎登陆                   ");
        System.out.println("学生ID:" + student.getS_id() + "    学生姓名：" + student.getName());
        System.out.println("             " + student.getDepartment() + "  " + student.getClassName());
        help();
    }

    //展示功能菜单
    public void help() {
        System.out.println("功能菜单如下：");
        System.out.println("1.查看个人详细信息");
        System.out.println("2.填报当日健康日报");
        System.out.println("3.填写入校申请");
        System.out.println("4.填写离校申请");
        System.out.println("5.查询入校权限");
        System.out.println("6.查看最近n天的健康日报");
        System.out.println("7.查询入校申请");
        System.out.println("8.查询出校申请");
        System.out.println("9.查询（从当天算起）过去一年的离校总时长");
        System.out.println("10.帮助");
        System.out.println("11.退出");

    }

    public void fun1() {
        //展示学生详细信息
        System.out.println("1.查看个人详细信息");
        System.out.println(student);

    }

    public void fun2() {
        System.out.println("2.填报当日健康日报");
        Connection conn = DBUtil.getConnection();
        PAFDDAOImplement pafddaoImplement = new PAFDDAO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newTime = null;
        try {
            newTime = format.parse(new Date().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (pafddaoImplement.isAdd(conn, student.getS_id(), newTime)) {
            System.out.println("您今日已填写，无需再填");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入当前地址:");
            String address = scanner.nextLine();
            System.out.println("请输入今日体温：");
            float temper = scanner.nextFloat();
            pafddaoImplement.addPAFD(conn, student.getS_id(), address, new Date(), temper);
            DBUtil.closeResource(conn);
            System.out.println("填写成功！");
        }
    }

    public void fun3() {
        System.out.println("3.填写入校申请");
        Connection conn = DBUtil.getConnection();
        TcomeDAOImplement tcomeDAOImplement = new TcomeDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入入校原因：");
        String reason = scanner.nextLine();
        System.out.println("请输入七天内所到地区：");
        String addresses = scanner.nextLine();
        System.out.println("请输入返校时间：(格式为：yyyy-MM-dd)");
        String time = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newtime = null;
        try {
            newtime = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tcomeDAOImplement.addTcome(conn, student.getS_id(), reason, addresses, newtime);
        DBUtil.closeResource(conn);
        System.out.println("填写成功！");
    }

    public void fun4() {
        System.out.println("4.填写离校申请");
        Connection conn = DBUtil.getConnection();
        TleaveDAOImplement tleaveDAOImplement = new TleaveDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入离校原因：");
        String reason = scanner.nextLine();
        System.out.println("请输入目的地：");
        String addresses = scanner.nextLine();
        System.out.println("请输入离校时间：(格式为：yyyy-MM-dd)");
        String time = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newtime1 = null;
        try {
            newtime1 = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入返校时间：(格式为：yyyy-MM-dd)");
        time = scanner.nextLine();
        Date newtime2 = null;
        try {
            newtime2 = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tleaveDAOImplement.addTleave(conn, student.getS_id(), reason, addresses, newtime1, newtime2);
        DBUtil.closeResource(conn);
        System.out.println("填写成功！");
    }

    public void fun5() {
        System.out.println("5.查询入校权限");
        System.out.println("进校权限：");
    }

    public void fun6() {
        System.out.println("6.查看最近n天的健康日报");
        Connection conn = DBUtil.getConnection();
        PAFDDAOImplement pafddaoImplement = new PAFDDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入n值：");
        int n = scanner.nextInt();
        pafddaoImplement.getPAFDs(conn, student.getS_id(), n);
        DBUtil.closeResource(conn);
    }

    public void fun7() {
        System.out.println("7.查询入校申请");
        Connection conn = DBUtil.getConnection();
        TcomeDAOImplement tcomeDAOImplement = new TcomeDAO();
        System.out.println("----------------");
        System.out.println("1.待辅导员审批申请申请");
        System.out.println("2.待院系管理员审批申请");
        System.out.println("3.已完成申请");
        System.out.println("4.已拒绝申请");
        System.out.println("请输入具体类别对应序号：");
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<Tcome> tcomes = null;
        switch (n) {
            case 1:
                tcomes = tcomeDAOImplement.getTcome(conn, student.getS_id(), 0);
                break;
            case 2:
                tcomes = tcomeDAOImplement.getTcome(conn, student.getS_id(), 1);
                break;
            case 3:
                tcomes = tcomeDAOImplement.getTcome(conn, student.getS_id(), 2);
                break;
            case 4:
                tcomes = tcomeDAOImplement.getTcome(conn, student.getS_id(), 3);
                break;
        }
        for (Tcome t: tcomes) {
            System.out.println(tcomes);
        }
        DBUtil.closeResource(conn);
    }

    public void fun8() {
        System.out.println("8.查询出校申请");
        Connection conn = DBUtil.getConnection();
        TleaveDAOImplement tleaveDAOImplement=new TleaveDAO();
        System.out.println("--------------------");
        System.out.println("1.待辅导员审批申请申请");
        System.out.println("2.待院系管理员审批申请");
        System.out.println("3.已完成申请");
        System.out.println("4.已拒绝申请");
        System.out.println("请输入具体类别对应序号：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Tleave> tleaves= null;
        switch (n) {
            case 1:
                tleaves =tleaveDAOImplement.getTleave(conn,student.getS_id(),0);
                break;
            case 2:
                tleaves =tleaveDAOImplement.getTleave(conn,student.getS_id(),1);
                break;
            case 3:
                tleaves =tleaveDAOImplement.getTleave(conn,student.getS_id(),2);
                break;
            case 4:
                tleaves =tleaveDAOImplement.getTleave(conn,student.getS_id(),3);
                break;
        }
        for (Tleave t: tleaves) {
            System.out.println(tleaves);
        }
        DBUtil.closeResource(conn);
    }

    public void fun9() {
        System.out.println("9.查询（从当天算起）过去一年的离校总时长");
    }

    public static void main(String[] args) {
        String ID = "20301234568";
        StudentOperation studentOperation = new StudentOperation(ID);
        studentOperation.show();
        Boolean flag = true;
        while (flag) {
            System.out.println("请输入对应功能的序号：");
            Scanner scanner = new Scanner(System.in);
            int fun = scanner.nextInt();
            switch (fun) {
                case 1:
                    studentOperation.fun1();
                    break;
                case 2:
                    studentOperation.fun2();
                    break;
                case 3:
                    studentOperation.fun3();
                    break;
                case 4:
                    studentOperation.fun4();
                    break;
                case 5:
                    studentOperation.fun5();
                    break;
                case 6:
                    studentOperation.fun6();
                    break;
                case 7:
                    studentOperation.fun7();
                    break;
                case 8:
                    studentOperation.fun8();
                    break;
                case 9:
                    studentOperation.fun9();
                case 10:
                    studentOperation.help();
                    break;
                case 11:
                    flag = false;
                    break;
            }

        }


    }
}
