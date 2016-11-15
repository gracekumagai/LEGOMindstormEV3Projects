import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class StackingBlocks {
	static NXTRegulatedMotor theta = Motor.B;
	static NXTRegulatedMotor psi = Motor.C;
	static NXTRegulatedMotor gripper = Motor.A;
	
	private static final double PSI_GEAR_RATIO = -2.47;
	private static final int THETA_GEAR_RATIO = -5;
	
	private static final int[] POSITIONS = {180, 135, 90, 45, 0};
	private static final int STACKED_BLOCK_ANGLE = 12; 
	
	public static void placeBlock(int position1, int position2) {
		raiseArm();
		goToPosition(position1);
		lowerArm();
		grab();
		raiseArm();
		goToPosition(position2);
		theta.rotateTo(STACKED_BLOCK_ANGLE*THETA_GEAR_RATIO);
		release();
	}
	
	public static void stackBlock(int position1, int position2) {
		raiseArm();
		goToPosition(position1);
		lowerArm();
		grab();
		raiseArm();
		goToPosition(position2);
		theta.rotateTo(STACKED_BLOCK_ANGLE*THETA_GEAR_RATIO);
		release();		
	}
	

	private static void placeStackedBlock(int position1, int position2) {
		raiseArm();
		goToPosition(position1);
		lowerArm(STACKED_BLOCK_ANGLE);
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
		theta.rotateTo(90*THETA_GEAR_RATIO);
	}

	private static void lowerArm() {
		theta.rotateTo(0);
	}
	
	private static void lowerArm(int angle) {
		theta.rotateTo(angle*THETA_GEAR_RATIO);
	}

	private static void goToPosition(int position) {
		psi.rotateTo((int) Math.round(POSITIONS[position - 1]*PSI_GEAR_RATIO));
	}
	
	private static void setup() {
		psi.setSpeed((int) (90*PSI_GEAR_RATIO));
		theta.setSpeed(45*THETA_GEAR_RATIO);
	}

	public static void main(String[] args) {
		setup();
		
		placeBlock(1, 3);
		stackBlock(2, 3);
		raiseArm();
		goToPosition(5);
		placeStackedBlock(3, 4);
		stackBlock(3, 4);
		raiseArm();
		goToPosition(5);
	}
}
