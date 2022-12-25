import Operation.SuperAdminOperation;

import java.util.Scanner;

public class MainSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎使用A大学进出校管理系统，请输入您的ID:");
            String line = scanner.nextLine();
            if (line.equals("admin")) {
                SuperAdminOperation operation = new SuperAdminOperation();
                operation.start();
            } else {
                int id;
                try {
                    id = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("ID格式错误");
                    continue;
                }
               /* Employee employee = EmployeeDAO.getEmployeeById(id);
                if (employee == null) {
                    System.out.println("ID不存在");
                } else {
                    if (employee.role == 1) {
                        EmployeeOperation operation = new EmployeeOperation(employee);
                        operation.start();
                    } else if (employee.role == 2) {
                        ManagerOperation operation = new ManagerOperation(employee);
                        operation.start();
                    } else if (employee.role == 3) {
                        InstructorOperation operation = new InstructorOperation(employee);
                        operation.start();
                    }
                }*/
            }
        }
    }
}
