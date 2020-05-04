package sample.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

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
        private Button AddPatient;

        @FXML
        private Button DeletePatient;

        @FXML
        void initialize() {
            ;
        }
}
