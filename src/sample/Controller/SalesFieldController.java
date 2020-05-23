package sample.Controller;

import static sample.Database.DatabaseHandler.getDbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Animation.Shaker;
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
	@FXML
	private TextField salesFn;

	@FXML
	private TextField salesLn;

	@FXML
	private TextField salesUn;

	@FXML
	private TextField salesUn1;

	@FXML
	private TextField salesPw;

	@FXML
	private Button ordersSave;
	@FXML
	private Button saleslogout;
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
		showInfo();
		saleslogout.setOnAction(event -> {
			LoginController.setUserConnectedId(null);
			saleslogout.getScene().getWindow().hide();
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
		ordersSave.setOnAction(event -> {
			SaveP();
		});
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
				.executeQuery("SELECT idordertable,Cdate,Commande,Quantity FROM ordertable");
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
				OrderTable order = new OrderTable(rs.getInt("idordertable"), rs.getString("Cdate"), date, rs.getString("Commande"), rs.getString("Quantity"));

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
		orderID.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("idordertable"));
		orderDate.setCellValueFactory(new PropertyValueFactory<OrderTable, Date>("Cdate"));
		orderCommande.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Commande"));
		orderQuantity.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Quantity"));

		oblista = showit();
		orderstable.setItems(oblista);
	}
	public void SaveP()
	{
		try
		{
			String tmp;
			conu = DatabaseHandler.getDbConnection();
			String v1 = salesFn.getText();
			String v2 = salesLn.getText();
			String v3 = salesLn.getText();
			String v4 = salesPw.getText();
			boolean exists = false;
			List<String> listUsernames = databaseHandler.getListUsernames();
			for (String user : listUsernames)
			{
				if (user.equalsIgnoreCase(v3))
				{
					exists = true;
					Shaker userNameShaker = new Shaker(salesUn);
					userNameShaker.shake();
					break;
				}
			}
			if (!exists)
			{
				String sql = "UPDATE userstable SET firstname = '" +
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
					salesFn.setText(res.getString("firstname"));
					salesLn.setText(res.getString("lastname"));
					salesUn.setText(res.getString("username"));
					salesPw.setText(res.getString("password"));
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

}
