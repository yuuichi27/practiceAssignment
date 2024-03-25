public class Student {
    String fullName;
    String birthday;
    Integer group;

    public Student(String fullName, String birthday, Integer group) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.group = group;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Студент(ка) " + fullName + ", " + birthday + " года рождения из " + group + " группы";
    }
}
