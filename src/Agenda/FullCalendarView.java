package Agenda;


import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FullCalendarView {

	private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
	private VBox view;
	private Text calendarTitle;
	private YearMonth currentYearMonth;
	
	private Button previousMonth;
	private Button nextMonth;

	/**
	 * Create a calendar view
	 * 
	 * @param yearMonth
	 *            year month to create the calendar of
	 */
	public FullCalendarView(YearMonth yearMonth) {
		currentYearMonth = yearMonth;
		// Create the calendar grid pane
		GridPane calendar = new GridPane();
		calendar.setPrefSize(600, 400);
		calendar.setGridLinesVisible(true);
		// Create rows and columns with anchor panes for the calendar
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				AnchorPaneNode ap = new AnchorPaneNode();
				ap.setPrefSize(200, 200);
				calendar.add(ap, j, i);
				allCalendarDays.add(ap);
			}
		}
		// Days of the week labels
		Text[] dayNames = new Text[] { new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
				new Text("Wednesday"), new Text("Thursday"), new Text("Friday"), new Text("Saturday") };
		GridPane dayLabels = new GridPane();
		dayLabels.setPrefWidth(600);
		Integer col = 0;
		for (Text txt : dayNames) {
			AnchorPane ap = new AnchorPane();
			ap.setPrefSize(200, 10);
			ap.setBottomAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			dayLabels.add(ap, col++, 0);
		}
		// Create calendarTitle and buttons to change current month
		calendarTitle = new Text();
		this.previousMonth = new Button("<<");
		this.nextMonth = new Button(">>");
		
		HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
		titleBar.setAlignment(Pos.BASELINE_CENTER);
		// Populate calendar with the appropriate day numbers
		populateCalendar(yearMonth);
		// Create the calendar view
		view = new VBox(titleBar, dayLabels, calendar);
	}
	
	public Button getButtonPrev() {
		return this.previousMonth;
	}
	
	public Button getButtonNext() {
		return this.nextMonth;
	}

	public void addEvent(String nom, String date, String color) {
		Label nomEvent = new Label(nom);
		nomEvent.setLayoutY(20);
		String couleur = color.substring(2);
		if(!couleur.equals("ffffffff")) {
			nomEvent.setStyle("-fx-text-fill : white; -fx-background-color : #"+couleur+";");
		}
		
		LocalDate dateEvent = LocalDate.parse(date);
		
		for (AnchorPaneNode ap : allCalendarDays) {	
			if(dateEvent.getYear() == ap.getDate().getYear()) {
				if(dateEvent.getMonthValue() == ap.getDate().getMonthValue()) {
					if(dateEvent.getDayOfMonth() == ap.getDate().getDayOfMonth()) {
						int taille = ap.getChildren().size();
						if(taille > 1) {
							nomEvent.setLayoutY((taille*10)+20);
						}
						ap.getChildren().add(nomEvent);
					}
				}
			}
		}

	}

	/**
	 * Set the days of the calendar to correspond to the appropriate date
	 * 
	 * @param yearMonth
	 *            year and month of month to render
	 */
	public void populateCalendar(YearMonth yearMonth) {
		// Get the date we want to start with on the calendar
		LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		// Dial back the day until it is SUNDAY (unless the month starts on a sunday)
		while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}
		// Populate the calendar with day numbers
		for (AnchorPaneNode ap : allCalendarDays) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0, ap.getChildren().size());
			}
			Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
			ap.setDate(calendarDate);
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			ap.getChildren().add(txt);
			calendarDate = calendarDate.plusDays(1);
		}
		// Change the title of the calendar
		calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
	}

	/**
	 * Move the month back by one. Repopulate the calendar with the correct dates.
	 */
	public void previousMonth() {
		currentYearMonth = currentYearMonth.minusMonths(1);
		populateCalendar(currentYearMonth);
	}

	/**
	 * Move the month forward by one. Repopulate the calendar with the correct
	 * dates.
	 */
	public void nextMonth() {
		currentYearMonth = currentYearMonth.plusMonths(1);
		populateCalendar(currentYearMonth);
	}

	public VBox getView() {
		return view;
	}

	public ArrayList<AnchorPaneNode> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}
}
