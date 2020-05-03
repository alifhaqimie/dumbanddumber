package sample.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Database.DatabaseHandler;
import sample.Model.User;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registerfirstname;

    @FXML
    private TextField registerusername;

    @FXML
    private PasswordField registerpassword;

    @FXML
    private Button signupbutton;

    @FXML
    private RadioButton registerradio1;

    @FXML
    private RadioButton registerradio2;

    @FXML
    private RadioButton registerradio3;

    @FXML
    private TextField registerlastname;
    @FXML
    void initialize() {
        signupbutton.setOnAction(event -> {
            createUser();

        });

    }
    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String name = registerfirstname.getText();
        String lastname = registerlastname.getText();
        String username = registerusername.getText();
        String password = registerpassword.getText();
        String type = " ";
        if(registerradio1.isSelected()){
            type="doctor";
        }else if(registerradio2.isSelected()){
            type="Chef";
        }else if(registerradio3.isSelected()){
            type="Salesman";
        }else {

        }
        User user = new User(name,lastname,username,password,type);

        databaseHandler.signUpUser(user);

    }
}
