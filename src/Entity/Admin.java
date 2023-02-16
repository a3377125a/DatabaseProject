package Entity;

public class Admin {
    private String id;
    private String name;
    private String dept_name;
    private int deptId;

    public Admin() {
    }

    public Admin(String id, String name, String dept_name, int deptId) {
        this.id = id;
        this.name = name;
        this.dept_name = dept_name;
        this.deptId = deptId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}
