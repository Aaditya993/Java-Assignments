

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private List<StudentMarks> students = new ArrayList<>();

    public void addStudent(StudentMarks s) { students.add(s); }
    public void updateStudent(int index, StudentMarks s) { students.set(index, s); }
    public void deleteStudent(int index) { students.remove(index); }
    
    public double calculateAverage() {
        if (students.isEmpty()) return 0.0;
        double sum = 0;
        for (StudentMarks s : students) {
            sum += s.getMarks();
        }
        return sum / students.size();
    }
}
