import java.util.Scanner;

public class StudentMarksManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Store the Marks in a 2-D array
        int[][] marks = {
            {78, 82, 75}, // Student 1
            {90, 85, 88}, // Student 2
            {65, 72, 70}, // Student 3
            {88, 91, 85}, // Student 4
            {55, 60, 58}  // Student 5
        };
        
        int[] studentIds = {1, 2, 3, 4, 5};
        int[] totals = new int[5];
        
        menuLoop:
        while (true) {
            System.out.println("\n===== Student Marks Management System =====");
            System.out.println("1. Display All Marks");
            System.out.println("2. Calculate Student Total");
            System.out.println("3. Calculate Student Average");
            System.out.println("4. Find Highest Scorer & Subject Highest");
            System.out.println("5. Search Marks");
            System.out.println("6. Sort Student Totals");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    displayAllMarks(marks, studentIds);
                    break;
                case 2:
                    totals = calculateTotals(marks);
                    for (int i = 0; i < totals.length; i++) {
                        System.out.println("Student " + studentIds[i] + " Total   : " + totals[i]);
                    }
                    break;
                case 3:
                    totals = calculateTotals(marks);
                    double[] averages = calculateAverages(totals, marks[0].length);
                    for (int i = 0; i < averages.length; i++) {
                        System.out.printf("Student %d Average : %.2f\n", studentIds[i], averages[i]);
                    }
                    break;
                case 4:
                    totals = calculateTotals(marks);
                    displaySubjectHighest(marks);
                    findHighestScorer(totals, studentIds);
                    break;
                case 5:
                    System.out.print("Enter marks to search: ");
                    if (scanner.hasNextInt()) {
                        int target = scanner.nextInt();
                        searchMarks(marks, target);
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                    break;
                case 6:
                    totals = calculateTotals(marks);
                    // Create copies to sort so we don't mess up the original index alignment for other menu options
                    int[] totalsCopy = totals.clone();
                    int[] idsCopy = studentIds.clone();
                    bubbleSortTotals(totalsCopy, idsCopy);
                    System.out.println("Sorted Student Totals (Highest to Lowest):");
                    for (int i = 0; i < totalsCopy.length; i++) {
                        System.out.println("Student " + idsCopy[i] + " Total: " + totalsCopy[i]);
                    }
                    break;
                case 7:
                    System.out.println("Exiting System. Goodbye!");
                    break menuLoop;
                default:
                    System.out.println("Invalid choice. Please select from 1-7.");
            }
        }
        scanner.close();
    }
    
    // Method to calculate total marks
    public static int[] calculateTotals(int[][] marksArray) {
        int[] totals = new int[marksArray.length];
        for (int i = 0; i < marksArray.length; i++) {
            int sum = 0;
            for (int j = 0; j < marksArray[i].length; j++) {
                sum += marksArray[i][j];
            }
            totals[i] = sum;
        }
        return totals;
    }
    
    // Method to calculate average marks
    public static double[] calculateAverages(int[] totals, int subjectCount) {
        double[] averages = new double[totals.length];
        for (int i = 0; i < totals.length; i++) {
            averages[i] = (double) totals[i] / subjectCount;
        }
        return averages;
    }
    
    // Method to display all marks
    public static void displayAllMarks(int[][] marksArray, int[] ids) {
        System.out.println("Student\t\tJava\tPython\tDBMS");
        for (int i = 0; i < marksArray.length; i++) {
            System.out.print("Student " + ids[i] + "\t");
            for (int j = 0; j < marksArray[i].length; j++) {
                System.out.print(marksArray[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    // Method to display highest marks subject-wise
    public static void displaySubjectHighest(int[][] marksArray) {
        int highestJava = 0, highestPython = 0, highestDBMS = 0;
        for (int i = 0; i < marksArray.length; i++) {
            if (marksArray[i][0] > highestJava) highestJava = marksArray[i][0];
            if (marksArray[i][1] > highestPython) highestPython = marksArray[i][1];
            if (marksArray[i][2] > highestDBMS) highestDBMS = marksArray[i][2];
        }
        System.out.println("Highest Java Marks   : " + highestJava);
        System.out.println("Highest Python Marks : " + highestPython);
        System.out.println("Highest DBMS Marks   : " + highestDBMS);
        System.out.println();
    }
    
    // Method to find highest scorer overall
    public static void findHighestScorer(int[] totals, int[] ids) {
        int maxIndex = 0;
        for (int i = 1; i < totals.length; i++) {
            if (totals[i] > totals[maxIndex]) {
                maxIndex = i;
            }
        }
        System.out.println("Highest Scorer: Student " + ids[maxIndex] + " with Total: " + totals[maxIndex]);
    }
    
    // Method for Linear Search
    public static void searchMarks(int[][] marksArray, int target) {
        boolean found = false;
        String[] subjects = {"Java", "Python", "DBMS"};
        for (int i = 0; i < marksArray.length; i++) {
            for (int j = 0; j < marksArray[i].length; j++) {
                if (marksArray[i][j] == target) {
                    System.out.println("Mark " + target + " found for Student " + (i + 1) + " in " + subjects[j]);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Mark " + target + " not found.");
        }
    }
    
    // Method for Bubble Sort
    public static void bubbleSortTotals(int[] totalsCopy, int[] idsCopy) {
        int n = totalsCopy.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (totalsCopy[j] < totalsCopy[j + 1]) { // Sort descending
                    int tempTotal = totalsCopy[j];
                    totalsCopy[j] = totalsCopy[j + 1];
                    totalsCopy[j + 1] = tempTotal;
                    
                    int tempId = idsCopy[j];
                    idsCopy[j] = idsCopy[j + 1];
                    idsCopy[j + 1] = tempId;
                }
            }
        }
    }
}