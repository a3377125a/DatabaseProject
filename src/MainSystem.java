import DAO.*;
import Entity.Admin;
import Entity.Instructor;
import Entity.Student;
import Operation.ClassAdminOperation;
import Operation.DeptAdminOperation;
import Operation.StudentOperation;
import Operation.SuperAdminOperation;
import Utils.DBUtil;

import java.sql.Connection;
import java.util.Scanner;

public class MainSystem {
    //确认是否是学生
    public static Boolean s_confirm(String id) {
        //学生登录系统
        StudentImplement stuImpl = new StudentDAO();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stuImpl.IsStudent(conn, id);

    }

    //确认是否是班级辅导员
    public static Boolean i_confirm(String id) {
        InstructorImplement insImpl = new InstructorDAO();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (insImpl.IsInstructor(conn, id));
    }

    //确认是否是院系管理员
    public static Boolean a_confirm(String id) {
        AdminImplement adImpl = new AdminDAO();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (adImpl.IsAdmin(conn, id));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎使用A大学进出校管理系统，请输入您的ID:");
            String ID = scanner.nextLine();
            if (ID.equals("exit")) {
                break;
            }
            if (ID.equals("admin")) {
                //最高权限
                SuperAdminOperation operation = new SuperAdminOperation();
                operation.run();
            } else {
                int n = ID.length();
                if (n == 11) {
                    //学生登录系统
                    if (s_confirm(ID)) {
                        System.out.println("登录成功！您当前的权限为：学生。");
                        StudentOperation studentOperation = new StudentOperation(ID);
                        studentOperation.run();
                    } else {
                        System.out.println("输入学号无效");
                    }
                } else if (n == 5) {
                    // 辅导员登录系统
                    if (i_confirm(ID)) {
                        System.out.println("登录成功！您当前的权限为：班级辅导员。");
                        ClassAdminOperation classAdminOperation = new ClassAdminOperation(ID);
                        classAdminOperation.run();
                    } else {
                        System.out.println("输入工号无效");
                    }

                } else if (n == 4) {
                    //院系管理员登录系统
                    if (a_confirm(ID)) {
                        System.out.println("登录成功！您当前的权限为：院系管理员。");
                        DeptAdminOperation deptAdminOperation = new DeptAdminOperation(ID);
                        deptAdminOperation.run();
                    } else {
                        System.out.println("输入工号无效");
                    }
                } else {
                    System.out.println("输入格式错误！");
                }
            }
        }
    }
}
