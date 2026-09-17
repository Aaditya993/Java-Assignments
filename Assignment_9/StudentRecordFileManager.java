

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StudentRecordFileManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String directoryPath = "StudentRecords";
        String filePath = directoryPath + "/student.txt";
        
        File directory = new File(directoryPath);
        File recordFile = new File(filePath);

        while (true) {
            System.out.println("\n===== Student Record File Manager =====");
            System.out.println("1. Create Records Directory");
            System.out.println("2. Create Record File");
            System.out.println("3. Write Student Record");
            System.out.println("4. Display File Information");
            System.out.println("5. Read File Content");
            System.out.println("6. Append New Record");
            System.out.println("7. Delete Record File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // Create Records Directory
                    if (!directory.exists()) {
                        if (directory.mkdir()) {
                            System.out.println("Directory created: " + directory.getName());
                        } else {
                            System.out.println("Failed to create directory.");
                        }
                    } else {
                        System.out.println("Directory already exists.");
                    }
                    break;
                    
                case 2:
                    // Create Record File
                    try {
                        if (!directory.exists()) {
                            System.out.println("Please create the directory first (Option 1).");
                        } else if (recordFile.exists()) {
                            System.out.println("File already exists: " + recordFile.getPath());
                        } else {
                            if (recordFile.createNewFile()) {
                                System.out.println("File created: " + recordFile.getPath());
                            } else {
                                System.out.println("Failed to create file.");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred while creating the file: " + e.getMessage());
                    }
                    break;
                    
                case 3:
                    // Write Student Record (Overwrites existing data)
                    try {
                        if (!recordFile.exists()) {
                            System.out.println("File does not exist. Please create it first.");
                            break;
                        }
                        System.out.println("Enter student record (e.g., Roll No 101, Rahul Sharma, B.Tech CE): ");
                        String record = scanner.nextLine();
                        
                        // Using FileOutputStream to write string converted to bytes
                        FileOutputStream fos = new FileOutputStream(recordFile);
                        fos.write(record.getBytes());
                        fos.close();
                        
                        System.out.println("\nRecord written successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                    break;
                    
                case 4:
                    // Display File Information
                    if (recordFile.exists()) {
                        System.out.println("\n===== File Information =====");
                        System.out.println("Name        : " + recordFile.getName());
                        System.out.println("Path        : " + recordFile.getPath());
                        System.out.println("Absolute    : " + recordFile.getAbsolutePath());
                        System.out.println("Size        : " + recordFile.length() + " bytes");
                        System.out.println("Is File     : " + recordFile.isFile());
                        System.out.println("Is Directory: " + recordFile.isDirectory());
                    } else {
                        System.out.println("File does not exist.");
                    }
                    break;
                    
                case 5:
                    // Read File Content
                    try {
                        if (!recordFile.exists()) {
                            System.out.println("File does not exist.");
                            break;
                        }
                        System.out.println("\n===== File Content =====");
                        // Using FileInputStream to read byte by byte
                        FileInputStream fis = new FileInputStream(recordFile);
                        int data;
                        while ((data = fis.read()) != -1) {
                            System.out.print((char) data);
                        }
                        fis.close();
                        System.out.println(); // Print a newline at the end
                    } catch (IOException e) {
                        System.out.println("An error occurred while reading the file: " + e.getMessage());
                    }
                    break;
                    
                case 6:
                    // Append New Record
                    try {
                        if (!recordFile.exists()) {
                            System.out.println("File does not exist. Please create it first.");
                            break;
                        }
                        System.out.println("Enter new student record to append: ");
                        String newRecord = "\n" + scanner.nextLine();
                        
                        // FileOutputStream in append mode (true as second argument)
                        FileOutputStream fosAppend = new FileOutputStream(recordFile, true);
                        fosAppend.write(newRecord.getBytes());
                        fosAppend.close();
                        
                        System.out.println("\nRecord appended successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while appending to the file: " + e.getMessage());
                    }
                    break;
                    
                case 7:
                    // Delete Record File
                    if (recordFile.exists()) {
                        if (recordFile.delete()) {
                            System.out.println("File deleted successfully.");
                        } else {
                            System.out.println("Failed to delete the file.");
                        }
                    } else {
                        System.out.println("File does not exist.");
                    }
                    break;
                    
                case 8:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }
}