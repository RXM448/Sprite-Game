package application;

public class Check {
	double XLeft1, XRight1, YTop1, YBottom1, XLeft2, XRright2, YTop2, YBottom2;

	// checks the boundaries of two objects to see if there is overlap and therefore collision 
	public synchronized static  boolean checkColission(double Left1,double Right1,double Top1,double Bottom1,double Left2,double Right2,double Top2,double Bottom2) {
		
		 if((Left2 > Left1) && (Left2 < Right1) && (Bottom2 > Top1) && (Bottom2 < Bottom1)) {
			return true;
		}else if((Right2 > Left1) && (Right2 < Right1) && (Top2> Top1) && (Top2 < Bottom1)) {
			return true;
		}else if((Right2 > Left1) && (Right2 < Right1) && (Bottom2 > Top1) && (Bottom2 < Bottom1)) {
			return true;
		}else
			return false;
	}

	//checks a spike is not aboput to move out of bounds 
	public synchronized static int SpikeBoundry(double layoutX, double layoutY) {
		if (layoutX < 10) {
			return 1;
		}
		else if(layoutX > 560) {
			return 1;
		}
		else if(layoutY > 280) {
			return 2;
		}
		else if(layoutY < 10) {
			return 2;
		}
		else {
			return 0;
		}
	}
}
