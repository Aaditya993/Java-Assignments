import java.util.Scanner;

public class StudentActivityManagementSystem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Loop to display the menu repeatedly until the user exits
        menuLoop:
        while (true) {
            System.out.println("\n===== Student Activity Management System =====");
            System.out.println("1. Check Attendance Eligibility");
            System.out.println("2. View Performance Category");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            // Input validation for the menu choice
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
                continue; // Skip invalid entry and re-display menu
            }
            
            int choice = scanner.nextInt();
            
            // Switch statement to route the menu selection
            switch (choice) {
                case 1:
                    System.out.print("Enter attendance percentage (0-100): ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid input format.");
                        scanner.next();
                        continue;
                    }
                    
                    double attendance = scanner.nextDouble();
                    
                    // Validate attendance range
                    if (attendance < 0 || attendance > 100) {
                        System.out.println("Invalid attendance value. Please try again.");
                        continue;
                    }
                    
                    // if-else for attendance eligibility
                    if (attendance >= 75) {
                        System.out.println("Student is eligible to appear for the examination.");
                    } else {
                        System.out.println("Student is not eligible to appear for the examination.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter marks (0-100): ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input format.");
                        scanner.next();
                        continue;
                    }
                    
                    int marks = scanner.nextInt();
                    
                    // Nested if used for range validation and categorization
                    if (marks >= 0 && marks <= 100) {
                        if (marks >= 90 && marks <= 100) {
                            System.out.println("Performance Category: Excellent");
                        } else if (marks >= 70 && marks <= 89) {
                            System.out.println("Performance Category: Good");
                        } else if (marks >= 60 && marks <= 69) {
                            System.out.println("Performance Category: Average");
                        } else {
                            System.out.println("Performance Category: Needs Improvement");
                        }
                    } else {
                        System.out.println("Invalid marks entered. Prompting to main menu.");
                        continue; 
                    }
                    break;
                    
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    // Terminate the program using break
                    break menuLoop;
                    
                default:
                    System.out.println("Invalid menu choice. Please select 1, 2, or 3.");
                    continue; // Skip invalid entry
            }
        }
        
        scanner.close();
    }
}