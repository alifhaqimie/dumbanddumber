package sample.Controller;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Model.MenuTable;
import sample.Model.OrderTable;
import sample.Model.Table;

import static sample.Database.DatabaseHandler.getDbConnection;

public class SalesFieldController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<OrderTable> orderstable;

    @FXML
    private TableColumn<OrderTable, String> orderID;

    @FXML
    private TableColumn<OrderTable, Date> orderDate;

    @FXML
    private TableColumn<OrderTable, String> orderCommande;

    @FXML
    private TableColumn<OrderTable, String> orderQuantity;
    ObservableList<OrderTable> oblista;
    Connection conu	= null;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        orderstable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        databaseHandler = new DatabaseHandler();
        UpdateTable();
    }

    private ObservableList<OrderTable> showit()
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
                    .executeQuery("SELECT Cdate,orderNumber,Commande,Quantity FROM ordertable");
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
                oblista
                        .add(new OrderTable(rs.getString("orderNumber"),rs.getDate("Cdate"),rs.getString("Commande"),rs.getString("Quantity")));
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        }
        orderID.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("Cdate"));
        orderCommande.setCellValueFactory(new PropertyValueFactory<>("Commande"));
        orderQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        orderstable.setItems(oblista);
        return oblista;
    }

    public void UpdateTable()
    {
        orderID.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("orderNumber"));
        orderDate.setCellValueFactory(new PropertyValueFactory<OrderTable,Date>("Cdate"));
        orderCommande.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Commande"));
        orderQuantity.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Quantity"));

        oblista = showit();
        orderstable.setItems(oblista);
    }

}