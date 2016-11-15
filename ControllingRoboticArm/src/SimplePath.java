import lejos.hardware.motor.*;

public class SimplePath {

	static NXTRegulatedMotor psi = Motor.C;
	static NXTRegulatedMotor theta = Motor.B;
	
	private static final float PSI_GEAR_RATIO = 9/4;
	private static final int THETA_GEAR_RATIO = 5;
	
	public static void main(String[] args) {
		psi.setSpeed(90);
		theta.setSpeed(90);

		theta.rotateTo(-90*THETA_GEAR_RATIO);
		psi.rotateTo(Math.round(-180*PSI_GEAR_RATIO));
		psi.rotateTo(0);
	}

}
