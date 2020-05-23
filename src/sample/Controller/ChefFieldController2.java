package sample.Controller;

import static sample.Database.DatabaseHandler.getDbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Model.MenuTable;
import sample.Model.OrderTable;
import sample.Model.Patient;
import sample.Model.Storage;

import javax.swing.*;

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
	private TableColumn<Storage,Integer>								StrorageIDElement;

	@FXML
	private TableColumn<Storage, String>								StrorageElement;

	@FXML
	private TableColumn<Storage, String>								StorageZone;

	@FXML
	private TableColumn<Storage,String>								StorageType;

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
	private TableView<Storage> storage;
	private DatabaseHandler							databaseHandler;
	@FXML
	private RadioMenuItem z1;
	@FXML
	private RadioMenuItem z2;

	@FXML
	private Button													StorageDeleteButton;
	Connection													conu	= null;
	ObservableList<MenuTable>								tableau				= FXCollections.observableArrayList();
	String																	pattern				= "yyyy-MM-dd";
	DateTimeFormatter												dateFormatter	= DateTimeFormatter.ofPattern(pattern);
	ObservableList<Storage>								tabl				= FXCollections.observableArrayList();
	int																	index	= -1;
	@FXML
	void initialize()
	{
		databaseHandler = new DatabaseHandler();
		storage.setEditable(true);
		storage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//showstorage();
		UpdateTabl();
		UpdateTable();
		ChefSubmit.setOnAction(event -> { addOrder();
            reseet();});
		StorageAddElement.setOnAction(event ->{
			addelement();
		});
		StorageDeleteButton.setOnAction(event -> {
			try {
				Delete();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
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
		patientDinner.setCellValueFactory(new PropertyValueFactory<>("dinner"));

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
		LocalDate ChefOrderDa = ChefOrderDate.getValue();
		LocalDate ReceptionDa = ChefReceptionDate.getValue();
		String Commande = " ";
		if (ChefFraise.isSelected())
		{
			Commande = "Fraise";
		}
		String Quantity = ChefQuantity.getText();
		OrderTable order = new OrderTable(ChefOrderDa, ReceptionDa, Commande, Quantity);
		databaseHandler.makeorder(order);
	}
	public void reseet(){
        ChefOrderDate.getEditor().clear();
        ChefReceptionDate.getEditor().clear();
        ChefQuantity.clear();
    }
	private ObservableList<Storage> showstorage()
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
					.executeQuery("SELECT * FROM storagetable");
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
				tabl
						.add(new Storage(rs.getInt("idelement"), rs.getString("element"), rs.getString("storagezone"), rs.getString("storagetype")));
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

		}
		StrorageIDElement.setCellValueFactory(new PropertyValueFactory<>("idelement"));
		StrorageElement.setCellValueFactory(new PropertyValueFactory<>("element"));
		StorageZone.setCellValueFactory(new PropertyValueFactory<>("storagezone"));
		StorageType.setCellValueFactory(new PropertyValueFactory<>("storagetype"));

		storage.setItems(tabl);
		return tabl;
	}
	public void UpdateTabl()
	{
		StrorageIDElement.setCellValueFactory(new PropertyValueFactory<Storage, Integer>("idelement"));
		StrorageElement.setCellValueFactory(new PropertyValueFactory<Storage, String>("element"));
		StorageZone.setCellValueFactory(new PropertyValueFactory<Storage, String>("storagezone"));
		StorageType.setCellValueFactory(new PropertyValueFactory<Storage, String>("storagetype"));

		tabl = showstorage();
		storage.setItems(tabl);
	}
	public void getSelected(javafx.scene.input.MouseEvent event)
	{
		index = storage.getSelectionModel().getSelectedIndex();
		if (index <= -1)
		{
			return;
		}
		TextidElement.setText(String.valueOf(StrorageIDElement.getCellData(index)).toString());
		Textelement.setText(StrorageElement.getCellData(index).toString());

	}
	@FXML
	public void Delete() throws SQLException, ClassNotFoundException
	{
		conu = DatabaseHandler.getDbConnection();
		String sql = "delete from storagetable where idelement = ?";
		try
		{
			PreparedStatement pst = conu.prepareStatement(sql);
			pst.setString(1, TextidElement.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "that element have been deleted ");
			UpdateTabl();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		UpdateTabl();

	}
	private void addelement()
	{
		String element = Textelement.getText().trim();
		String elementzone = " ";
		if(z1.isSelected()){
			elementzone="test1";
		}else{
			elementzone=Textstoragezone.getText().trim();
		}
		String elementtype = " ";
		if(z2.isSelected()){
			elementtype="test2";

		}else{
			elementzone=Textstoragetype.getText().trim();
		}


		Storage storage = new Storage(
				element,
				elementzone,
				elementtype
		);

		//storage.setDoctorId(LoginController.userConnectedId);

		databaseHandler.addelement(storage);
		UpdateTabl();
	}
}
