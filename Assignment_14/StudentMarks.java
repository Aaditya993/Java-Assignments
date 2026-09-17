

public class StudentMarks {
    private String rollNo;
    private String name;
    private double marks;

    public StudentMarks(String rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public double getMarks() { return marks; }
}