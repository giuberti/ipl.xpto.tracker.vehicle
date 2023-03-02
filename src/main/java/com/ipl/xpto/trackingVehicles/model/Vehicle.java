package com.ipl.xpto.trackingVehicles.model;

import java.util.UUID;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@Column(name = "vehicleId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@NotNull
	@Column(name = "customerOwner")
	private UUID customerOwner;
	
	@NotNull
	@Column(name = "telemetryProfile")
	private UUID telemetryProfile;
	
	@NotNull
	@Column(name = "currentDriver")
	private UUID currentDriver;
	
	@NotNull
	@Size(min=6, max=12)
	@Column(name = "numberPlate")
	private String numberPlate;
	
	@NotNull
	@Size(min=17, max=17)
	@Column(name = "vin")
	private String vin;
	
	@Column(name = "color")
	@Size(max=30)
	private String color;
	
	public Vehicle() {

	}

	public Vehicle(UUID currentDriver, UUID telemetryProfile, UUID customerOwner, String vin, String color, String numberPlate) {
		this.customerOwner = customerOwner;
		this.telemetryProfile = telemetryProfile;	
		this.currentDriver = currentDriver;
		this.color = color;
		this.vin = vin;
		this.numberPlate = numberPlate;
	}

	public UUID getId() {
		return id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate.toUpperCase();
	}

	public String getNumberPlate() {
		return numberPlate.toUpperCase();
	}
	
	public void setTelemetryProfile(UUID telemetryProfile) {
		this.telemetryProfile = telemetryProfile;
	}

	public UUID getTtelemetryProfile() {
		return telemetryProfile;
	}
	
	public void setCustomerOwner(UUID customerOwner) {
		this.customerOwner = customerOwner;
	}

	public UUID getCustomerOwner() {
		return customerOwner;
	}
	
	public void setCurrentDriver(UUID currentDriver) {
		this.currentDriver = currentDriver;
	}

	public UUID getCurrentDriver() {
		return currentDriver;
	}
	
}
