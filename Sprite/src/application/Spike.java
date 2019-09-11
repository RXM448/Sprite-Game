package application;

public class Spike {

	private double  XCoord, YCoord, XDim, YDim, XVol, YVol;

	public Spike(double xVol, double yVol) {
		super();
//		XCoord = xCoord;
//		YCoord = yCoord;
//		XDim = xDim;
//		YDim = yDim;
		XVol = xVol;
		YVol = yVol;
	}

	public double getXCoord() {
		return XCoord;
	}

	public void setXCoord(double xCoord) {
		XCoord = xCoord;
	}

	public double getYCoord() {
		return YCoord;
	}

	public void setYCoord(double yCoord) {
		YCoord = yCoord;
	}

	public double getXDim() {
		return XDim;
	}

	public void setXDim(double xDim) {
		XDim = xDim;
	}

	public double getYDim() {
		return YDim;
	}

	public void setYDim(double yDim) {
		YDim = yDim;
	}

	public double getXVol() {
		return XVol;
	}

	public void setXVol(double xVol) {
		XVol = xVol;
	}

	public double getYVol() {
		return YVol;
	}

	public void setYVol(double yVol) {
		YVol = yVol;
	}
}
