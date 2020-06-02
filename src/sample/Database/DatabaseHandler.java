package sample.Database;
import sample.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sample.Model.Chef;
import sample.Model.Menu;
import sample.Model.OrderTable;
import sample.Model.Patient;
import sample.Model.Quantity;
import sample.Model.Storage;
import sample.Model.User;


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
	public List<String> getListUsernames()
	{
		ResultSet resultSet = null;
		List<String> list = new ArrayList();
		String query = "SELECT " + Const.Users_USERNAME + " FROM " + Const.Users_Table;

		PreparedStatement preparedStatement;
		try
		{
			preparedStatement = getDbConnection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				list.add(resultSet.getString("username"));
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
		}

		return list;

	}


	public int getUserId(User user)
	{
		ResultSet resultSet = null;
		int i = 0;
		if (!user.getUsername().equals("") && !user.getPassword().equals(""))
		{
			String query = "SELECT userid FROM " +
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
					i = resultSet.getInt("userid");
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
				Const.Patients_BREAKFAST + "," +Const.Patients_LUNCH + "," + Const.Patients_DINNER + "," +Const.Patients_Regime + "," + Const.Patients_usersId+ ")" +
				"Values(?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertion);
			preparedStatement.setString(1, patient.getFullname());
			preparedStatement.setString(2, patient.getEtatpatient());
			preparedStatement.setString(6, patient.getRegime());
			preparedStatement.setString(3, patient.getBreakfast());
			preparedStatement.setString(4, patient.getLunch());
			preparedStatement.setString(5, patient.getDinner());
			preparedStatement.setInt(7, patient.getDoctorId());

			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
	public void addOrder(Chef chef)
	{
		String insertion = "INSERT INTO " +
				Const.Order_Table + "(" + Const.Order_id + "," + Const.Order_date + "," +
				Const.Order_commande + "," + Const.Order_quantity + ")" + "Values(?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertion);
			preparedStatement.setString(1, chef.getNumero());
			preparedStatement.setString(2, chef.getDate());
			preparedStatement.setString(3, chef.getOrdre());
			preparedStatement.setString(4, chef.getQuantity());

			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	//add an element
	public void addelement(Storage storage)
	{
		String insertion = "INSERT INTO " +
				Const.Storage_Table + "(" + Const.Element_id + "," + Const.Storage_element+ "," +
				Const.Storage_zone + "," + Const.Storage_type +  ")" +
				"Values(?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insertion);
			preparedStatement.setString(1, String.valueOf(storage.getIdelement()));
			preparedStatement.setString(2, storage.getElement());
			preparedStatement.setString(3, storage.getStoragezone());
			preparedStatement.setString(4, storage.getStoragetype());


			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
	public void getQuantity(Quantity quantity){

		String insert = "INSERT INTO " +
				Const.Quantity_Table + "("+ Const.Quantity_Date + ","  + Const.Quantity_Element+ "," + Const.Quantity_initial + "," + Const.Quantity_consumed + "," + Const.Quantity_ordered + ","+ Const.Quantity_present+ ")" +
				"Values(?,?,?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
			preparedStatement.setDate(1, java.sql.Date.valueOf(quantity.getDate()));
			preparedStatement.setString(2, quantity.getElement());
			preparedStatement.setInt(3, quantity.getInitial());
			preparedStatement.setInt(4, quantity.getConsumed());
			preparedStatement.setInt(5, quantity.getOrdered());
			preparedStatement.setInt(6, quantity.getPresent());
			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}



	public void getPlat(Menu plat)
	{
		String insert = "INSERT INTO " +
				Const.Menu_Table + "(" + Const.Menu_plat + "," + Const.Menu_gras1 + "," + Const.Menu_gras2 +
				"," + Const.Menu_fruit1 + "," + Const.Menu_fruit2 + "," + Const.Menu_fruit3 + "," +
				Const.Menu_legume1 + "," + Const.Menu_legume2 + "," + Const.Menu_legume3 + "," +
				Const.Menu_cereal + "," + Const.Menu_cereal1 + "," + Const.Menu_boisson + "," +
				Const.Menu_vvpolav + "," + Const.Menu_name + ")" + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
			preparedStatement.setString(1, plat.getPlat());
			preparedStatement.setString(2, plat.getGras());
			preparedStatement.setString(3, plat.getGras1());
			preparedStatement.setString(4, plat.getFruit1());
			preparedStatement.setString(5, plat.getFruit2());
			preparedStatement.setString(6, plat.getFruit3());
			preparedStatement.setString(7, plat.getLeg1());
			preparedStatement.setString(8, plat.getLeg2());
			preparedStatement.setString(9, plat.getLeg3());
			preparedStatement.setString(10, plat.getCereal1());
			preparedStatement.setString(11, plat.getCereal2());
			preparedStatement.setString(12, plat.getBoi());
			preparedStatement.setString(13, plat.getVvpolav());
			preparedStatement.setString(14, plat.getName());

			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public List<String> getLunchNames()
	{
		ResultSet resultSet = null;
		List<String> list = new ArrayList();
		String query = "SELECT name  FROM menutable Where plat = 'lunch';";

		PreparedStatement preparedStatement;
		try
		{
			preparedStatement = getDbConnection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				list.add(resultSet.getString("name"));
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
		}

		return list;

	}
	public List<String> getBreakfastNames()
	{
		ResultSet resultSet = null;
		List<String> list = new ArrayList();
		String query = "SELECT name  FROM menutable Where plat = 'breakfast';";

		PreparedStatement preparedStatement;
		try
		{
			preparedStatement = getDbConnection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				list.add(resultSet.getString("name"));

			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
		}

		return list;

	}

	public void makeorder(OrderTable order)
	{
		//	String pattern = "yyyy-MM-dd";
		//	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		String insert = "INSERT INTO " +
				Const.Order_Table + "(" + Const.Order_date + "," + Const.Order_rdate + "," +

				Const.Order_commande + "," + Const.Order_quantity + ")" + "Values(?,?,?,?)";

		//Const.Order_commande + "," + Const.Order_quantity + "," + Const.Order_number + ")" +
		//"Values(?,?,?,?,?)";

		try
		{
			PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
			preparedStatement.setDate(1, java.sql.Date.valueOf(order.getCdate()));
			preparedStatement.setDate(2, java.sql.Date.valueOf(order.getRdate()));
			preparedStatement.setString(3, order.getCommande());
			preparedStatement.setString(4, order.getQuantity());

			preparedStatement.executeUpdate();

		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}


	public List<String> getDinnerNames()
	{
		ResultSet resultSet = null;
		List<String> list = new ArrayList();
		String query = "SELECT name  FROM menutable Where plat = 'dinner';";

		PreparedStatement preparedStatement;
		try
		{
			preparedStatement = getDbConnection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				list.add(resultSet.getString("name"));
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
		}

		return list;

	}

}