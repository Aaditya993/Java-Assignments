

import javax.swing.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StudentMarksController {
    private StudentModel model;
    private StudentMarksView view;

    public StudentMarksController(StudentModel model, StudentMarksView view) {
        this.model = model;
        this.view = view;
        
        new JFXPanel(); // Initialize JavaFX toolkit

        view.btnAdd.addActionListener(e -> addRecord());
        view.btnUpdate.addActionListener(e -> updateRecord());
        view.btnDelete.addActionListener(e -> deleteRecord());
        view.btnAverage.addActionListener(e -> showAverage());
    }

    private void addRecord() {
        try {
            String roll = view.txtRoll.getText();
            String name = view.txtName.getText();
            double marks = Double.parseDouble(view.txtMarks.getText());
            
            StudentMarks sm = new StudentMarks(roll, name, marks);
            model.addStudent(sm);
            view.tableModel.addRow(new Object[]{roll, name, marks});
            
            JOptionPane.showMessageDialog(view, "Row added to table.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid Marks.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRecord() {
        try {
            int row = view.table.getSelectedRow();
            if (row == -1) throw new ArrayIndexOutOfBoundsException();
            
            String roll = view.txtRoll.getText();
            String name = view.txtName.getText();
            double marks = Double.parseDouble(view.txtMarks.getText());
            
            StudentMarks sm = new StudentMarks(roll, name, marks);
            model.updateStudent(row, sm);
            
            view.tableModel.setValueAt(roll, row, 0);
            view.tableModel.setValueAt(name, row, 1);
            view.tableModel.setValueAt(marks, row, 2);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(view, "Select a row to update.", "Warning", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid Marks.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        try {
            int row = view.table.getSelectedRow();
            if (row == -1) throw new ArrayIndexOutOfBoundsException();
            
            model.deleteStudent(row);
            view.tableModel.removeRow(row);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(view, "Select a row to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showAverage() {
        double avg = model.calculateAverage();
        view.lblAverage.setText("Class Average: " + String.format("%.2f", avg));
        
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setTitle("JavaFX Preview");
            Label label = new Label("Class Average (JavaFX): " + String.format("%.2f", avg));
            label.setStyle("-fx-font-size: 16px; -fx-padding: 20px;");
            stage.setScene(new Scene(new StackPane(label), 300, 100));
            stage.show();
        });
    }
}