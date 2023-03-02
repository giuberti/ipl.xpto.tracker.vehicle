package com.ipl.xpto.trackingVehicles.business;

import com.ipl.xpto.trackingVehicles.model.Vehicle;

public class VehicleBusiness {

	public boolean ValidateVehicle(Vehicle vehicle) {

		boolean result = true;
		
		if (vehicle.getVin().length() <= 0)
			result = false;
		
		if (vehicle.getNumberPlate().length() <= 0)
			result = false;
		
		return result;
	}
}
