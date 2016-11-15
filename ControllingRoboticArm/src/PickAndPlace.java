import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class PickAndPlace {
	static NXTRegulatedMotor theta = Motor.B;
	static NXTRegulatedMotor psi = Motor.C;
	static NXTRegulatedMotor gripper = Motor.A;
	
	private static final double PSI_GEAR_RATIO = -2.47;
	private static final int THETA_GEAR_RATIO = -5;
	
	static final int[] POSITIONS = {0, 45, 90, 135, 180}; 
	
	public static void moveBlock(int position1, int position2) {
		raiseArm();
		goToPosition(position1);
		lowerArm();
		grab();
		raiseArm();
		goToPosition(position2);
		lowerArm();
		release();
	}
	
	private static void grab() {
		gripper.rotateTo(-90);
	}
	
	private static void release() {
		gripper.rotateTo(0);
	}

	private static void raiseArm() {
		theta.rotateTo(45*THETA_GEAR_RATIO);
	}

	private static void lowerArm() {
		theta.rotateTo(0);
	}

	private static void goToPosition(int position) {
		psi.rotateTo((int) Math.round(POSITIONS[position - 1]*PSI_GEAR_RATIO));
	}
	
	private static void setup() {
		psi.setSpeed((int) (-90*PSI_GEAR_RATIO));
		theta.setSpeed(-45*THETA_GEAR_RATIO);
	}
	
	private static void returnToStart() {
		raiseArm();
		release();
		psi.rotateTo(0);
		theta.rotateTo(0);
		lowerArm();
	}


	public static void main(String[] args) {
		setup();
		
		moveBlock(4, 1);
		moveBlock(2, 4);
		moveBlock(1, 5);
		
		returnToStart();
	}
}
