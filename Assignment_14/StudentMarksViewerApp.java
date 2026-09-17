

import javax.swing.SwingUtilities;

public class StudentMarksViewerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentModel model = new StudentModel();
            StudentMarksView view = new StudentMarksView();
            new StudentMarksController(model, view);
            
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        });
    }
}