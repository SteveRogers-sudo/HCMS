package com.cg.iter.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iter.appointment.entities.Appointment;
import com.cg.iter.appointment.entities.DiagnosticCentre;
import com.cg.iter.appointment.entities.Test;
import com.cg.iter.appointment.exception.DuplicateUserIdException;
import com.cg.iter.appointment.service.AppointmentService;

import org.apache.log4j.Logger;

@RestController     
@RequestMapping
public class AppointmentController {
	
	private static final Logger logger = Logger.getLogger(AppointmentController.class);
	
	@Autowired
	AppointmentService aservice;
	
	@GetMapping("/FetchCenterList")
	public List<DiagnosticCentre> getCenterList() {
		List<DiagnosticCentre> center = aservice.DiagnosticCenterList();
		return center;
	}

	@GetMapping("/FetchTestList/{centerId}")
	public List<Test> getTestList(@PathVariable("centerId") String centerId) {
		List<Test> dCenter = aservice.TestsList(centerId);
		return dCenter;
	}

	@PostMapping("/makeAppointment")
	public ResponseEntity<Boolean> updateAppointment(@RequestBody Appointment app) {
		Boolean exists = aservice.userIdFound(app.getUserId());
		if (exists) {
			System.out.println(exists);
			aservice.makeAppointment(app);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			throw new DuplicateUserIdException("Sorry! User Id exists");
		}
	}

	 @PutMapping("/approveAppointment")
	 public String approveAppointment(@RequestBody Appointment app) {
		 System.out.println(app.toString());
		 String updateMsg =  aservice.approveAppointment(app);
		 return updateMsg;
	 }
		 
	@ExceptionHandler(DuplicateUserIdException.class)
	public ResponseEntity<Boolean> userFound(DuplicateUserIdException e) {
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
	
	@GetMapping("/FetchAppList") // displaying all values in appointment table
	   public List<Appointment> getAppointmentList(){
		   List<Appointment> center=aservice.AppointmentList();
		   return center; 
	   }

}
