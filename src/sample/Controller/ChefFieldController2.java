package sample.Controller;

import static sample.Database.DatabaseHandler.getDbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Model.MenuTable;
import sample.Model.OrderTable;

public class ChefFieldController2
{

	@FXML
	private ResourceBundle									resources;

	@FXML
	private URL															location;

	@FXML
	private TableView<MenuTable>						menushown;

	@FXML
	private TableColumn<MenuTable, Integer>	PatientId;

	@FXML
	private TableColumn<MenuTable, String>	patientbreakfast;

	@FXML
	private TableColumn<MenuTable, String>	patientLunch;

	@FXML
	private TableColumn<MenuTable, String>	patientDinner;

	@FXML
	private DatePicker											ChefOrderDate;

	@FXML
	private DatePicker											ChefReceptionDate;

	@FXML
	private TextField												ChefOrderNumber;

	@FXML
	private TextField												ChefQuantity;

	@FXML
	private Button													ChefSubmit;

	@FXML
	private MenuButton											ChefOrder;

	@FXML
	private RadioMenuItem										ChefFraise;

	@FXML
	private RadioMenuItem										ChefPT;

	@FXML
	private RadioMenuItem										viande;

	@FXML
	private RadioMenuItem										autre;

	@FXML
	private Button													ChefClear;
	@FXML
	private TextField ChefOrdern;

	@FXML
	private TableColumn<?, ?>								StrorageIDElement;

	@FXML
	private TableColumn<?, ?>								StrorageElement;

	@FXML
	private TableColumn<?, ?>								StorageZone;

	@FXML
	private TableColumn<?, ?>								StorageType;

	@FXML
	private TextField												TextidElement;

	@FXML
	private TextField												Textelement;

	@FXML
	private TextField												Textstoragetype;

	@FXML
	private TextField												Textstoragezone;

	@FXML
	private MenuButton											MenuZone;

	@FXML
	private MenuButton											MenuType;

	@FXML
	private Button													StorageAddElement;

	@FXML
	private Button													StorageModifyButton;

	@FXML
	private Button													StorageDeleteButton;
	ObservableList<MenuTable>								tableau				= FXCollections.observableArrayList();
	String																	pattern				= "yyyy-MM-dd";
	DateTimeFormatter												dateFormatter	= DateTimeFormatter.ofPattern(pattern);

	@FXML
	void initialize()
	{
		UpdateTable();
		ChefSubmit.setOnAction(event -> { addOrder();
            reseet();});
	}

	private ObservableList<MenuTable> showMenu()
	{
		Connection con = null;
		try
		{
			con = getDbConnection();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		ResultSet rs = null;
		try
		{
			rs = con
				.createStatement()
				.executeQuery("SELECT idpatientstable,breakfast,lunch,dinner FROM patientstable");
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
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			try
			{
				tableau
					.add(new MenuTable(rs.getInt("idpatientstable"), rs.getString("breakfast"), rs.getString("lunch"), rs.getString("dinner")));
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

		}
		PatientId.setCellValueFactory(new PropertyValueFactory<>("idpatientstable"));
		patientbreakfast.setCellValueFactory(new PropertyValueFactory<>("breakfast"));
		patientLunch.setCellValueFactory(new PropertyValueFactory<>("lunch"));
		patientDinner.setCellValueFactory(new PropertyValueFactory<>("breakfast"));

		menushown.setItems(tableau);
		return tableau;
	}

	public void UpdateTable()
	{
		PatientId.setCellValueFactory(new PropertyValueFactory<MenuTable, Integer>("idpatientstable"));
		patientbreakfast.setCellValueFactory(new PropertyValueFactory<MenuTable, String>("breakfast"));
		patientLunch.setCellValueFactory(new PropertyValueFactory<MenuTable, String>("lunch"));
		patientDinner.setCellValueFactory(new PropertyValueFactory<MenuTable, String>("dinner"));

		tableau = showMenu();
		menushown.setItems(tableau);
	}

	public void addOrder()
	{
		DatabaseHandler databaseHandler = new DatabaseHandler();
		String ChefOrdernumber = ChefOrdern.getText();
		LocalDate ChefOrderDa = ChefOrderDate.getValue();
		LocalDate ReceptionDa = ChefReceptionDate.getValue();
		String Commande = " ";
		if (ChefFraise.isSelected())
		{
			Commande = "Fraise";
		}
		String Quantity = ChefQuantity.getText();
		OrderTable order = new OrderTable(ChefOrdernumber,ChefOrderDa, ReceptionDa, Commande, Quantity);
		databaseHandler.makeorder(order);
	}
	public void reseet(){
        ChefOrderDate.getEditor().clear();
        ChefReceptionDate.getEditor().clear();
        ChefQuantity.clear();
    }
}
