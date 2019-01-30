package util;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * @author SSP 
 * This class create a user Iinterface to display graph
 */

public class GraphicalUserInterface extends ApplicationFrame {

	// Serial Version Id = Default
	private static final long serialVersionUID = 1L;
	final DefaultCategoryDataset dcd = new DefaultCategoryDataset();

	// Constructor creates a barchart with certain paameters
	public GraphicalUserInterface(String somethingToDisp, String graphName, ArrayList<String> name,ArrayList<Integer> chat) {
		super(graphName);

		JFreeChart barChart = ChartFactory.createBarChart(
				somethingToDisp, // NameOfBarChart
				"Name", // X-Axis Name
				"Score", // Y-Axis Name
				createDataset(name,chat), // Method to add data and value
				PlotOrientation.VERTICAL, // orientation
				false, true, false); // legend, tooltips, urls

		// Specify type of chart
		ChartPanel chartPanel = new ChartPanel(barChart);
		
		// Default setting of dimensions of graph
		chartPanel.setPreferredSize(new java.awt.Dimension(570, 380));
		setContentPane(chartPanel);

	}
	
	// this methods creates a dataset of all value that come to it and pass to graph create
	private CategoryDataset createDataset(ArrayList<String> name, ArrayList<Integer> chat) {

		for (int i = 0; i < name.size() && i < chat.size(); i++) {
			dcd.setValue(chat.get(i), name.get(i), name.get(i));
		}

		return dcd;
	}

}
