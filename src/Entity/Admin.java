package Entity;

public class Admin {
    private String id,name,dept_name;

    public Admin() {
    }

    public Admin(String id, String name, String dept_name) {
        this.id = id;
        this.name = name;
        this.dept_name = dept_name;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
