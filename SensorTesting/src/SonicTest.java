import lejos.hardware.Button;
import lejos.hardware.lcd.*;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class SonicTest {

	public static void main(String[] args) {
		EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S2);
		LCD.clear();
		
		while(!Button.ENTER.isDown()) {
			LCD.clear();
			int sampleSize = sonic.sampleSize();
			float[] sonicSample = new float[sampleSize];
			sonic.fetchSample(sonicSample, 0);
			LCD.clear();
			System.out.println(sonicSample[0]*100);
		}
		sonic.close();

	}

}
