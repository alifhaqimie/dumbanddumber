package sample.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import sample.Model.Table;

public class SalesFieldController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<OrderTable> orderstable;

    @FXML
    private TableColumn<OrderTable, Integer> orderID;

    @FXML
    private TableColumn<OrderTable, String> orderDate;

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

    private ObservableList<OrderTable> showorders()
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
        ObservableList<OrderTable> oblista = FXCollections.observableArrayList();
        try
        {
            PreparedStatement prp = conu
                    .prepareStatement(
                            "SELECT * FROM ordertable"
                    );
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
                //oblista
                       // .add(new OrderTable(rs.getInt("idordertable"), rs.getString("Cdate"), rs.getString("Commande"), rs.getString("Quantity")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //catch (SQLException e)
            //{
                //e.printStackTrace();
            //}
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
        orderID.setCellValueFactory(new PropertyValueFactory<OrderTable, Integer>("idordertable"));
        orderDate.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Cdate"));
        orderCommande.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Commande"));
        orderQuantity.setCellValueFactory(new PropertyValueFactory<OrderTable, String>("Quantity"));

        oblista = showorders();
        orderstable.setItems(oblista);
    }

}