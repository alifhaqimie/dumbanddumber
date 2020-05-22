package sample.Controller;

import static sample.Database.DatabaseHandler.getDbConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Model.OrderTable;

public class SalesFieldController
{

	@FXML
	private ResourceBundle									resources;

	@FXML
	private URL															location;
	@FXML
	private TableView<OrderTable>						orderstable;

	@FXML
	private TableColumn<OrderTable, String>	orderID;

	@FXML
	private TableColumn<OrderTable, Date>		orderDate;

	@FXML
	private TableColumn<OrderTable, String>	orderCommande;

	@FXML
	private TableColumn<OrderTable, String>	orderQuantity;
	ObservableList<OrderTable>							oblista;
	Connection															conu	= null;
	private DatabaseHandler									databaseHandler;

	@FXML
	void initialize()
	{
		oblista = FXCollections.observableArrayList();
		orderstable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		databaseHandler = new DatabaseHandler();
		UpdateTable();
	}

	private ObservableList<OrderTable> showit()
	{
		//this.databaseHandler.getListOrders();

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
				.executeQuery("SELECT idordertable,Cdate,orderNumber,Commande,Quantity FROM ordertable");
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

				LocalDate date = Instant
					.ofEpochMilli(rs.getDate("Cdate").getTime())
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
				OrderTable order = new OrderTable(
					rs.getInt("idordertable"),
					rs.getString("orderNumber"),
					date,
					rs.getString("Commande"),
					rs.getString("Quantity")
				);

				oblista.add(order);
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}

		}
		orderID.setCellValueFactory(new PropertyValueFactory<>("idordertable"));
		orderDate.setCellValueFactory(new PropertyValueFactory<>("Cdate"));
		orderCommande.setCellValueFactory(new PropertyValueFactory<>("Commande"));
		orderQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

		orderstable.setItems(oblista);
		return oblista;
	}

	public void UpdateTable()
	{
		orderID.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("orderNumber"));
		orderDate.setCellValueFactory(new PropertyValueFactory<OrderTable, Date>("Cdate"));
		orderCommande.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Commande"));
		orderQuantity.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Quantity"));

		oblista = showit();
		orderstable.setItems(oblista);
	}

}
