package com.cg.iter.center.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.iter.center.entities.DiagnosticCentre;
@Repository
public interface CenterRepository extends JpaRepository<DiagnosticCentre,String>{
/*
 * 
 */
	@Query("select d.centreName from DiagnosticCentre d where d.centreName=?1")
	String getCentre(String centreName);
	
}
