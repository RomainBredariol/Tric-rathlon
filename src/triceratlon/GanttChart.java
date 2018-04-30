package triceratlon;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class GanttChart extends JFrame {

   private static final long serialVersionUID = 1L;

   public GanttChart(String title) {
      super(title);
      // Creation du jeu de donnees
      IntervalCategoryDataset dataset = getCategoryDataset();
      
      // Creation du Gantt
      JFreeChart chart = ChartFactory.createGanttChart(
            "Vue d'ensemble du triathlon", // Titre Gantt
            "Taches", // Titre ordonnee
            "Temps", // Titre abscisse
            dataset);// jeu de donnees à utiliser

      ChartPanel panel = new ChartPanel(chart);
      setContentPane(panel);
   }

   private IntervalCategoryDataset getCategoryDataset() {
      
      TaskSeries series2 = new TaskSeries("Actual Date");
      series2.add(new Task("Requirement",
            Date.from(LocalDate.of(2017, 7, 3).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 7, 05).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      series2.add(new Task("Design",
            Date.from(LocalDate.of(2017, 7, 6).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      series2.add(new Task("Coding",
            Date.from(LocalDate.of(2017, 7, 18).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 7, 27).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      series2.add(new Task("Testing",
            Date.from(LocalDate.of(2017, 7, 28).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 8, 1).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      series2.add(new Task("Deployment",
            Date.from(LocalDate.of(2017, 8, 2).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 8, 4).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));

      TaskSeriesCollection dataset = new TaskSeriesCollection();
      dataset.add(series2);// ajoute les sets de données au Gantt
      return dataset;
   }
}