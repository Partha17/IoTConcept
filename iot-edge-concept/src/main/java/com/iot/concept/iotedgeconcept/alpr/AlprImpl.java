package com.iot.concept.iotedgeconcept.alpr;

import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprPlate;
import com.openalpr.jni.AlprPlateResult;
import com.openalpr.jni.AlprResults;

public class AlprImpl {
	
	public static void main(String[] args) {
		
		try {
			Alpr alpr = new Alpr("us", "openalpr_64/openalpr.conf", "openalpr_64/runtime_data");
			// Set top N candidates returned to 20.
			alpr.setTopN(10);
			// Set pattern to Maryland.
			alpr.setDefaultRegion("md");
			AlprResults results = alpr.recognize("./images/us-1.jpg");
			System.out.format("  %-15s%-8s\n", "Plate Number", "Confidence");
			for (AlprPlateResult result : results.getPlates()) {
				for (AlprPlate plate : result.getTopNPlates()) {
					if (plate.isMatchesTemplate())
						System.out.print("  * ");
					else
						System.out.print("  - ");
					System.out.format("%-15s%-8f\n", plate.getCharacters(), plate.getOverallConfidence());
				
				}
				// Make sure to call this to release memory.
				alpr.unload();
			} 
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	
	
		
	}
	
	

}
