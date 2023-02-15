package Operation;

import DAO.*;
import Entity.Instructor;
import Entity.Student;
import Entity.Tcome;
import Entity.Tleave;
import Utils.DBUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ClassAdminOperation {

    private final Instructor instructor;
    private final InstructorImplement instructorDAO;
    private final StudentDAO studentDAO;

    public ClassAdminOperation(String id) {
        instructorDAO = new InstructorDAO();
        studentDAO = new StudentDAO();
        Connection conn = DBUtil.getConnection();
        instructor = instructorDAO.GetInstructor(conn, id);
        DBUtil.closeResource(conn);
    }

    public void show() {
        System.out.println("-------------------------------------------");
        System.out.println("                  欢迎登陆                   ");
        System.out.println("班级辅导员ID:" + instructor.getId() + "    姓名：" + instructor.getName());
        System.out.println("学院：" + instructor.getDept_name() + "    班级：" + instructor.getClassName());
        help();
    }

    public void help() {
        System.out.println("功能菜单如下：");
        System.out.println("1.查询本班学生信息");
        System.out.println("2.查询过去 n 天尚未批准的入校申请和出校申请数量及详细信息");
        System.out.println("3.查询前 n 个提交入校申请最多的学生");
        System.out.println("4.查询前 n 个平均离校时间最长的学生");
        System.out.println("5.查询已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间");
        System.out.println("6.查询未提交出校申请但离校状态超过 24h 的学生数量、个人信息");
        System.out.println("7.查询已提交出校申请但未离校的学生数量、个人信息");
        System.out.println("8.查询过去 n 天一直在校未曾出校的学生");
        System.out.println("9.查询连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息");
        System.out.println("10.帮助");
        System.out.println("11.退出");
    }

    public void fun1() {
        System.out.println("请输入查询学号：");
        Scanner scanner = new Scanner(System.in);
        String sID = scanner.nextLine();
        Connection conn = DBUtil.getConnection();
        Student student = studentDAO.GetStudent(conn, sID);
        if (student == null || !student.getDepartment().equals(instructor.getDept_name()) || !student.getClassName().equals(instructor.getClassName())) {
            System.out.println("学号无效！只支持查询自己所负责班级的学生数据。");
        } else {
            System.out.println("您可选择：");
            System.out.println("1.查看该学生详细信息");
            System.out.println("2.查询该学生入校权限");
            System.out.println("3.查看该学生最近n天的健康日报");
            System.out.println("4.查询该学生入校申请");
            System.out.println("5.查询该学生出校申请");
            System.out.println("6.查询该学生（从当天算起）过去一年的离校总时长");



        }

        DBUtil.closeResource(conn);
    }

//    查询过去 n 天尚未批准的入校申请和出校申请数量及详细信息
    public void fun2() {
        System.out.println("a.查询入校申请");
        System.out.println("b.查询出校申请");
        System.out.println("请选择：");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        System.out.println("请输入n值：");
        int n = scanner.nextInt();
        Connection conn = DBUtil.getConnection();

//        入校申请
        if (choice.equals("a")) {
            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            TcomeDAO tcomeDAO = new TcomeDAO();
            List<Tcome> list = tcomeDAO.getNTcome(conn, n, classes.get(index - 1), instructor.getDeptId());
            System.out.println("过去 " + n + " 天尚未批准的入校申请数量为：" + list.size());
            if (!classes.get(index - 1).equals(instructor.getClassName())) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "条");
                System.out.println(list.get(i).toString());

            }
            System.out.println("若批准入校申请，请输入序号。若退出，请输入exit。");
            scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                System.out.println("已退出。");
            } else if (Integer.parseInt(line) <= list.size() && Integer.parseInt(line) >= 1) {
                int t_id = list.get(Integer.parseInt(line) - 1).getT_id();
                if (tcomeDAO.getTcomeByID(conn, t_id).getState() == 1) {
                    tcomeDAO.changeState(conn, t_id + "", 2);
                    System.out.print("已批准ID为 ");
                    System.out.println(t_id + " 的入校申请。");
                } else {
                    System.out.println("操作失败，没有权限。");
                }
            } else {
                System.out.println("输入错误。");
            }


//        出校申请
        } else if (choice.equals("b")) {
            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            TleaveDAO tleaveDAO = new TleaveDAO();
            List<Tleave> list = tleaveDAO.getNTleave(conn, n, classes.get(index - 1), instructor.getDeptId() + "");
            System.out.println("过去 " + n + " 天尚未批准的出校申请数量为：" + list.size());
            if (!classes.get(index - 1).equals(instructor.getClassName())) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "条");
                System.out.println(list.get(i).toString());
            }
            System.out.println("若批准出校申请，请输入序号。若退出，请输入exit。");
            scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                System.out.println("已退出。");
            } else if (Integer.parseInt(line) <= list.size() && Integer.parseInt(line) >= 1) {
                int t_id = list.get(Integer.parseInt(line) - 1).getT_id();
                if (tleaveDAO.getTleaveByID(conn, t_id).getState() == 1) {
                    tleaveDAO.changeState(conn, t_id + "", 2);
                    System.out.print("已批准ID为 ");
                    System.out.println(t_id + " 的出校申请。");
                } else {
                    System.out.println("操作失败，没有权限。");
                }
            } else {
                System.out.println("输入错误。");
            }
        }

        DBUtil.closeResource(conn);
    }

//    查询前 n 个提交入校申请最多的学生
    public void fun3() {
        System.out.println("请输入n值：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TcomeDAO tcomeDAO = new TcomeDAO();
        Connection conn = DBUtil.getConnection();
        List<Student> list = tcomeDAO.getNMostStudent(conn, n, instructor.getClassName(), instructor.getDeptId());
        System.out.println("前 " + n + " 个提交入校申请最多的学生如下：");
        for (Student student : list) {
            System.out.println(student.getName() + " " + student.getS_id());
        }
        DBUtil.closeResource(conn);

    }
    public void fun4() {

    }

    public void fun5() {

    }


//      查询未提交出校申请但离校状态超过 24h 的学生数量、个人信息；
    public void fun6() {




    }
    public void fun7() {

    }
    public void fun8() {

    }
    public void fun9() {

    }

    public static void main(String[] args) {
        String ID = "01001";
        ClassAdminOperation classAdminOperation = new ClassAdminOperation(ID);
        classAdminOperation.show();
        boolean flag = true;
        while (flag) {
            System.out.println("请输入对应功能的序号：");
            Scanner scanner = new Scanner(System.in);
            int fun = scanner.nextInt();
            switch (fun) {
                case 1:
                    classAdminOperation.fun1();
                    break;
                case 2:
                    classAdminOperation.fun2();
                    break;
                case 3:
                    classAdminOperation.fun3();
                    break;
                case 4:
                    classAdminOperation.fun4();
                    break;
                case 5:
                    classAdminOperation.fun5();
                    break;
                case 6:
                    classAdminOperation.fun6();
                    break;
                case 7:
                    classAdminOperation.fun7();
                    break;
                case 8:
                    classAdminOperation.fun8();
                    break;
                case 9:
                    classAdminOperation.fun9();
                case 10:
                    classAdminOperation.help();
                    break;
                case 11:
                    flag = false;
                    break;
            }

        }


    }



































}
