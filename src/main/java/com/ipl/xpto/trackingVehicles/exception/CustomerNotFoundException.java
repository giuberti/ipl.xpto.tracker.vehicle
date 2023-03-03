package com.ipl.xpto.trackingVehicles.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -743643339875321639L;

	public CustomerNotFoundException(String message) {
        super(message);
	}
}
