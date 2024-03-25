public class Group {
    String number;
    String facultyName;

    public Group(String number, String facultyName) {
        this.number = number;
        this.facultyName = facultyName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "Группа с номером " + number + ", по направлению " + facultyName;
    }
}
