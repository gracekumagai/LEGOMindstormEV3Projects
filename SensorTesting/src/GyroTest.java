import lejos.hardware.Button;
import lejos.hardware.lcd.*;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroTest {

	public static void main(String[] args) {
		EV3GyroSensor tilt = new EV3GyroSensor(SensorPort.S3);
		LCD.clear();
		
		while(!Button.ENTER.isDown()) {
			LCD.clear();
			int sampleSize = tilt.sampleSize();
			float[] tiltSample = new float[sampleSize];
			float[] rateSample = new float[sampleSize];
			tilt.getAngleMode().fetchSample(tiltSample, 0);
			tilt.getRateMode().fetchSample(rateSample, 0);
			LCD.clear();
			System.out.println(tiltSample[0] + " " + rateSample[0]);
		}
		tilt.close();

	}

}
