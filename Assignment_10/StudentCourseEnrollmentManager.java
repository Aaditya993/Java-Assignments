package Assignment_10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentCourseEnrollmentManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Set Up the Collections
        ArrayList<String> enrolledStudents = new ArrayList<>();
        LinkedList<String> waitingQueue = new LinkedList<>();

        // 2. Build the Menu
        while (true) {
            System.out.println("\n===== Student Course Enrollment Manager =====");
            System.out.println("1. Add Student to Enrollment List");
            System.out.println("2. Display Enrolled Students");
            System.out.println("3. Add Student to Waiting Queue");
            System.out.println("4. Promote Student from Queue");
            System.out.println("5. Search Student in Enrollment List");
            System.out.println("6. Remove Student from Enrollment List");
            System.out.println("7. Display Waiting Queue");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // 3 & 4. Implement Core Operations & Handle Exceptions
            switch (choice) {
                case 1:
                    System.out.print("Enter student name to enroll: ");
                    String enrollName = scanner.nextLine();
                    enrolledStudents.add(enrollName);
                    System.out.println("Student enrolled successfully.");
                    break;
                    
                case 2:
                    System.out.println("\n===== Enrolled Students =====");
                    if (enrolledStudents.isEmpty()) {
                        System.out.println("No students enrolled yet.");
                    } else {
                        int index = 1;
                        for (String student : enrolledStudents) {
                            System.out.println(index + ". " + student);
                            index++;
                        }
                    }
                    System.out.println("Total Enrolled: " + enrolledStudents.size());
                    break;
                    
                case 3:
                    System.out.print("Enter student name for waiting queue: ");
                    String waitName = scanner.nextLine();
                    waitingQueue.addLast(waitName);
                    System.out.println("Student added to waiting queue.");
                    break;
                    
                case 4:
                    System.out.println("Promoting first student from waiting queue...");
                    try {
                        // removeFirst() throws NoSuchElementException if the queue is empty
                        String promotedStudent = waitingQueue.removeFirst();
                        enrolledStudents.add(promotedStudent);
                        System.out.println(promotedStudent + " moved to Enrollment List.");
                    } catch (NoSuchElementException e) {
                        System.out.println("Error: The waiting queue is empty. No student to promote.");
                    }
                    break;
                    
                case 5:
                    System.out.print("Enter student name to search: ");
                    String searchName = scanner.nextLine();
                    if (enrolledStudents.contains(searchName)) {
                        int studentIndex = enrolledStudents.indexOf(searchName);
                        System.out.println("Student found at index: " + studentIndex);
                    } else {
                        System.out.println("Student not found in the Enrollment List.");
                    }
                    break;
                    
                case 6:
                    System.out.print("Enter student name to remove: ");
                    String removeName = scanner.nextLine();
                    if (enrolledStudents.remove(removeName)) {
                        System.out.println("Student removed successfully from Enrollment List.");
                    } else {
                        System.out.println("Error: Student not found in the Enrollment List.");
                    }
                    break;
                    
                case 7:
                    System.out.println("\n===== Waiting Queue =====");
                    if (waitingQueue.isEmpty()) {
                        System.out.println("Waiting queue is currently empty.");
                    } else {
                        int qIndex = 1;
                        for (String student : waitingQueue) {
                            System.out.println(qIndex + ". " + student);
                            qIndex++;
                        }
                    }
                    System.out.println("Total in Waiting Queue: " + waitingQueue.size());
                    break;
                    
                case 8:
                    System.out.println("Exiting the manager. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }
}