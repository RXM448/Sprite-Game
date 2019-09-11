package application;

public class Check {
	double GXLeft, GXRight, GYTop, GYBottom, PXLeft, PXRright, PYTop, PYBottom;

	public synchronized static  boolean checkColission(double GLeft,double GRight,double GTop,double GBottom,double PLeft,double PRight,double PTop,double PBottom) {
		if ((PLeft > GLeft) && (PLeft < GRight) && (PTop > GTop) && (PTop < GBottom)){
			return true;
		}else if((PLeft > GLeft) && (PLeft < GRight) && (PBottom > GTop) && (PBottom < GBottom)) {
			return true;
		}else if((PRight > GLeft) && (PRight < GRight) && (PTop > GTop) && (PTop < GBottom)) {
			return true;
		}else if((PRight > GLeft) && (PRight < GRight) && (PBottom > GTop) && (PBottom < GBottom)) {
			return true;
		}else
			return false;
	}

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
