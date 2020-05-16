package sample.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.Animation.Shaker;
import sample.Database.DatabaseHandler;
import sample.Model.User;

public class RegisterController
{
	@FXML
	private ResourceBundle	resources;

	@FXML
	private URL							location;

	@FXML
	private TextField				registerfirstname;

	@FXML
	private TextField				registerusername;

	@FXML
	private PasswordField		registerpassword;

	@FXML
	private Button					signupbutton;

	@FXML
	private RadioButton			registerradio1;

	@FXML
	private RadioButton			registerradio2;

	@FXML
	private RadioButton			registerradio3;

	@FXML
	private TextField				registerlastname;

	LoginController					loginController;

	@FXML
	void initialize()
	{
		signupbutton.setOnAction(event -> { createUser(); });

	}

	private void createUser()
	{
		DatabaseHandler databaseHandler = new DatabaseHandler();
		String name = registerfirstname.getText();
		String lastname = registerlastname.getText();
		String username = registerusername.getText();
		String password = registerpassword.getText();
		String type = " ";
		Boolean exists = false;
		if (registerradio1.isSelected())
		{
			type = "doctor";
		}
		else if (registerradio2.isSelected())
		{
			type = "Chef";
		}
		else if (registerradio3.isSelected())
		{
			type = "Salesman";
		}
		else
		{

		}

		List<String> listUsernames = databaseHandler.getListUsernames();
		for (String user : listUsernames)
		{
			if (user.equalsIgnoreCase(username))
			{
				exists = true;
				Shaker userNameShaker = new Shaker(registerusername);
				userNameShaker.shake();
				registerusername.setText(" ");
				break;
			}
		}
		if (!exists)
		{
			User user = new User(name, lastname, username, password, type);
			databaseHandler.signUpUser(user);

		}

	}

}
