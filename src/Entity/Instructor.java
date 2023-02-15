package Entity;

public class Instructor {
    private String id, name, dept_name, className;

    private int deptId;

    public Instructor() {
    }

    public Instructor(String id, String name, String dept_name, String className, int deptId) {
        this.id = id;
        this.name = name;
        this.dept_name = dept_name;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }


    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", deptId='" + deptId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
