package application;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;

public class Prescription {
	private SimpleStringProperty name;
	private SimpleStringProperty state;// ×´Ì¬
	private SimpleStringProperty range;// ·¶Î§
	private HashMap<String, Medicine> prescription = new HashMap<String, Medicine>();
	private String PatientMediName;
	private int patientMediNum;

	public String getPatientMediName() {
		return PatientMediName;
	}

	public void setPatientMediName(String patientMediName) {
		PatientMediName = patientMediName;
	}

	public int getPatientMediNum() {
		return patientMediNum;
	}

	public void setPatientMediNum(int patientMediNum) {
		this.patientMediNum = patientMediNum;
	}
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getState() {
		return state.get();
	}

	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}

	public HashMap<String, Medicine> getPrescription() {
		return (HashMap<String, Medicine>) prescription;
	}

	public void setPriscription(HashMap<String, Medicine> prescription) {
		this.prescription = prescription;
	}

	public String getRange() {
		return range.get();
	}

	public void setRange(String range) {
		this.range = new SimpleStringProperty(range);
	}


}