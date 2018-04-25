package triceratlon;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GantTest {


   public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
         GanttChart example = new GanttChart("Gantt");
         example.setSize(800, 400);
         example.setLocationRelativeTo(null);
         example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         example.setVisible(true);
      });
   }
}
