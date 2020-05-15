package com.cg.iter.center.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.iter.center.entities.DiagnosticCentre;
import com.cg.iter.center.repository.CenterRepository;
@Service
@Transactional
public class CenterServiceImpl implements CenterService {
   @Autowired
	CenterRepository repo;
	
	@Override
	public DiagnosticCentre addCentre(DiagnosticCentre diagnosticCentre) {
		return repo.save(diagnosticCentre);
	}

	@Override
	public String getCentre(String centreName) {
		return repo.getCentre(centreName);

	}

	@Override
	public void deleteCentreById(String centreId) {
		repo.deleteById(centreId);;//JpaRepository method for deleting using centreId
		
	}

	@Override
	public List<DiagnosticCentre> getCentres() {
		return repo.findAll();
	}

	@Override
	public Boolean getDetails(String centreId) {
		return repo.existsById(centreId);
	}

}
