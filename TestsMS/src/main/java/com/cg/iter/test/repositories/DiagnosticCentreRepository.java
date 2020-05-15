package com.cg.iter.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.iter.test.entities.DiagnosticCentre;

public interface DiagnosticCentreRepository extends JpaRepository<DiagnosticCentre, String> {
	
	DiagnosticCentre findByCentreName(String centreName);

}
