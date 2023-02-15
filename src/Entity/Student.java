package Entity;

public class Student {
    private String s_id, name, className;
    private String email, adress, familyAdress, IdNumber, type, tellphone, department;

    public Student() {
    }

    public Student(String s_id, String name, String className,
                   String tellphone, String department, String email,
                   String adress, String familyAdress, String idNumber, String type) {
        this.s_id = s_id;
        this.name = name;
        this.className = className;
        this.tellphone = tellphone;
        this.department = department;
        this.email = email;
        this.adress = adress;
        this.familyAdress = familyAdress;
        IdNumber = idNumber;
        this.type = type;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFamilyAdress() {
        return familyAdress;
    }

    public void setFamilyAdress(String familyAdress) {
        this.familyAdress = familyAdress;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "学号：" + s_id + "\n" +
                        "姓名：" + name + '\n' +
                        "班级：" + className + '\n' +
                        "院系：" + department + '\n' +
                        "电话：" + tellphone + "\n" +
                        "邮箱：" + email + '\n' +
                        "住址：" + adress + "\n" +
                        "家庭住址：" + familyAdress + '\n' +
                        "身份证号码：" + IdNumber + '\n' +
                        "身份证类型：" + type + "\n"
                ;
    }
}
