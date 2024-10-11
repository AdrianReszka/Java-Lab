package Model;

public class Grade {
    private double value;
    private String teacher;
    private String subject;

    public Grade(double value, String teacher, String subject) {
        this.value = value;
        this.teacher = teacher;
        this.subject = subject;
    }

    public double getValue() {
        return value;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String toString() {
        return "Grade: " + value + ", Teacher: " + teacher + ", Subject: " + subject;
    }
}
