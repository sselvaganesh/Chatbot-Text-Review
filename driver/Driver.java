package driver;

import java.util.ArrayList;

import functional.PerformanceEvaluator;

import org.jfree.ui.RefineryUtilities;

import util.GraphicalUserInterface;
import util.MySQLConnector;

/**
 * @author SSP
 * This is the Driver class, Primary class that drives all other classes
 */

public class Driver {
	
	public ArrayList<String> newname = new ArrayList<String>();
	public ArrayList<Integer> chat = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {

		// Sql connector object
		MySQLConnector c = new MySQLConnector();
		
		// The connection to database gets established and query is fired 
		c.CreateConn();
		
		Driver d = new Driver();
		
		// Text parser for the scala textual parsing class
		PerformanceEvaluator performance = new  PerformanceEvaluator();
		
		while(!c.name.isEmpty()) {
			int i = performance.checkperformance((c.chat.remove(0)).toString());
			String myname = (c.name.remove(0));
			if(d.newname.contains(myname))
			{
				int k = d.newname.indexOf(myname);
				int m = d.chat.get(k);
				int n = i+m;
				d.chat.set(k, n);
			}
			else
			{
				d.newname.add(myname);
				d.chat.add(i);
			}
		}
		
		// User interface to display the graph
		GraphicalUserInterface userInterface = new GraphicalUserInterface("Plotting graph for Names against Score", "SentimentScore",
				d.newname,d.chat);
		userInterface.pack();

		// Align the graph to center
		RefineryUtilities.centerFrameOnScreen(userInterface);
		// Visibility set to True
		userInterface.setVisible(true);

	}
	
}
