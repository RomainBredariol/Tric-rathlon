package triceratlon;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import javax.swing.JFrame;

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
      
      // Creation du Gantt avec JFreeChart
      JFreeChart chart = ChartFactory.createGanttChart(
            "Vue d'ensemble du triathlon", // Titre Gantt
            "Taches", // Titre ordonnee
            "Temps", // Titre abscisse
            dataset);// jeu de donnees à utiliser

      ChartPanel panel = new ChartPanel(chart);
      setContentPane(panel);
   }

   private IntervalCategoryDataset getCategoryDataset() {
      
	   //Le jeu de données qu'on va utiliser, pour chaque tache : ligne 1 = début, ligne 2 = fin
      TaskSeries taches = new TaskSeries("Duree de la tache");//Nom de la série
      taches.add(new Task("Tache A",
    		  //Attention : les dates sont sous le format AAAA, MM, DD, il faudra les remplacer par des variables
            Date.from(LocalDate.of(2017, 7, 3).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 7, 05).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      taches.add(new Task("Tache B",
            Date.from(LocalDate.of(2017, 7, 6).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      taches.add(new Task("Tache C",
            Date.from(LocalDate.of(2017, 7, 18).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 7, 27).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      taches.add(new Task("Tache D",
            Date.from(LocalDate.of(2017, 7, 28).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 8, 1).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));
      
      taches.add(new Task("Tache E",
            Date.from(LocalDate.of(2017, 8, 2).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2017, 8, 4).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));

      TaskSeriesCollection dataset = new TaskSeriesCollection();
      dataset.add(taches);// ajoute les sets de données au Gantt
      return dataset;
   }
}