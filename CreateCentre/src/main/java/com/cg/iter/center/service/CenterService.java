package com.cg.iter.center.service;

import java.util.List;

import com.cg.iter.center.entities.DiagnosticCentre;

public interface CenterService {

	DiagnosticCentre addCentre(DiagnosticCentre diagnosticCentre);
	
	String getCentre(String centreName);
	
	Boolean getDetails(String centreId);
	
	void deleteCentreById(String centreId);

	List<DiagnosticCentre> getCentres();

}
