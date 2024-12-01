import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  // Set the look and feel to the system's look and feel
            } catch (Exception e) {
                e.printStackTrace();
            }
            NewsGui gui = new NewsGui();  // Create an instance of NewsGui
            gui.setVisible(true);   // Make the GUI visible
        });
    }

}




