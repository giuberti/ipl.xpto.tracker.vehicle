package com.ipl.xpto.trackingVehicles.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipl.xpto.trackingVehicles.business.CustomerBusiness;
import com.ipl.xpto.trackingVehicles.business.DriverBusiness;
import com.ipl.xpto.trackingVehicles.exception.CustomerNotFoundException;
import com.ipl.xpto.trackingVehicles.exception.DriverNotFoundException;
import com.ipl.xpto.trackingVehicles.model.Vehicle;
import com.ipl.xpto.trackingVehicles.repository.VehicleRepository;


@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/tracking")
public class VehicleController {

  @Autowired
  VehicleRepository vehicleRepo;
  
  @Autowired
  CustomerBusiness customerBusiness;
  
  @Autowired
  DriverBusiness driverBusiness;

  @GetMapping("/vehicles")
  public ResponseEntity<List<Vehicle>> getAllTutorials(@RequestParam(required = false) String numberPlate) {
    try {
      List<Vehicle> vehicles = new ArrayList<Vehicle>();

      if (numberPlate == null)
    	  vehicleRepo.findAll().forEach(vehicles::add);
      else
    	  vehicleRepo.findByNumberPlate(numberPlate.toLowerCase()).forEach(vehicles::add);  
      
      if (vehicles.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(vehicles, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/vehicles/{id}")
  public ResponseEntity<Vehicle> getTutorialById(@PathVariable("id") UUID id) {
    Optional<Vehicle> vehicleData = vehicleRepo.findById(id);

    if (vehicleData.isPresent()) {
      return new ResponseEntity<>(vehicleData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/vehicles")
  public ResponseEntity<Vehicle> createTutorial(@RequestBody @Valid Vehicle vehicle) {
    try {
    	
    	if (customerBusiness.VerifyExistingCustomer(vehicle.getCustomerOwner()) == false)
    		throw new CustomerNotFoundException("Customer Not Found");
    	
    	if (driverBusiness.VerifyExistingDriver(vehicle.getCurrentDriver()) == false)
    		throw new DriverNotFoundException("Driver Not Found");
    	
    	Vehicle _vehicle = vehicleRepo.save(
    			new Vehicle(
    					vehicle.getCurrentDriver(), 
    					vehicle.getTelemetryProfile(), 
    					vehicle.getCustomerOwner(), 
    					vehicle.getVin(),
    					vehicle.getColor(), 
    					vehicle.getNumberPlate()));
    	
      return new ResponseEntity<>(_vehicle, HttpStatus.CREATED);
    } catch (CustomerNotFoundException e) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    } catch (DriverNotFoundException e) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/vehicles/{id}")
  public ResponseEntity<Vehicle> updateTutorial(@PathVariable("id") UUID id, @RequestBody Vehicle vehicle) {
    Optional<Vehicle> vehicleData = vehicleRepo.findById(id);

    if (vehicleData.isPresent()) {
		Vehicle _vehicle = vehicleData.get();
		
    	if (customerBusiness.VerifyExistingCustomer(vehicle.getCustomerOwner()) == false)
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    	
    	if (driverBusiness.VerifyExistingDriver(vehicle.getCurrentDriver()) == false)
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		_vehicle.setColor(vehicle.getColor());
		_vehicle.setVin(vehicle.getVin());
		_vehicle.setNumberPlate(vehicle.getNumberPlate());
		_vehicle.setTelemetryProfile(vehicle.getTelemetryProfile());
		_vehicle.setCustomerOwner(vehicle.getCustomerOwner());
		_vehicle.setCurrentDriver(vehicle.getCurrentDriver());
		
      return new ResponseEntity<>(vehicleRepo.save(_vehicle), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/vehicles/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") UUID id) {
    try {
    	vehicleRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}