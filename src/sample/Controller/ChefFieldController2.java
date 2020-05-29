package sample.Controller;

import static sample.Database.DatabaseHandler.getDbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.Model.*;

import javax.swing.*;

public class ChefFieldController2
{
	@FXML
	private ComboBox<String> menunames;

	@FXML
	private TextField gras1;

	@FXML
	private TextField fr1;

	@FXML
	private TextField leg1;

	@FXML
	private TextField cereal;

	@FXML
	private TextField gras2;

	@FXML
	private TextField fr2;

	@FXML
	private TextField leg2;

	@FXML
	private TextField cereal1;

	@FXML
	private TextField boi;

	@FXML
	private TextField fr3;

	@FXML
	private TextField leg3;

	@FXML
	private TextField vvpolav;

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
	private ComboBox<String> MenuButton;

	@FXML
	private RadioMenuItem viande;

	@FXML
	private RadioMenuItem fraise;

	@FXML
	private TextField ConsumedQuantity;

	@FXML
	private TextField OrderedQuantity;

	@FXML
	private TextField PresentQuantity;

	@FXML
	private Button RefreshButton;

	@FXML
	private TextField InitialQuantity;

	@FXML
	private DatePicker DatePicker;


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
	private RadioMenuItem										viandee;

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
	private RadioMenuItem z11;
	@FXML
	private RadioMenuItem z21;
	@FXML
	private Button LOGOUT;
	@FXML
	private Button RefreshM;

	@FXML
	private Button StorageDeleteButton;
	Connection													conu	= null;
	ObservableList<MenuTable>								tableau				= FXCollections.observableArrayList();
	String																	pattern				= "yyyy-MM-dd";
	DateTimeFormatter												dateFormatter	= DateTimeFormatter.ofPattern(pattern);
	ObservableList<Storage>								tabl;
	int																	index	= -1;
	@FXML
	void initialize()
	{
		databaseHandler = new DatabaseHandler();
		Connection con = null;
		try {
			con = DatabaseHandler.getDbConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		ResultSet rs = null;

		PreparedStatement prp = null;
		try {
			prp = con.prepareStatement("SELECT element FROM storagetable");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		try {
			rs = prp.executeQuery();
		} catch (SQLException throwables) {
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
			//Storage usr = new Storage();
			//String naam = rs.getString("element");
			//usr.setElement(naam);
			//MenuItem temp = new MenuItem(naam);
			try {
				MenuButton.getItems().addAll(rs.getString("element"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RefreshButton.setOnAction(event ->{
			AddData();
		});
		RefreshM.setOnAction(event -> {
			tableau.removeAll(tableau);
			UpdateTable();
		});
		//showstorage();
		UpdateTabl();
		UpdateTable();
		UpdateComboBox();
		storage.setEditable(true);
		storage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		databaseHandler = new DatabaseHandler();
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
		StorageModifyButton.setOnAction(event -> {
			Edit();
		});
		menunames.setOnAction(event->{
			String query="SELECT * FROM menutable WHERE name=?";
			Connection conu = null;
			try {
				conu = DatabaseHandler.getDbConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			ResultSet ris = null;
			PreparedStatement prip = null;
			try {
				prip=conu.prepareStatement(query);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			try {
				prip.setString(1,(String)menunames.getSelectionModel().getSelectedItem());
				ris = prip.executeQuery();
				while (ris.next()){
					gras1.setText(ris.getString("gras"));
					gras2.setText(ris.getString("gras1"));
					boi.setText(ris.getString("boi"));
					fr1.setText(ris.getString("fruit1"));
					fr2.setText(ris.getString("fruit2"));
					fr3.setText(ris.getString("fruit3"));
					leg1.setText(ris.getString("leg1"));
					leg2.setText(ris.getString("leg2"));
					leg3.setText(ris.getString("leg3"));
					cereal.setText(ris.getString("cereal1"));
					cereal1.setText(ris.getString("cereal2"));
					vvpolav.setText(ris.getString("vvpolav"));
				}
				prip.close();
				ris.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		});
		LOGOUT.setOnAction(event ->

		{
			LoginController.setUserConnectedId(null);
			LOGOUT.getScene().getWindow().hide();
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
		ObservableList<Storage> tabl = FXCollections.observableArrayList();
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
		Textstoragezone.setText(StorageZone.getCellData(index).toString());
		Textstoragetype.setText(StorageType.getCellData(index).toString());
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
	private void addelement() {
		String element = Textelement.getText().trim();
		String elementzone = " ";
		if (z1.isSelected()) {
			elementzone = "A";
		}
		else if(z11.isSelected()){
			elementzone= "B";
		}else{
			elementzone=Textstoragezone.getText().trim();
		}
		String elementtype = " ";
		if(z2.isSelected()) {
			elementtype = "Cold";
		}else if (z21.isSelected()){
			elementtype = "Warm";

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
	public void Edit()
	{
		try
		{
			String tmp;
			conu = DatabaseHandler.getDbConnection();

			String val= TextidElement.getText();
			String Value0= Textelement.getText();
			String Value1 = Textstoragezone.getText();
			String Value2 = Textstoragetype.getText();


			String sql = "UPDATE storagetable SET element = '" +
					Value0 + "',storagezone = '" + Value1 + "',storagetype = '" + Value2  + "' WHERE idelement = '"+val+"' ";
			PreparedStatement psst = conu.prepareStatement(sql);
			System.out.println(sql);
			psst.execute();
			UpdateTabl();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	private void UpdateComboBox() {
		Connection con = null;
		try {
			con = DatabaseHandler.getDbConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		ResultSet rs = null;

		PreparedStatement prp = null;
		try {
			prp = con.prepareStatement("SELECT name from menutable ");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		try {
			rs = prp.executeQuery();
		} catch (SQLException throwables) {
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
			{   menunames.getItems().addAll(rs.getString("name"));
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

	}
	private void AddData() {
		DatabaseHandler databaseHandler = new DatabaseHandler();

		String elemnt = MenuButton.getValue().toString();
		int initial = Integer.parseInt(InitialQuantity.getText());
		int consumed = Integer.parseInt(ConsumedQuantity.getText());
		int ordered = Integer.parseInt(OrderedQuantity.getText());
		int present = Integer.parseInt(PresentQuantity.getText());

		Quantity quantity = new Quantity(elemnt,initial,consumed,ordered,present);

		databaseHandler.getQuantity(quantity);
	}
}
