package Operation;

import DAO.*;
import Entity.*;
import Utils.DBUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SuperAdminOperation {

    public void show() {
        System.out.println("-------------------------------------------");
        System.out.println("                  欢迎登陆                   ");
        System.out.println("您是超级用户，可以执行跨校区、跨院系以及所有级别的数据查询分析。");
        help();
    }

    public void help() {
        System.out.println("功能菜单如下：");
        System.out.println("1.查询学生信息");
        System.out.println("2.查询过去 n 天尚未批准的入校申请和出校申请数量及详细信息");
        System.out.println("3.查询前 n 个提交入校申请最多的学生");
        System.out.println("4.查询前 n 个平均离校时间最长的学生");
        System.out.println("5.查询已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间");
        System.out.println("6.查询未提交出校申请但离校状态超过 24h 的学生数量、个人信息");
        System.out.println("7.查询已提交出校申请但未离校的学生数量、个人信息");
        System.out.println("8.查询过去 n 天一直在校未曾出校的学生");
        System.out.println("9.查询连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息");
        System.out.println("10.过去 n 天每个院系学生产生最多出入校记录的校区");
        System.out.println("11.帮助");
        System.out.println("12.退出");
    }

    public void run() {
        this.show();
        boolean flag = true;
        while (flag) {
            System.out.println("请输入对应功能的序号：");
            Scanner scanner = new Scanner(System.in);
            int fun = scanner.nextInt();
            switch (fun) {
                case 1 -> this.fun1();
                case 2 -> this.fun2();
                case 3 -> this.fun3();
                case 4 -> this.fun4();
                case 5 -> this.fun5();
                case 6 -> this.fun6();
                case 7 -> this.fun7();
                case 8 -> this.fun8();
                case 9 -> this.fun9();
                case 10 -> this.fun10();
                case 11 -> this.help();
                case 12 -> flag = false;
            }

        }
    }

    //    1.查询学生信息
    public void fun1() {
        System.out.println("请输入查询学号：");
        Scanner scanner = new Scanner(System.in);
        String sID = scanner.nextLine();
        Connection conn = DBUtil.getConnection();
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.GetStudent(conn, sID);
        if (student == null  ) {
            System.out.println("学号无效！");
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
            TcomeDAO tcomeDAO = new TcomeDAO();
            List<Tcome> list = tcomeDAO.getNTcome(conn, n);
            conn = DBUtil.restart(conn);
            System.out.println("过去 " + n + " 天尚未批准的入校申请数量为：" + list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "条");
                System.out.println(list.get(i).toString());
            }

//        出校申请
        } else if (choice.equals("b")) {
            TleaveDAO tleaveDAO = new TleaveDAO();
            List<Tleave> list = tleaveDAO.getNTleave(conn, n);
            conn = DBUtil.restart(conn);
            System.out.println("过去 " + n + " 天尚未批准的出校申请数量为：" + list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "条");
                System.out.println(list.get(i).toString());
            }
        }

        DBUtil.closeResource(conn);
    }
    //    3.查询前 n 个提交入校申请最多的学生
    public void fun3() {

        System.out.println("1：全校范围");
        System.out.println("2：院系范围");
        System.out.println("3：班级范围");
        System.out.println("请选择查询范围：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        System.out.println("请输入n值：");
        scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Student> list;
        TcomeDAO tcomeDAO = new TcomeDAO();
        Connection conn = DBUtil.getConnection();
        if (index == 1) {
            list = tcomeDAO.getNMostStudent(conn, n);
        } else if (index == 2) {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();
            list = tcomeDAO.getNMostStudent(conn, n, deptInd);
        } else {
            if (index != 3) {
                return;
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();

            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, deptInd + "");
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int classInd = scanner.nextInt();
            list = tcomeDAO.getNMostStudent(conn, n, classes.get(classInd - 1), deptInd);
        }
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

        System.out.println("1：全校范围");
        System.out.println("2：院系范围");
        System.out.println("3：班级范围");
        System.out.println("请选择查询范围：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        List<Object[]> list;
        LogDAO logDAO = new LogDAO();
        Connection conn = DBUtil.getConnection();
        if (index == 1) {
            list = logDAO.getLeaveStudents(conn);
        } else if (index == 2) {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();
            list = logDAO.getLeaveStudents1(conn, deptList.get(deptInd - 1)[2].toString());
        } else {
            if (index != 3) {
                return;
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();

            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, deptInd + "");
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int classInd = scanner.nextInt();
            list = logDAO.getLeaveStudents2(conn, classes.get(classInd - 1), deptInd);
        }
        System.out.println("离校状态学生共有 " + list.size() + " 人。");
        for (Object[] ele : list) {
            System.out.println(ele[0] + " " + ele[1] + "    " + ele[2]);
        }
        DBUtil.closeResource(conn);


    }

    //    6.查询未提交出校申请但离校状态超过 24h 的学生数量、个人信息
    public void fun6() {
        System.out.println("1：全校范围");
        System.out.println("2：院系范围");
        System.out.println("3：班级范围");
        System.out.println("请选择查询范围：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        List<Student> list;
        TleaveDAO tleaveDAO = new TleaveDAO();
        Connection conn = DBUtil.getConnection();
        if (index == 1) {
            list = tleaveDAO.getOver24H(conn);
        } else if (index == 2) {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();
            list = tleaveDAO.getOver24H(conn, deptInd + "");
        } else {
            if (index != 3) {
                return;
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();

            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, deptInd + "");
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int classInd = scanner.nextInt();
            list = tleaveDAO.getOver24H(conn, classes.get(classInd - 1), deptInd + "");
        }
        System.out.println("未提交出校申请但离校状态超过 24h 的学生共 " + list.size() + " 人：");
        for (Student student : list) {
            System.out.println(student.getS_id() + " " + student.getName());
        }
        DBUtil.closeResource(conn);
    }

    //    7.查询已提交出校申请但未离校的学生数量、个人信息
    public void fun7() {
        System.out.println("1：全校范围");
        System.out.println("2：院系范围");
        System.out.println("3：班级范围");
        System.out.println("请选择查询范围：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        List<Student> list;
        TleaveDAO tleaveDAO = new TleaveDAO();
        Connection conn = DBUtil.getConnection();
        if (index == 1) {
            list = tleaveDAO.getStays(conn);
        } else if (index == 2) {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();
            list = tleaveDAO.getStays(conn, deptInd + "");
        } else {
            if (index != 3) {
                return;
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();

            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, deptInd + "");
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int classInd = scanner.nextInt();
            list = tleaveDAO.getStays(conn, classes.get(classInd - 1), deptInd + "");
        }
        System.out.println("已提交出校申请但未离校的学生共 " + list.size() + " 人：");
        for (Student student : list) {
            System.out.println(student.getS_id() + " " + student.getName());
        }
        DBUtil.closeResource(conn);
    }

    //    8.查询过去 n 天一直在校未曾出校的学生
    public void fun8() {
        System.out.println("1：全校范围");
        System.out.println("2：院系范围");
        System.out.println("3：班级范围");
        System.out.println("请选择查询范围：");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        System.out.println("请输入n值：");
        scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Student> list;
        LogDAO logDAO = new LogDAO();
        Connection conn = DBUtil.getConnection();
        if (index == 1) {
            list = logDAO.UnLeaveStudent(conn, n);
        } else if (index == 2) {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();
            list = logDAO.UnLeaveStudent1(conn, n, deptInd);
        } else {
            if (index != 3) {
                return;
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Object[]> deptList = departmentDAO.getDept(conn);
            for (int i = 0; i < deptList.size(); i++) {
                System.out.println((i + 1) + ":" + deptList.get(i)[1]);
            }
            System.out.println("请选择要查询的院系序号：");
            scanner = new Scanner(System.in);
            int deptInd = scanner.nextInt();

            ClassDAO classDAO = new ClassDAO();
            List<String> classes = classDAO.getClassByDeptId(conn, deptInd + "");
            conn = DBUtil.restart(conn);
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ":" + classes.get(i));
            }
            System.out.println("请选择要查询的班级序号：");
            scanner = new Scanner(System.in);
            int classInd = scanner.nextInt();
            list = logDAO.UnLeaveStudent2(conn, n, classes.get(classInd - 1), deptInd);
        }
        System.out.println("过去 " + n + " 天一直在校未曾出校的学生共有 " + list.size() + " 人");
        for (Student student : list) {
            System.out.println(student.getS_id() + " " + student.getName());
        }
        DBUtil.closeResource(conn);

    }

    //    9.查询连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息
    public void fun9() {
//todo
    }

    //    10.查询过去 n 天每个院系学生产生最多出入校记录的校区。
    public void fun10() {
        LogDAO logDAO = new LogDAO();
        Connection conn = DBUtil.getConnection();
        System.out.println("请输入n值：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, String> map = logDAO.getCampus(conn, n);
        map.forEach((dept, campus) -> {
            System.out.println(dept + ": " + campus);
        });
    }
}
