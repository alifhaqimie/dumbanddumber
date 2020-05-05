package sample.Controller;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import sample.Database.DatabaseHandler;
import sample.Model.Patient;
import sample.Model.User;

public class MedFieldController {
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TableColumn<?, ?> PatientName;

        @FXML
        private TableColumn<?, ?> PatientStatus;

        @FXML
        private TableColumn<?, ?> Patientmenu;

        @FXML
        private TableColumn<?, ?> PatientDiet;

        @FXML
        private Button medAddPatient;

        @FXML
        private Button medDeletePatient;

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
                        
                });

        }
        private void addPatient(){
                String patientName = medPatientName.getText().trim();
                String patientState = medPatientState.getText().trim();
                String patientMenu = medPatientMenu.getText().trim();
                String patientRegime = medPatientRegime.getText().trim();

                Patient patient = new Patient(patientName,patientState,patientMenu,patientRegime);
                databaseHandler.addPatient(patient);
        }
}
