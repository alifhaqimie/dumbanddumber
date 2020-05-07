package sample.Controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Model.Patient;
import sample.Model.Table;
import sample.Model.User;

public class MedFieldController  {
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TableView<Table> patientsTable;

        @FXML
        private TableColumn< Table, String> PatientName;

        @FXML
        private TableColumn< Table,String> PatientStatus;

        @FXML
        private TableColumn< Table,String> Patientmenu;

        @FXML
        private TableColumn< Table, String> PatientDiet;

        @FXML
        private Button medAddPatient;

        @FXML
        private Button medDeletePatient;

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

        private DatabaseHandler databaseHandler;

        @FXML
        void initialize() {
                databaseHandler = new DatabaseHandler();
                medAddPatient.setOnAction(event ->{
                        addPatient();
                        showPatients();
                        
                });

        }
        private void addPatient() {
                String patientName = medPatientName.getText().trim();
                String patientState = medPatientState.getText().trim();
                String patientMenu = medPatientMenu.getText().trim();
                String patientRegime = medPatientRegime.getText().trim();

                Patient patient = new Patient(patientName, patientState, patientMenu, patientRegime);
                databaseHandler.addPatient(patient);
        }
        private void showPatients(){
                Connection con = null;
                try {
                        con = DatabaseHandler.getDbConnection();
                } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                }
                ResultSet rs = null;
                try{
                        String qq="SELECT * FROM patientstable ";
                        System.out.println(qq);
                        rs = con.createStatement().executeQuery(qq);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                while(true){
                        try {
                                if (!rs.next()) break;
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                        try {
                                oblist.add(new Table(rs.getString("fullname"),rs.getString("Etatpatient")
                                        ,rs.getString("Menu"),rs.getString("Regime")));
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                PatientName.setCellValueFactory(new PropertyValueFactory<>("fullname"));
                PatientStatus.setCellValueFactory(new PropertyValueFactory<>("Etatpatient"));
                Patientmenu.setCellValueFactory(new PropertyValueFactory<>("Menu"));
                PatientDiet.setCellValueFactory(new PropertyValueFactory<>("Regime"));

                patientsTable.setItems(oblist);

        }
        ObservableList<Table> oblist = FXCollections.observableArrayList();







}

