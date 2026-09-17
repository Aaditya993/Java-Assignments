


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentMarksView extends JFrame {
    public DefaultTableModel tableModel;
    public JTable table;
    public JTextField txtRoll, txtName, txtMarks;
    public JButton btnAdd, btnUpdate, btnDelete, btnAverage;
    public JLabel lblAverage;

    public StudentMarksView() {
        setTitle("Student Marks Viewer");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] columns = {"Roll No", "Name", "Marks"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.add(new JLabel("Roll No:"));
        txtRoll = new JTextField();
        inputPanel.add(txtRoll);
        
        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Marks:"));
        txtMarks = new JTextField();
        inputPanel.add(txtMarks);

        JPanel btnPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnAverage = new JButton("Show Average");
        lblAverage = new JLabel("Class Average: 0.0");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnAverage);
        btnPanel.add(lblAverage);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        bottomPanel.add(btnPanel, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(bottomPanel, BorderLayout.SOUTH);
    }
}