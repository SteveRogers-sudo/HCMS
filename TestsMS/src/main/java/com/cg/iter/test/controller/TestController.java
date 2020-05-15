package com.cg.iter.test.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iter.test.entities.DiagnosticCentre;
import com.cg.iter.test.entities.Test;
import com.cg.iter.test.exception.RecordFoundException;
import com.cg.iter.test.service.TestService;

@RestController
@RequestMapping("/Test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {
	
	private static final Logger logger = Logger.getLogger(TestController.class);
	@Autowired
	TestService service;
	
	private Random rand = new Random();

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Test test) {
		System.out.println(test);
		test.setTestId(Integer.toString(rand.nextInt(1000)));

		Test test1 = null;

		DiagnosticCentre a = service.findByCentreName(test.getCentre().getCentreName());
		if (a != null) {
			Optional<Test> testEntity = service.findBycentreNameAndTestName(a.getCentreName(), test.getTestName());
			if (testEntity.isPresent()) {
				throw new RecordFoundException("TestName found");
			}
			test1 = new Test(test.getTestName(), a);
		} else {
			DiagnosticCentre centre = new DiagnosticCentre(test.getCentre().getCentreName());
			service.save(centre);
			test1 = new Test(test.getTestName(), centre);

		}

		service.addTest(test1);
		return new ResponseEntity<>(true, HttpStatus.OK);

	}

	@GetMapping("/findCentre")
	public ResponseEntity<List<DiagnosticCentre>> getCentres() {
		List<DiagnosticCentre> list = service.getCentres();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/find")
	public ResponseEntity<List<Test>> getTests() {
		List<Test> list = service.getTest();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);

	}

	@DeleteMapping("/delete/{testId}")
	public ResponseEntity<Boolean> deleteTestById(@PathVariable("testId") String testId) {
		System.out.println(testId);
		service.deleteTestById(testId);
		return new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.OK);

	}
	

	@ExceptionHandler(RecordFoundException.class)
	public ResponseEntity<Boolean> userNotFound(RecordFoundException e) {
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
