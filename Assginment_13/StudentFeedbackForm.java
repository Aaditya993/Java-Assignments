

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentFeedbackForm extends JFrame {
    
    // UI Components
    private JTextField nameField, courseField, ratingField, commentsField;
    private JTextArea feedbackList;
    private JButton submitButton;

    public StudentFeedbackForm() {
        // 1. Set Up the Main Window (BorderLayout)
        setTitle("Student Feedback Form");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close via WindowListener
        setLayout(new BorderLayout(10, 10)); // 10px padding

        // 2. Build the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save Feedback");
        JMenuItem clearItem = new JMenuItem("Clear Form");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(saveItem);
        fileMenu.add(clearItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Form Panel (GridLayout for aligned rows)
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Submit Your Feedback"));
        
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        formPanel.add(courseField);

        formPanel.add(new JLabel("Rating (Numeric):"));
        ratingField = new JTextField();
        formPanel.add(ratingField);

        formPanel.add(new JLabel("Comments:"));
        commentsField = new JTextField();
        formPanel.add(commentsField);

        add(formPanel, BorderLayout.NORTH);

        // Feedback List (Center)
        feedbackList = new JTextArea();
        feedbackList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedbackList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Submitted Feedback"));
        add(scrollPane, BorderLayout.CENTER);

        // Submit Button (South)
        submitButton = new JButton("Submit Feedback");
        add(submitButton, BorderLayout.SOUTH);

        // 3 & 4. Implement Core GUI Operations & Handle Exceptions
        
        // Shared logic for button click and 'Save Feedback' menu item
        ActionListener submitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processFeedback();
            }
        };
        submitButton.addActionListener(submitAction);
        saveItem.addActionListener(submitAction);

        // Clear Form logic
        clearItem.addActionListener(e -> clearForm());

        // Exit logic with confirmation
        exitItem.addActionListener(e -> exitApplication());
        
        // Window closing cross behavior
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });

        // About dialog
        aboutItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(StudentFeedbackForm.this, 
                "Student Feedback Form v1.0\nSubmitted by Aaditya Bhosale", 
                "About", 
                JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void processFeedback() {
        String name = nameField.getText().trim();
        
        // Validate empty name
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Name field cannot be empty.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String ratingStr = ratingField.getText().trim();
        int rating = 0;
        
        // Handle NumberFormatException for rating
        try {
            rating = Integer.parseInt(ratingStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Rating must be a valid number.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String course = courseField.getText().trim();
        String comments = commentsField.getText().trim();

        // Format and append
        String entry = String.format("Name: %s | Course: %s | Rating: %d | Comments: %s\n", name, course, rating, comments);
        feedbackList.append(entry);
        
        JOptionPane.showMessageDialog(this, 
            "Feedback added to list.", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
            
        clearForm(); // Automatically clear after successful submission
    }

    private void clearForm() {
        nameField.setText("");
        courseField.setText("");
        ratingField.setText("");
        commentsField.setText("");
    }

    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to exit?", 
            "Confirm Exit", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Ensure GUI creation runs on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            StudentFeedbackForm form = new StudentFeedbackForm();
            form.setLocationRelativeTo(null); // Center on screen
            form.setVisible(true);
        });
    }
}
