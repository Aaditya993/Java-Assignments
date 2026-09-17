public class StudentProfileCalculator1 {
    public static void main(String[] args) {
        // 1. Declare the Student Profile Data
        String name = "Aaditya Bhosale";
        int rollNo = 101;
        String course = "B.Tech CSE";
        String year = "Second Year"; 
        
        // 2. Implement the Calculator Logic
        int firstNumber = 25;
        int secondNumber = 10;
        
        int addition = firstNumber + secondNumber;
        int subtraction = firstNumber - secondNumber;
        int multiplication = firstNumber * secondNumber;
        // Cast to double to preserve decimal precision for division
        double division = (double) firstNumber / secondNumber; 
        
        // 3. Display the Output (Student Profile)
        System.out.println("--sTudent-ProfIle");
        System.out.println();
        System.out.println("Name       : " + name);
        System.out.println("Roll No    : " + rollNo);
        System.out.println("Course     : " + course);
        System.out.println("Year       : " + year);
        System.out.println();
        
        // 4. Display the Output (Calculator)
        System.out.println("Calculator");
        System.out.println();
        System.out.println("First Number  : " + firstNumber);
        System.out.println("Second Number : " + secondNumber);
        System.out.println();
        System.out.println("Addition       : " + addition);
        System.out.println("Subtraction    : " + subtraction);
        System.out.println("Multiplication : " + multiplication);
        System.out.println("Division       : " + division);
    }
}