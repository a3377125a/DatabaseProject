import DAO.*;
import Entity.Admin;
import Entity.Instructor;
import Entity.Student;
import Operation.SuperAdminOperation;
import Utils.DBUtil;

import java.sql.Connection;
import java.util.Scanner;

public class MainSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎使用A大学进出校管理系统，请输入您的ID:");
            String ID = scanner.nextLine();
            if (ID.equals("admin")) {
                SuperAdminOperation operation = new SuperAdminOperation();
                operation.start();
            } else {
                int n = ID.length();
                if (n == 11) {
                    //学生登录系统
                    StudentImplement stuImpl = new StudentDAO();
                    Connection conn = null;
                    try {
                        conn = DBUtil.getConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (stuImpl.IsStudent(conn, ID)) {
                        Student stu=stuImpl.GetStudent(conn,ID);
                        System.out.println(stu);
                    } else {
                        System.out.println("输入学号无效");
                    }
                } else if ( n == 5) {
                    // 辅导员登录系统
                    InstructorImplement insImpl=new InstructorDAO();
                    Connection conn = null;
                    try {
                        conn = DBUtil.getConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (insImpl.IsInstructor(conn,ID)) {
                        Instructor instructor=insImpl.GetInstructor(conn,ID);
                        System.out.println(instructor);
                    } else {
                        System.out.println("输入工号无效");
                    }
                } else if (n == 4) {
                    //院系管理员登录系统
                    AdminImplement adImpl=new AdminDAO();
                    Connection conn = null;
                    try {
                        conn = DBUtil.getConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (adImpl.IsAdmin(conn, ID)) {
                        Admin admin=adImpl.GetAdmin(conn,ID);
                        System.out.println(admin);
                    } else {
                        System.out.println("输入工号无效");
                    }
                }else{
                    System.out.println("输入格式错误！");
                }

            }
        }
    }
}
