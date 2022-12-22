package Operation;

import java.util.Scanner;

public class SuperAdminOperation {


    private Scanner scanner;

    public void start() {
        scanner = new Scanner(System.in);
        System.out.println("成功登陆A大学进出校管理系统，您的身份是*超级用户*");
        System.out.println("您可以输入help命令以获得帮助，或者输入exit命令以退出");

        while (true) {
            System.out.println("请输入操作命令：");
            String cmd = scanner.nextLine();
            String[] args = cmd.split(" ");
            if (args[0].equals("search-PAFD")) {
                Operations.searchStudentPAFD(args);
            } else if (args[0].equals("")) {
//todo
            } else if (args[0].equals("exit")) {
                break;
            } else {
                System.out.println("操作命令格式错误");
                printUsage();
            }

        }
        System.out.println("已退出系统");
    }




    private void printUsage() {
        System.out.println("");
    }









}
