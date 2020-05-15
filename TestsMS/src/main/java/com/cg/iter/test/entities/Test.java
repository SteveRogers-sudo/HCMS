package com.cg.iter.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "test_s2")
public class Test {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String testId;
	
	private String testName;
	
	@ManyToOne
	@JoinColumn(name = "centre_id")
	private DiagnosticCentre centre;
	
	private String centreName;

	public Test(String testName,DiagnosticCentre centreName) {
		super();
		this.testName = testName;
		this.centre = centreName;
		this.centreName = centreName.getCentreName();
	}
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiagnosticCentre getCentre() {
		return centre;
	}
	public void setCentre(DiagnosticCentre centre) {
		this.centre = centre;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	
	
	
	

}
