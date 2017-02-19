package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;
//import processing.core.PFont;
import processing.core.PShape;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
//import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.StamenMapProvider;
//import de.fhpotsdam.unfolding.providers.ThunderforestProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 250, 50, 600, 500, new StamenMapProvider.WaterColor());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	for(int i=0;i<earthquakes.size();i++){
	    		PointFeature f = earthquakes.get(i);
	    		//System.out.println(f.getProperties());
	    		Object magObj = f.getProperty("magnitude");
	    		float mag = Float.parseFloat(magObj.toString());
	    		if(mag<4f){
	    			/*SimplePointMarker smp = new SimplePointMarker(earthquakes.get(i).getLocation(), earthquakes.get(i).getProperties());
	    			smp.setRadius(9.56f);
	    			smp.setColor(color(155,120,100));
	    			markers.add(smp);*/
	    			markers.add(createMarker(earthquakes.get(i), color(0, 100, 155), 8.0f));
	    		}
	    		else if(mag>=4 && mag<=4.9){
	    			markers.add(createMarker(earthquakes.get(i), color(100, 0, 255), 12.0f));
	    		}
	    		else if(mag>=5){
	    			markers.add(createMarker(earthquakes.get(i), color(155,255,0), 16.0f));
	    		}
	    		
	    	// PointFeatures also have a getLocation method
	    }
	    }
	    map.addMarkers(markers);
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    //int yellow = color(255, 255, 0);
	    
	    //TODO: Add code here as appropriate
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature,int color, Float radius)
	{
		// finish implementing and use this method, if it helps.
		SimplePointMarker smp = new SimplePointMarker(feature.getLocation(), feature.getProperties());
		smp.setRadius(radius);
		smp.setColor(color);
		return smp;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}

	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		fill(255,255,255);
		text("Earthquake Key", 40, 50);
		fill(100,100,100);
		PShape rect1 = createShape(RECT,0,60,200,50);
		shape(rect1);
		fill(155,255,0);
		PShape head1 = createShape(ELLIPSE,05,80,16,16);
		shape(head1);
		fill(255,255,255);
		textSize(15);
		text("Major Earthquakes", 25, 90); 
		fill(150,150,150);
		PShape rect2 = createShape(RECT,0,110,200,50);
		shape(rect2);
		fill(10, 0, 255);
		PShape head2 = createShape(ELLIPSE,05,130,12,12);
		shape(head2);
		fill(255,255,255);
		textSize(15);
		text("Medium Earthquakes", 25, 140);
		fill(200,200,200);
		PShape rect3 = createShape(RECT,0,160,200,50);
		shape(rect3);
		fill(0, 100, 155);
		PShape head3 = createShape(ELLIPSE,05,180,8,8);
		shape(head3);
		fill(255,255,255);
		textSize(15);
		text("Minor Earthquakes", 25, 190);
	
	}
}
