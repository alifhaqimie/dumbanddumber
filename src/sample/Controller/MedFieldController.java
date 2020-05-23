package sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Animation.Shaker;
import sample.Database.DatabaseHandler;
import sample.Model.Menu;
import sample.Model.Patient;
import sample.Model.Table;

public class MedFieldController
{
	@FXML
	private ResourceBundle							resources;

	@FXML
	private URL													location;

	@FXML
	private TableView<Table>						patientsTable;

	@FXML
	private TableColumn<Table, Integer>	PatientID;

	@FXML
	private TableColumn<Table, String>	PatientName;

	@FXML
	private TableColumn<Table, String>	PatientStatus;

	@FXML
	private TableColumn<Table, String>	PatientBreakfast;

	@FXML
	private TableColumn<Table, String>	PatientLunch;

	@FXML
	private TableColumn<Table, String>	PatientDinner;

	@FXML
	private TableColumn<Table, String>	PatientDiet;

	@FXML
	private Button											medAddPatient;

	@FXML
	private Button											medDeletePatient;

	@FXML
	private TextField										medPatientName;

	@FXML
	private Button											medModify;

	@FXML
	private TextField										selectedPatientId;

	@FXML
	private ComboBox<String>						StatusBox;

	@FXML
	private ComboBox<String>						BreakfastBox;

	@FXML
	private ComboBox<String>						LunchBox;

	@FXML
	private ComboBox<String>						DinnerBox;

	@FXML
	private ComboBox<String>						DietBox;

	@FXML
	private TextField										medFName;

	@FXML
	private TextField										medLName;

	@FXML
	private TextField										medUsername;

	@FXML
	private Button											medSaveB;

	@FXML
	private PasswordField								medPw;

	@FXML
	private TextField										medUsername1;

	@FXML
	private ComboBox<String>						gras1;

	@FXML
	private ComboBox<String>						frui1;

	@FXML
	private ComboBox<String>						cereal2;

	@FXML
	private ComboBox<String>						boi;

	@FXML
	private ComboBox<String>						fruit2;

	@FXML
	private ComboBox<String>						leg1;

	@FXML
	private ComboBox<String>						leg2;

	@FXML
	private ComboBox<String>						vvpolav;

	@FXML
	private ComboBox<String>						leg3;

	@FXML
	private RadioButton									BreakfastCheckBox;

	@FXML
	private ToggleGroup									lolo;

	@FXML
	private RadioButton									LunchCheckBox;

	@FXML
	private RadioButton									DinnerCheckBox;

	@FXML
	private TextField										name;

	@FXML
	private Button											addmealbutton;

	@FXML
	private ComboBox<String>						gras2;

	@FXML
	private ComboBox<String>						fruit3;

	@FXML
	private ComboBox<String>						cereal1;

	@FXML
	private Button											logout;
	ObservableList<Table>								oblist;
	private DatabaseHandler							databaseHandler;
	Connection													conu	= null;
	int																	index	= -1;
	final ObservableList								bf		= FXCollections.observableArrayList();
	//final ObservableList lc =FXCollections.observableArrayList();
	//final ObservableList di =FXCollections.observableArrayList();

	@FXML
	void initialize()
	{
		databaseHandler = new DatabaseHandler();
		fill();
		Connection con = null;
		seethis();
		showw();
		UpdateTable();
		showInfo();
		patientsTable.setEditable(true);

		//This will allow the table to select multiple rows at once

		patientsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		databaseHandler = new DatabaseHandler();
		medAddPatient.setOnAction(event -> {
			addPatient();

		});
		medModify.setOnAction(event -> { Edit(); });
		medDeletePatient.setOnAction(event -> {
			try
			{
				Delete();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		});

		logout.setOnAction(event ->

		{
			LoginController.setUserConnectedId(null);
			logout.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/sample/view/Login.fxml"));
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
			stage.show();

		});
		medSaveB.setOnAction(event -> {
			SaveP();

		});
		addmealbutton
			.setOnAction(event -> { AddPlat(); seethis(); testing(); resetValues(); showw(); });

	}

	private void AddPlat()
	{
		DatabaseHandler databaseHandler = new DatabaseHandler();
		String menus = "";
		if (BreakfastCheckBox.isSelected())
		{
			menus = "breakfast";
		}
		else if (LunchCheckBox.isSelected())
		{
			menus = "lunch";
		}
		else if (DinnerCheckBox.isSelected())
		{
			menus = "dinner";
		}

		String gass = gras1.getValue().toString();
		String gass1 = gras2.getValue().toString();
		String bois = boi.getValue().toString();
		String fruit = frui1.getValue().toString();
		String fruits2 = fruit2.getValue().toString();
		String fruits3 = fruit3.getValue().toString();
		String legg = leg1.getValue().toString();
		String legg1 = leg2.getValue().toString();
		String legg2 = leg3.getValue().toString();
		String cer = cereal1.getValue().toString();
		String cer1 = cereal2.getValue().toString();
		String vv = vvpolav.getValue().toString();
		String name1 = name.getText();

		Menu plat = new Menu(
			menus,
			gass,
			gass1,
			fruit,
			fruits2,
			fruits3,
			legg,
			legg1,
			legg2,
			cer,
			cer1,
			bois,
			vv,
			name1
		);

		if (
			(menus.equalsIgnoreCase("breakfast") &&
				this.databaseHandler.getBreakfastNames().contains(name1)) ||
				menus.equalsIgnoreCase("lunch") && this.databaseHandler.getLunchNames().contains(name1) || menus.equalsIgnoreCase("dinner") && this.databaseHandler.getDinnerNames().contains(name1)
		)

		{
			System.out.println("Add shaker");

		}
		else
			databaseHandler.getPlat(plat);

	}

	public void Edit()
	{
		try
		{
			String tmp;
			conu = DatabaseHandler.getDbConnection();
			String Value0 = selectedPatientId.getText();
			String Value1 = medPatientName.getText();
			//String Value2 = medPatientState.getText();
			String Value2 = StatusBox.getValue().toString();

			String Value3 = BreakfastBox.getValue().toString();
			String Value4 = LunchBox.getValue().toString();
			String Value5 = DinnerBox.getValue().toString();
			String Value6 = DietBox.getValue().toString();

			String sql = "UPDATE patientstable SET fullname = '" +
				Value1 + "',Etatpatient = '" + Value2 + "',breakfast = '" + Value3 + "',lunch = '" +
				Value4 + "',dinner = '" + Value5 + "',Regime = '" + Value6 + "' WHERE idpatientstable = '" +
				Value0 + "' ";
			PreparedStatement psst = conu.prepareStatement(sql);
			System.out.println(sql);
			psst.execute();
			UpdateTable();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void SaveP()
	{
		try
		{
			String tmp;
			conu = DatabaseHandler.getDbConnection();
			String v1 = medFName.getText();
			String v2 = medLName.getText();
			String v3 = medUsername1.getText();
			String v4 = medPw.getText();
			boolean exists = false;
			List<String> listUsernames = databaseHandler.getListUsernames();
			for (String user : listUsernames)
			{
				if (user.equalsIgnoreCase(v3))
				{
					exists = true;
					Shaker userNameShaker = new Shaker(medUsername1);
					userNameShaker.shake();
					break;
				}
			}
			if (!exists)
			{
				String sql = "UPDATE userstableau SET firstname = '" +
					v1 + "',lastname = '" + v2 + "',username = '" + v3 + "',password = '" + v4 +
					"' WHERE userid = ?";
				PreparedStatement psst = conu.prepareStatement(sql);
				psst.setInt(1, LoginController.userConnectedId);
				psst.execute();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private void addPatient()
	{
		String patientName = medPatientName.getText().trim();
		String patientState = StatusBox.getValue().toString();
		String patientRegime = DietBox.getValue().toString();
		String patientBreakfast = BreakfastBox.getValue().toString();
		String patientLunch = LunchBox.getValue().toString();
		String patientDinner = DinnerBox.getValue().toString();

		Patient patient = new Patient(
			patientName,
			patientState,
			patientRegime,
			patientBreakfast,
			patientLunch,
			patientDinner
		);

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
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet rs = null;
		ObservableList<Table> oblist = FXCollections.observableArrayList();
		try
		{
			PreparedStatement prp = con
				.prepareStatement(
					"SELECT * FROM patientstable INNER JOIN userstable ON patientstable.userid=userstable.userid WHERE userstable.userid=?"
				);
			prp.setInt(1, LoginController.userConnectedId);
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
					.add(new Table(rs.getInt("idpatientstable"), rs.getString("fullname"), rs.getString("Etatpatient"), rs.getString("Regime"), rs.getString("breakfast"), rs.getString("lunch"), rs.getString("dinner")));
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		PatientID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("idpatientstable"));
		PatientName.setCellValueFactory(new PropertyValueFactory<Table, String>("fullname"));
		PatientStatus.setCellValueFactory(new PropertyValueFactory<Table, String>("Etatpatient"));
		PatientDiet.setCellValueFactory(new PropertyValueFactory<Table, String>("Regime"));
		PatientBreakfast.setCellValueFactory(new PropertyValueFactory<Table, String>("breakfast"));
		PatientLunch.setCellValueFactory(new PropertyValueFactory<Table, String>("lunch"));
		PatientDinner.setCellValueFactory(new PropertyValueFactory<Table, String>("dinner"));

		patientsTable.setItems(oblist);

		return oblist;
	}

	private void showInfo()
	{
		Connection cn = null;
		try
		{
			cn = DatabaseHandler.getDbConnection();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet res = null;
		try
		{
			PreparedStatement prp = cn
				.prepareStatement("SELECT * FROM  userstable  WHERE userstable.userid=?");
			prp.setInt(1, LoginController.userConnectedId);
			res = prp.executeQuery();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (res.next())
			{
				try
				{
					medFName.setText(res.getString("firstname"));
					medLName.setText(res.getString("lastname"));
					medUsername.setText(res.getString("username"));
					medPw.setText(res.getString("password"));
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	public void getSelected(javafx.scene.input.MouseEvent event)
	{
		index = patientsTable.getSelectionModel().getSelectedIndex();
		if (index <= -1)
		{
			return;
		}
		selectedPatientId.setText(String.valueOf(PatientID.getCellData(index)).toString());
		medPatientName.setText(PatientName.getCellData(index).toString());

	}

	@FXML
	public void Delete() throws SQLException, ClassNotFoundException
	{
		conu = DatabaseHandler.getDbConnection();
		String sql = "delete from patientstable where idpatientstable = ?";
		try
		{
			PreparedStatement pst = conu.prepareStatement(sql);
			pst.setString(1, selectedPatientId.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Delete");
			//UpdateTable();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		UpdateTable();

	}

	public void fill()
	{
		gras1.getItems().addAll("oil", "egg", "butter", "aucun");
		gras2.getItems().addAll("oil", "egg", "butter", "aucun");
		boi.getItems().addAll("Tea", "juice", "cofe", "aucun");
		frui1.getItems().addAll("apple", "avocado", "banana", "Apricots", "Date Fruit", "aucun");
		fruit2.getItems().addAll("apple", "avocado", "banana", "Apricots", "Date Fruit", "aucun");
		fruit3.getItems().addAll("apple", "avocado", "banana", "Apricots", "Date Fruit", "aucun");
		leg1
			.getItems()
			.addAll(
				"tomato",
				"potato",
				"eggplant",
				"green pepper",
				"carrot",
				"garlic",
				"broccoli",
				"aucun"
			);
		leg2
			.getItems()
			.addAll(
				"tomato",
				"potato",
				"eggplant",
				"green pepper",
				"carrot",
				"garlic",
				"broccoli",
				"aucun"
			);
		leg3
			.getItems()
			.addAll(
				"tomato",
				"potato",
				"eggplant",
				"green pepper",
				"carrot",
				"garlic",
				"broccoli",
				"aucun"
			);
		cereal2.getItems().addAll("bread", "cereal", "pasta", "oats", "corn", "aucun");
		cereal1.getItems().addAll("bread", "cereal", "pasta", "oats", "corn", "aucun");
		vvpolav.getItems().addAll("meat", "fish", "chicken", "aucun");
		StatusBox.getItems().addAll("intesif care", "normal");
		DietBox.getItems().addAll("sugar free", "full");
	}

	public void UpdateTable()
	{
		PatientID.setCellValueFactory(new PropertyValueFactory<Table, Integer>("patientid"));
		PatientName.setCellValueFactory(new PropertyValueFactory<Table, String>("fullname"));
		PatientStatus.setCellValueFactory(new PropertyValueFactory<Table, String>("Etatpatient"));
		PatientBreakfast.setCellValueFactory(new PropertyValueFactory<Table, String>("breakfast"));
		PatientLunch.setCellValueFactory(new PropertyValueFactory<Table, String>("lunch"));
		PatientDinner.setCellValueFactory(new PropertyValueFactory<Table, String>("dinner"));
		PatientDiet.setCellValueFactory(new PropertyValueFactory<Table, String>("Regime"));

		oblist = showPatients();
		patientsTable.setItems(oblist);
	}

	public void resetValues()
	{
		name.clear();
	}

	public void showw()
	{
		try
		{
			conu = DatabaseHandler.getDbConnection();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet rs = null;

		PreparedStatement prp = null;
		try
		{
			prp = conu.prepareStatement("SELECT name from menutable WHERE menutable.plat='breakfast'");
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		try
		{
			rs = prp.executeQuery();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		BreakfastBox.getItems().clear();

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
				//BreakfastBox.getItems().clear();
				BreakfastBox.getItems().addAll(rs.getString("name"));

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}

			try
			{
				conu = DatabaseHandler.getDbConnection();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			ResultSet res = null;

			PreparedStatement prep = null;
			try
			{
				prep = conu.prepareStatement("SELECT  name from menutable WHERE menutable.plat='lunch'");
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

			try
			{
				res = prep.executeQuery();
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			LunchBox.getItems().clear();

			while (true)
			{
				try
				{
					if (!res.next())
						break;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				try
				{
					LunchBox.getItems().addAll(res.getString("name"));

				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			ResultSet resu = null;

			PreparedStatement prepr = null;
			try
			{
				prepr = conu.prepareStatement("SELECT  name from menutable WHERE menutable.plat='dinner'");
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

			try
			{
				resu = prepr.executeQuery();
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			DinnerBox.getItems().clear();
			while (true)
			{
				try
				{
					if (!resu.next())
						break;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				try
				{
					DinnerBox.getItems().addAll(resu.getString("name"));
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	public void seethis()
	{
		LunchBox.getItems().clear();
		BreakfastBox.getItems().clear();
		DinnerBox.getItems().clear();
	}

	public void testing()
	{
		try
		{
			conu = DatabaseHandler.getDbConnection();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet rs = null;

		PreparedStatement prp = null;
		try
		{
			prp = conu.prepareStatement("SELECT name from menutable WHERE menutable.plat='breakfast'");
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		try
		{
			rs = prp.executeQuery();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
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
				BreakfastBox.getItems().clear();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				conu = DatabaseHandler.getDbConnection();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			ResultSet res = null;

			PreparedStatement prep = null;
			try
			{
				prep = conu.prepareStatement("SELECT name from menutable WHERE menutable.plat='lunch'");
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

			try
			{
				res = prep.executeQuery();
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			while (true)
			{
				try
				{
					if (!res.next())
						break;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				LunchBox.getItems().clear();
			}
			ResultSet resu = null;

			PreparedStatement prepr = null;
			try
			{
				prepr = conu.prepareStatement("SELECT name from menutable WHERE menutable.plat='dinner'");
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

			try
			{
				resu = prepr.executeQuery();
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			while (true)
			{
				try
				{
					if (!resu.next())
						break;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				DinnerBox.getItems().clear();
			}

		}
	}

}
