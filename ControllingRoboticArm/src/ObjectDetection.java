import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class ObjectDetection {
	static NXTRegulatedMotor psi = Motor.C;
	static NXTRegulatedMotor theta = Motor.B;
	static EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S2);
	
	private static final double PSI_GEAR_RATIO = -2.47;
	private static final int THETA_GEAR_RATIO = -5;
	
	private static final int[] POSITIONS = {180, 135, 90, 45, 0};
	private static final int SAMPLE_SIZE = touch.sampleSize();
	
	private static void raiseArm() {
		theta.rotateTo(15*THETA_GEAR_RATIO);
	}

	private static void lowerArm() {
		theta.rotateTo(0);
	}

	private static void goToPosition(int position) {
		psi.rotateTo((int) Math.round(POSITIONS[position - 1]*PSI_GEAR_RATIO));
	}
	
	private static void setup() {
		psi.setSpeed(Math.round(60*PSI_GEAR_RATIO));
		theta.setSpeed(30*THETA_GEAR_RATIO);
	}

	public static void main(String[] args) {
		setup();
		for (int i = 1; i < POSITIONS.length + 1; i++) {
			boolean objectAtPosition = detectObjectAtPosition(i);
			if (objectAtPosition) {
				System.out.format("Object found at position %d\n", i);
			} else {
				System.out.format("Object not found at position %d\n", i);
			}
		}
	}

	private static boolean detectObjectAtPosition(int position) {
		raiseArm();
		goToPosition(position);
		lowerArm();
		return getTouchPressed();
	}

	private static boolean getTouchPressed() {
		float[] touchSample = new float[SAMPLE_SIZE];
		touch.fetchSample(touchSample, 0);
		return touchSample[0] > 0.5;
	}

}
