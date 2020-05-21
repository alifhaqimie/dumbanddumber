package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class Patient
{
	private String	fullname;
	private String	Etatpatient;
	private String Regime;
	private String	breakfast,lunch,dinner;
	private int	doctorId;
	private int Patientid;
	public  int getPatientid() {
		return Patientid;
	}

	public Patient() {
	}

	public Patient(String fullname, String etatpatient, String regime, String breakfast, String lunch, String dinner) {
		this.fullname = fullname;
		Etatpatient = etatpatient;
		Regime = regime;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
	}


	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEtatpatient() {
		return Etatpatient;
	}

	public void setEtatpatient(String etatpatient) {
		Etatpatient = etatpatient;
	}

	public String getRegime() {
		return Regime;
	}

	public void setRegime(String regime) {
		Regime = regime;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public void setPatientid(int patientid) {
		Patientid = patientid;
	}
}
