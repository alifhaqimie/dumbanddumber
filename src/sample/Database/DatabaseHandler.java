package sample.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sample.Model.Chef;
import sample.Model.Patient;
import sample.Model.Table;
import sample.Model.User;

import java.sql.*;

import static sample.Model.Patient.*;

public class DatabaseHandler extends Configs
{
	static Connection dbConnection;

	public static Connection getDbConnection() throws ClassNotFoundException, SQLException
	{
		String connectionString = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "active";
		Class.forName("com.mysql.jdbc.Driver");
		dbConnection = DriverManager.getConnection(connectionString, "root", "1337");

		return dbConnection;
	}

	//signup
	public void signUpUser(User user)
	{
		String insert = "INSERT INTO " +
			Const.Users_Table + "(" + Const.Users_FIRSTNAME + "," + Const.Users_LASTNAME + "," +
			Const.Users_USERNAME + "," + Const.Users_PASSWORD + "," + Const.Users_TYPE + ")" +
			"Values(?,?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getLastname());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getType());

			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public ResultSet getUser(User user)
	{
		ResultSet resultSet = null;

		if (!user.getUsername().equals("") || !user.getPassword().equals(""))
		{
			String query = "SELECT * FROM " +
				Const.Users_Table + " " + "WHERE" + " " + Const.Users_USERNAME + "=?" + " " + "AND" + " " +
				Const.Users_PASSWORD + "=?" + " " + "AND" + " " + Const.Users_TYPE + "=?";
			try
			{
				PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getType());

				resultSet = preparedStatement.executeQuery();

			}
			catch (SQLException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("please enter your information");

		}

		return resultSet;

	}
	

	public int getUserId(User user)
	{
		ResultSet resultSet = null;
		int i = 0;
		if (!user.getUsername().equals("") && !user.getPassword().equals(""))
		{
			String query = "SELECT userId FROM " +
				Const.Users_Table + " " + "WHERE" + " " + Const.Users_USERNAME + "=?" + " " + "AND" + " " +
				Const.Users_PASSWORD + "=?" + " " + "AND" + " " + Const.Users_TYPE + "=?";
			try
			{
				PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getType());

				resultSet = preparedStatement.executeQuery();
				if (resultSet.next())
				{
					i = resultSet.getInt("userId");
					resultSet.next();
				}

			}
			catch (SQLException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("please enter your information");

		}
		return i;

	}

	//add a patient
	public void addPatient(Patient patient)
	{
		String insertion = "INSERT INTO " +
			Const.Patients_Table + "(" + Const.Patients_FULLNAME + "," + Const.Patients_ETAT + "," +
			Const.Patients_MENU + "," + Const.Patients_Regime + "," + Const.Patients_usersId+ ")" +
			"Values(?,?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertion);
			preparedStatement.setString(1, patient.getFullname());
			preparedStatement.setString(2, patient.getEtatpatient());
			preparedStatement.setString(3, patient.getMenu());
			preparedStatement.setString(4, patient.getRegime());
			preparedStatement.setInt(5, patient.getDoctorId());

			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
	public void addOrder(Chef chef){
		String insertion = "INSERT INTO " +
				Const.Order_Table + "(" + Const.Order_id + "," + Const.Order_date + "," +
				Const.Order_commande + "," + Const.Order_quantity + ")" +
				"Values(?,?,?,?)";
		try{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertion);
			preparedStatement.setString(1,chef.getNumero());
			preparedStatement.setString( 2,chef.getDate() );
			preparedStatement.setString( 3,chef.getOrdre());
			preparedStatement.setString( 4,chef.getQuantity());

			preparedStatement.executeUpdate();

		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}


}
