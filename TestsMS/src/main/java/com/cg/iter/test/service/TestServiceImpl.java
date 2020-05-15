package com.cg.iter.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iter.test.entities.DiagnosticCentre;
import com.cg.iter.test.entities.Test;
import com.cg.iter.test.repositories.DiagnosticCentreRepository;
import com.cg.iter.test.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepository tr;
	@Autowired
	DiagnosticCentreRepository dcr;
	
	@Override
	public Test addTest(Test test) {
		return tr.save(test);
	}

	@Override
	public List<DiagnosticCentre> getCentres() {
		return dcr.findAll();

	}

	@Override
	public List<Test> getTest() {
		return tr.findAll();

	}

	@Override
	public DiagnosticCentre findByCentreName(String centreName) {
		return dcr.findByCentreName(centreName);
	}

	@Override
	public Optional<Test> findBycentreNameAndTestName(String centreName, String testName) {
		return tr.findBycentreNameAndTestName(centreName, testName);

	}

	@Override
	public void save(DiagnosticCentre centre) {
		dcr.save(centre);
		
	}

	@Override
	public void deleteTestById(String testId) {
		tr.deleteById(testId);
		
	}


	

	

}
