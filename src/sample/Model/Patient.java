package sample.Model;

import javafx.beans.property.SimpleStringProperty;

public class Patient
{
	private String	fullname;
	private String	Etatpatient;
	private String	breakfast,lunch,dinner;
	private String Regime;
	private int	doctorId;
	private int Patientid;
	public  int getPatientid() {
		return Patientid;
	}

	public void setPatientid(int patientid) {
		Patientid = patientid;
	}


	public Patient()
	{
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

	public Patient(String fullname, String etatpatient, String breakfast, String lunch, String dinner, String regime) {
		this.fullname = fullname;
		Etatpatient = etatpatient;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		Regime = regime;
	}

	public int getDoctorId()
	{
		return doctorId;
	}

	public void setDoctorId(int doctorId)
	{
		this.doctorId = doctorId;
	}

	public String getFullname()
	{
		return fullname;
	}

	public void setFullname(String fullname)
	{
		this.fullname =fullname;
	}

	public String getEtatpatient()
	{
		return Etatpatient;
	}

	public void setEtatpatient(String etatpatient)
	{
		Etatpatient = etatpatient;
	}
	public String getRegime()
	{
		return Regime;
	}

	public void setRegime(String regime)
	{
		Regime = regime;
	}
}
