package View;

import Model.Student;
import java.util.ArrayList;

public class StudentListView {
    public void printStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Surname: " + student.getSurname());
            student.printGrades();
        }
    }
}
