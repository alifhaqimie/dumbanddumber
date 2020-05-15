package sample.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Model.Patient;
import sample.Model.Table;
import javax.swing.*;

public class MedFieldController
{
	@FXML
	private ResourceBundle	resources;

	@FXML
	private URL	location;

	@FXML
	private TableView<Table> patientsTable;
	@FXML
	private TableColumn<Table, Integer> PatientID;


	@FXML
	private TableColumn<Table, String>	PatientName;

	@FXML
	private TableColumn<Table, String>	PatientStatus;

	@FXML
	private TableColumn<Table, String>	Patientmenu;

	@FXML
	private TableColumn<Table, String>	PatientDiet;

	@FXML
	private Button	medAddPatient;

	@FXML
	private Button	medDeletePatient;

	@FXML
	private Button medModify;

	@FXML
	private TextField medPatientName;

	@FXML
	private TextField medPatientState;

	@FXML
	private TextField medPatientMenu;

	@FXML
	private TextField medPatientRegime;
	@FXML
	private MenuButton menubar;
	@FXML
	private RadioMenuItem tst1;

	@FXML
	private ToggleGroup k;

	@FXML
	private RadioMenuItem tst2;

	@FXML
	private RadioMenuItem tst3;

	@FXML
	private RadioMenuItem tst4;

	@FXML
	private RadioMenuItem tst5;
	@FXML
	private MenuButton menubar1;

	@FXML
	private RadioMenuItem r1;

	@FXML
	private ToggleGroup k1;

	@FXML
	private RadioMenuItem r2;

	@FXML
	private RadioMenuItem r3;

	@FXML
	private RadioMenuItem r4;

	@FXML
	private RadioMenuItem r5;

	@FXML
	private MenuButton menubar2;

	@FXML
	private RadioMenuItem s1;

	@FXML
	private ToggleGroup k2;

	@FXML
	private RadioMenuItem s2;

	@FXML
	private RadioMenuItem s3;

	@FXML
	private RadioMenuItem s4;

	@FXML
	private RadioMenuItem s5;



	ObservableList<Table> oblist;
	Connection conu = null;
	int index = -1;


	private DatabaseHandler	databaseHandler;

	@FXML
	void initialize()
	{
		UpdateTable();
		patientsTable.setEditable(true);

		//This will allow the table to select multiple rows at once

		patientsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		databaseHandler = new DatabaseHandler();
		medAddPatient.setOnAction(event -> {
			addPatient();

		});
		medModify.setOnAction(event -> {
			Edit();
		});
		medDeletePatient.setOnAction(event -> {
			try {
				Delete();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});

	}

	public void Edit(){
		try{
			String tmp;
			conu=DatabaseHandler.getDbConnection();
			String Value0 =PatientID.getText();
			String Value1 = medPatientName.getText();
			String Value2 = medPatientState.getText();
			String Value3 = medPatientMenu.getText();
			String Value4 = medPatientRegime.getText();
			String sql ="UPDATE patientstable SET fullname = '"+Value1+"',Etatpatient = '"+Value2+"',Menu = '"+Value3+"',Regime = '"+Value4+"' WHERE idpatientstable = '"+Value0+"' " ;
			PreparedStatement psst = conu.prepareStatement(sql);
			System.out.println(sql);
			psst.execute();
			UpdateTable();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void addPatient()
	{
		String patientName = medPatientName.getText().trim();
		String patientState= " ";
		if(s1.isSelected()){
			patientState="Soins intensifs";
		}else if(tst2.isSelected()){
			patientState="Hospitalisation court séjour";
		}else if(tst3.isSelected()){
			patientState="Hospitalisation long séjour";
		}else if(tst4.isSelected()){
			patientState="Hospitalisation du jour";
		}else{
			patientState = medPatientState.getText().trim();
		}
		//String patientState = medPatientState.getText().trim();
		//String patientMenu = medPatientMenu.getText().trim();
		//String patientRegime = medPatientRegime.getText().trim();
		String patientRegime= " ";
		if(r1.isSelected()){
			patientRegime="Sans sel";
		}else if(r2.isSelected()){
			patientRegime="Avec sel";
		}else if(r3.isSelected()){
			patientRegime="Sans sucre";
		}else if(r4.isSelected()){
			patientRegime="Avec sucre";
		}else{
			patientRegime = medPatientRegime.getText().trim();
		}

		String patientMenu = " ";
		if(tst1.isSelected()){
			patientMenu="Végétarien";
		}else if(tst2.isSelected()){
			patientMenu="Bœuf";
		}else if(tst3.isSelected()){
			patientMenu="Agneau";
		}else if(tst4.isSelected()){
			patientMenu="Poulet";
		}else{
			patientMenu = medPatientMenu.getText().trim();
		}

		Patient patient = new Patient(patientName, patientState, patientMenu, patientRegime);

		patient.setDoctorId(LoginController.userConnectedId);

		databaseHandler.addPatient(patient);
		UpdateTable();
	}

	private ObservableList<Table> showPatients()
	{
		Connection con = null;
		try
		{
			con = DatabaseHandler.getDbConnection();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet rs = null;
		ObservableList<Table> oblist = FXCollections.observableArrayList();
		try
		{
			PreparedStatement prp =con.prepareStatement("SELECT * FROM patientstable INNER JOIN userstable ON patientstable.userId=userstable.userId WHERE userstable.userid=?");
			prp.setInt(1,LoginController.userConnectedId);
			rs = prp.executeQuery();



			//rs = con.createStatement().executeQuery("SELECT * FROM patientstable INNER JOIN userstable ON patientstable.userId=userstable.userId WHERE userstable.username=?");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		while (true)
		{
			try
			{
				if (!rs.next())
					break;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				oblist
					.add(new Table(rs.getInt("idpatientstable"),rs.getString("fullname"), rs.getString("Etatpatient"), rs.getString("Menu"), rs.getString("Regime")));
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		PatientID.setCellValueFactory(new PropertyValueFactory<>("idpatientstable"));
		PatientName.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		PatientStatus.setCellValueFactory(new PropertyValueFactory<>("Etatpatient"));
		Patientmenu.setCellValueFactory(new PropertyValueFactory<>("Menu"));
		PatientDiet.setCellValueFactory(new PropertyValueFactory<>("Regime"));

		patientsTable.setItems(oblist);

		return oblist;
	}

	@FXML
	public void getSelected(javafx.scene.input.MouseEvent event) {
		index=patientsTable.getSelectionModel().getSelectedIndex();
		if(index<=-1){
			return;
		}
		PatientID.setText(String.valueOf(PatientID.getCellData(index)).toString());
		medPatientName.setText(PatientName.getCellData(index).toString());
		medPatientState.setText(PatientStatus.getCellData(index).toString());
		medPatientMenu.setText(Patientmenu.getCellData(index).toString());
		medPatientRegime.setText(PatientDiet.getCellData(index).toString());
	}
	@FXML
	public void Delete() throws SQLException, ClassNotFoundException {
		conu = DatabaseHandler.getDbConnection();
		String sql = "delete from patientstable where idpatientstable = ?";
		try {
			PreparedStatement pst = conu.prepareStatement(sql);
			pst.setString(1,PatientID.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Delete");
			UpdateTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	public void UpdateTable(){
		PatientID.setCellValueFactory(new PropertyValueFactory<Table,Integer>("idpatientstable"));
		PatientName.setCellValueFactory(new PropertyValueFactory<Table,String>("fullname"));
		PatientStatus.setCellValueFactory(new PropertyValueFactory<Table,String>("Etatpatient"));
		Patientmenu.setCellValueFactory(new PropertyValueFactory<Table,String>("Menu"));
		PatientDiet.setCellValueFactory(new PropertyValueFactory<Table,String>("Regime"));

		oblist = showPatients();
		patientsTable.setItems(oblist);
	}
}
