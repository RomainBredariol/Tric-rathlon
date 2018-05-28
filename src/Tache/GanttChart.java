package Tache;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import javafx.embed.swing.SwingNode;

public class GanttChart {

   private TaskSeries taches;
   IntervalCategoryDataset dataset ;
   
   public GanttChart(SwingNode swingNode) {
      JPanel pane = new JPanel();
      
      // Creation du jeu de donnees
      taches = new TaskSeries("Durée de la tache");
      this.dataset = getCategoryDataset();
      // Creation du Gantt avec JFreeChart
      JFreeChart chart = ChartFactory.createGanttChart(
            "Vue d'ensemble du triathlon", // Titre Gantt
            "Taches", // Titre ordonnee
            "Temps", // Titre abscisse
            dataset);// jeu de donnees Ã  utiliser
      
      ChartPanel panel = new ChartPanel(chart);
      swingNode.setContent((JComponent) pane.add(panel));
      
   }
   
   private IntervalCategoryDataset getCategoryDataset() {
      TaskSeriesCollection dataset = new TaskSeriesCollection();
      dataset.add(taches);
      return dataset;
   }
   
   public void addTache(String nom, String dateDebut, String dateFin) {
	   Date dateD = Date.from(LocalDate.parse(dateDebut).atStartOfDay().toInstant(ZoneOffset.UTC));
	   Date dateF = Date.from(LocalDate.parse(dateFin).atStartOfDay().toInstant(ZoneOffset.UTC)); 
	   
	   this.taches.add(new Task(nom, dateD, dateF));
   }
}