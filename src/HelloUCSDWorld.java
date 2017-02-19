import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Hello World!
 * 
 * This is the basic stub to start creating interactive maps.
 */
public class HelloUCSDWorld extends PApplet {

	UnfoldingMap map;

	public void setup() {
		size(800, 600, OPENGL);

		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleTerrainProvider());
		//map.zoomAndPanTo(14, new Location(32.881, -117.238)); // UCSD
		map.zoomToLevel(2);
		Location valLoc = new Location(-38.14f,-73.03f);
		//Marker val = new SimplePointMarker(valLoc);
		Feature valEq = new PointFeature(valLoc);
		valEq.addProperty("year", "1960");
		valEq.addProperty("title", "Chile");
		valEq.addProperty("Magnitude", "9.2");
		valEq.addProperty("date","May 22");
		//Marker val = new SimplePointMarker(valLoc);
		Marker val = new SimplePointMarker(valLoc,valEq.getProperties());
		map.addMarker(val);
		MapUtils.createDefaultEventDispatcher(this, map);
	}

	public void draw() {
		background(200);
		map.draw();
		//addKey();
		drawButtons();
		mouseReleased();
	}
	
	public void keyPressed(){
		if(key=='w'){
			background(255,255,255);
		}
	}
	public void drawButtons(){
		fill(255,255,255);
		rect(50,100,25,25);
		fill(0,0,0);
		rect(50,150,25,25);
	}
	public void mouseReleased(){
		if(mouseX>50 && mouseX<75 && mouseY>100 && mouseX<125){
			background(255,255,255);
		}
		if(mouseX>50 && mouseX<75 && mouseY>150 && mouseX<175){
			background(10,10,10);
		}
	}

}
