package Operation;

import DAO.*;
import Entity.*;
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

//    1.查询本班学生信息
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
            System.out.println("4.退出");
            System.out.println("请输入指令序号：");
            scanner = new Scanner(System.in);
            int fun = scanner.nextInt();
            switch (fun) {
                case 1:
                    System.out.println("个人详细信息如下：");
                    System.out.println(student);
                    break;
                case 2:
                    conn = DBUtil.getConnection();
                    AcessDAOImplement acessDAOImplement = new AccessDAO();
                    List<String> cnames = acessDAOImplement.accesses(conn, student.getS_id());
                    System.out.println("进校权限：");
                    for (String s : cnames) {
                        System.out.print(s + "   ");
                    }
                    System.out.println();
                    break;
                case 3:
                    conn = DBUtil.getConnection();
                    PAFDDAOImplement pafddaoImplement = new PAFDDAO();
                    scanner = new Scanner(System.in);
                    System.out.println("请输入n值：");
                    int n = scanner.nextInt();
                    System.out.println("查看最近" + n + "天的健康日报：");
                    List<PAFD> pafDs = pafddaoImplement.getPAFDs(conn, student.getS_id(), n);
                    for (PAFD p : pafDs) {
                        System.out.println(p);
                    }
                    DBUtil.closeResource(conn);
                    break;
                case 4:
                    break;
            }

        }

        DBUtil.closeResource(conn);
    }



//    2.查询过去 n 天尚未批准的入校申请和出校申请数量及详细信息
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
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int index = scanner.nextInt();
            TcomeDAO tcomeDAO = new TcomeDAO();
            List<Tcome> list = tcomeDAO.getNTcome(conn, n, classes.get(index - 1), instructor.getDeptId());
            conn = DBUtil.restart(conn);
            System.out.println("过去 " + n + " 天尚未批准的入校申请数量为：" + list.size());
            if (!classes.get(index - 1).equals(instructor.getClassName())) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "条");
                System.out.println(list.get(i).toString());
            }
            System.out.println("若批准/拒绝入校申请，请输入序号。若退出，请输入exit。");
            scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                System.out.println("已退出。");
            } else if (Integer.parseInt(line) <= list.size() && Integer.parseInt(line) >= 1) {
                int t_id = list.get(Integer.parseInt(line) - 1).getT_id();
                System.out.println("Y：批准。   N：拒绝");
                scanner = new Scanner(System.in);
                line = scanner.nextLine();

                if (line.equals("Y")) {
                    if (tcomeDAO.getTcomeByID(conn, t_id).getState() == 1) {
                        tcomeDAO.changeState(conn, t_id + "", 2);
                        conn = DBUtil.restart(conn);
                        System.out.print("已批准ID为 ");
                        System.out.println(t_id + " 的入校申请。");
                    } else {
                        System.out.println("操作失败，没有权限。");
                    }
                } else if (line.equals("N")) {
                    if (tcomeDAO.getTcomeByID(conn, t_id).getState() == 1) {
                        System.out.println("请输入拒绝理由：");
                        scanner = new Scanner(System.in);
                        line = scanner.nextLine();
                        tcomeDAO.reject(conn, t_id + "", line);
                        conn = DBUtil.restart(conn);
                        System.out.print("已拒绝ID为 ");
                        System.out.println(t_id + " 的入校申请。");
                    } else {
                        System.out.println("操作失败，没有权限。");
                    }
                }


            } else {
                System.out.println("输入错误。");
            }


//        出校申请
        } else if (choice.equals("b")) {
            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int index = scanner.nextInt();

            TleaveDAO tleaveDAO = new TleaveDAO();
            List<Tleave> list = tleaveDAO.getNTleave(conn, n, classes.get(index - 1), instructor.getDeptId() + "");
            conn = DBUtil.restart(conn);
            System.out.println("过去 " + n + " 天尚未批准的出校申请数量为：" + list.size());
            if (!classes.get(index - 1).equals(instructor.getClassName())) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "条");
                System.out.println(list.get(i).toString());
            }
            System.out.println("若批准/拒绝入校申请，请输入序号。若退出，请输入exit。");
            scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                System.out.println("已退出。");
            } else if (Integer.parseInt(line) <= list.size() && Integer.parseInt(line) >= 1) {
                int t_id = list.get(Integer.parseInt(line) - 1).getT_id();
                System.out.println("Y：批准。   N：拒绝");
                scanner = new Scanner(System.in);
                line = scanner.nextLine();

                if (line.equals("Y")) {
                    if (tleaveDAO.getTleaveByID(conn, t_id).getState() == 1) {
                        tleaveDAO.changeState(conn, t_id + "", 2);
                        conn = DBUtil.restart(conn);
                        System.out.print("已批准ID为 ");
                        System.out.println(t_id + " 的出校申请。");
                    } else {
                        System.out.println("操作失败，没有权限。");
                    }
                } else if (line.equals("N")) {
                    if (tleaveDAO.getTleaveByID(conn, t_id).getState() == 1) {
                        System.out.println("请输入拒绝理由：");
                        scanner = new Scanner(System.in);
                        line = scanner.nextLine();
                        tleaveDAO.reject(conn, t_id + "", line);
                        conn = DBUtil.restart(conn);
                        System.out.print("已拒绝ID为 ");
                        System.out.println(t_id + " 的出校申请。");
                    } else {
                        System.out.println("操作失败，没有权限。");
                    }
                }


            } else {
                System.out.println("输入错误。");
            }
        }

        DBUtil.closeResource(conn);
    }


//    3.查询前 n 个提交入校申请最多的学生
    public void fun3() {
        System.out.println("请输入n值：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TcomeDAO tcomeDAO = new TcomeDAO();
        Connection conn = DBUtil.getConnection();
        List<Student> list = tcomeDAO.getNMostStudent(conn, n, instructor.getClassName(), instructor.getDeptId());
        System.out.println("前 " + n + " 个提交入校申请最多的学生如下：");
        for (Student student : list) {
            System.out.println(student.getS_id() + " " + student.getName());
        }
        DBUtil.closeResource(conn);

    }

//    4.查询前 n 个平均离校时间最长的学生
    public void fun4() {
//todo
    }


//    5.查询已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间
    public void fun5() {
        Connection conn = DBUtil.getConnection();
        ClassDAO classDAO = new ClassDAO();
        List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
        conn = DBUtil.restart(conn);
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ":" + classes.get(i));
        }
        System.out.println("请选择要查询的班级序号：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        LogDAO logDAO = new LogDAO();
        List<Object[]> list = logDAO.getLeaveStudents2(conn, classes.get(index - 1), instructor.getDeptId());
        System.out.println("离校状态学生共有 "+list.size()+" 人。");
        if (classes.get(index - 1).equals(instructor.getClassName())) {
            for (Object[] ele : list) {
                System.out.println(ele[0] + " " + ele[1] + "    " + ele[2]);
            }
        }
    }


//    6.查询未提交出校申请但离校状态超过 24h 的学生数量、个人信息
    public void fun6() {
        Connection conn = DBUtil.getConnection();
        ClassDAO classDAO = new ClassDAO();
        List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
        conn = DBUtil.restart(conn);
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ":" + classes.get(i));
        }
        System.out.println("请选择要查询的班级序号：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();


        TleaveDAO tleaveDAO = new TleaveDAO();
        List<Student> list = tleaveDAO.getOver24H(conn, classes.get(index - 1), instructor.getDeptId() + "");
        System.out.println("未提交出校申请但离校状态超过 24h 的学生共 " + list.size() + " 人：");

        if (classes.get(index - 1).equals(instructor.getClassName())) {
            for (Student student : list) {
                System.out.println(student.getS_id() + " " + student.getName());
            }
        }
        DBUtil.closeResource(conn);

    }

//    7.查询已提交出校申请但未离校的学生数量、个人信息
    public void fun7() {
        Connection conn = DBUtil.getConnection();
        ClassDAO classDAO = new ClassDAO();
        List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
        conn = DBUtil.restart(conn);
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ":" + classes.get(i));
        }
        System.out.println("请选择要查询的班级序号：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        TleaveDAO tleaveDAO = new TleaveDAO();
        List<Student> list = tleaveDAO.getStays(conn, classes.get(index - 1), instructor.getDeptId() + "");
        System.out.println("已提交出校申请但未离校的学生共 " + list.size() + " 人：");
        if (classes.get(index - 1).equals(instructor.getClassName())) {
            for (Student student : list) {
                System.out.println(student.getS_id() + " " + student.getName());
            }
        }
        DBUtil.closeResource(conn);
    }



//    8.查询过去 n 天一直在校未曾出校的学生
    public void fun8() {
        System.out.println("请输入n值：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Connection conn = DBUtil.getConnection();
        ClassDAO classDAO = new ClassDAO();
        List<String> classes = classDAO.getClassByDeptId(conn, instructor.getDeptId() + "");
        conn = DBUtil.restart(conn);
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ":" + classes.get(i));
        }
        System.out.println("请选择要查询的班级序号：");
        scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        LogDAO logDAO = new LogDAO();
        List<Student> list = logDAO.UnLeaveStudent2(conn, n, classes.get(index - 1), instructor.getDeptId());
        System.out.println("过去 " + n + " 天一直在校未曾出校的学生共有 " + list.size() + " 人");
        if (classes.get(index - 1).equals(instructor.getClassName())) {
            for (Student student : list) {
                System.out.println(student.getS_id() + " " + student.getName());
            }
        }

    }

//    9.查询连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息
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
                case 1 -> classAdminOperation.fun1();
                case 2 -> classAdminOperation.fun2();
                case 3 -> classAdminOperation.fun3();
                case 4 -> classAdminOperation.fun4();
                case 5 -> classAdminOperation.fun5();
                case 6 -> classAdminOperation.fun6();
                case 7 -> classAdminOperation.fun7();
                case 8 -> classAdminOperation.fun8();
                case 9 -> classAdminOperation.fun9();
                case 10 -> classAdminOperation.help();
                case 11 -> flag = false;
            }

        }


    }



































}
