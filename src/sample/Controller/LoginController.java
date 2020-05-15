package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Animation.Shaker;
import sample.Database.DatabaseHandler;
import sample.Model.User;

public class LoginController
{
	@FXML
	private ResourceBundle	resources;

	@FXML
	private URL							location;

	@FXML
	private TextField				LoginUsername;

	@FXML
	private Button					LoginButton;

	@FXML
	private RadioButton			signinradio1;

	@FXML
	private RadioButton			signinradio2;

	@FXML
	private PasswordField		LoginPassword;

	@FXML
	private Button					registerbutton;

	@FXML
	private RadioButton			signinradio3;

	private DatabaseHandler	databaseHandler;

	public static int userConnectedId;

	@FXML
	void initialize()
	{

		databaseHandler = new DatabaseHandler();

		LoginButton.setOnAction(event -> {
			String loginText = LoginUsername.getText().trim();
			String loginPwd = LoginPassword.getText().trim();
			String type = " ";
			if (signinradio1.isSelected())
			{
				type = "doctor";
			}
			else if (signinradio2.isSelected())
			{
				type = "Chef";
			}
			else if (signinradio3.isSelected())
			{
				type = "Salesman";
			}
			else
			{

			}

			User user = new User();
			user.setUsername(loginText);
			user.setPassword(loginPwd);
			user.setType(type);

			ResultSet userRow = databaseHandler.getUser(user);

			int counter = 0;
			try
			{
				while (userRow.next())
				{
					counter++;
				}
				if (counter == 1)
				{
					userConnectedId = databaseHandler.getUserId(user);

					if (type == "doctor")
					{
						showMedField();
					}
					else if (type == "Chef")
					{
						showChefField();
					}
					else if (type == "Salesman")
					{
						showSalesField();
					}

				}
				else
				{
					Shaker userNameShaker = new Shaker(LoginUsername);
					Shaker passwordShaker = new Shaker(LoginPassword);
					userNameShaker.shake();
					passwordShaker.shake();

				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}

		});
		//Take to signup page
		registerbutton.setOnAction(event -> {
			registerbutton.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/sample/view/register.fxml"));
			try
			{
				loader.load();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();

		});

	}

	//take to medfield (the page is designed but incomplete)
	private void showMedField()
	{
		registerbutton.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sample/view/MedField.fxml"));
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();

	}

	// will take us to cheffield
	private void showChefField()
	{
		registerbutton.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sample/view/ChefField.fxml"));
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();

	}

	//salesfield page
	private void showSalesField()
	{
		registerbutton.getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/sample/view/SalesField.fxml"));
		try
		{
			loader.load();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();

	}

}
