package module5;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	//public boolean isClick=false;
	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	/** Draw the earthquake as a square */
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		  if(isSelected()==false){
		      pg.rect(x-radius, y-radius, 2*radius, 2*radius);
		  }
		  else{
			  pg.line(x, y, x, y+20);
			  pg.line(x, y, x, y-20);
			  pg.line(x, y, x+20, y);
			  pg.line(x, y, x-20, y);
			  pg.line(x, y, x+20, y+20);
			  pg.line(x, y, x-20, y+20);
			  pg.line(x, y, x+20, y-20);
			  pg.line(x, y, x-20, y-20);
			  pg.noStroke();
			  pg.rect(x-radius, y-radius, 2*radius, 2*radius);
		  }
	}


	/*private void drawLines() {
		// TODO Auto-generated method stub
		 	  pg.strokeWeight(1);
			  pg.stroke(0,100);
			  pg.fill(0,200,0,100);
			  pg.line(x, y, x+20, y+20);
			  pg.line(x, y, x, y+20);
			  pg.line(x, y, x, y-20);
			  pg.line(x, y, x+20, y);
			  pg.line(x, y, x-20, y);
			  pg.line(x, y, x-20, y+20);
			  pg.line(x, y, x+20, y-20);
			  pg.line(x, y, x-20, y-20);	
	}*/
}
