package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import java.sql.Connection;

import sample.Model.*;
import sample.Model.MenuTable;
import sample.Model.OrderTable;

public class ChefFieldController {

    @FXML
    private TabPane tp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab ChefMenu;

    @FXML
    private TableView<sample.Model.MenuTable> MenuTable;

    @FXML
    private TableColumn<MenuTable, Integer> PatientId;

    @FXML
    private TableColumn<MenuTable, String> PatientMenu;

    @FXML
    private Tab ChefOrder;

    @FXML
    private TableView<sample.Model.OrderTable> OrderTable;

    @FXML
    private TableColumn<OrderTable, Integer> NOrder;

    @FXML
    private TableColumn<OrderTable, String> DateOrder;

    @FXML
    private TableColumn<OrderTable, String> Order;
    @FXML
    private RadioMenuItem ChefFraise;


    @FXML
    private TableColumn<OrderTable, String> QuantiteOrder;

    private DatabaseHandler databaseHandler;
    boolean a=true;
    ObservableList<MenuTable> tableau = FXCollections.observableArrayList();
    ObservableList<OrderTable> tab = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        UpdateTable();
        MenuTable.setEditable(true);
        databaseHandler = new DatabaseHandler();
        tp.getSelectionModel().select(ChefOrder);
        tp.getSelectionModel().select(ChefMenu);
        //showOrder();
        //showMenu();
    }
    private void AddOrder() {
        databaseHandler = new DatabaseHandler();
        String Numero = NOrder.getText().trim();

        String  date = DateOrder.getText().trim();
        String Ordre=" ";
        if(ChefFraise.isSelected()){
            Ordre="Fraise";
        }
        String Quantity = QuantiteOrder.getText().trim();



        Chef chef = new Chef(Numero,date,Ordre,Quantity);
        databaseHandler.addOrder(chef);
    }

    private void showOrder(){
        Connection con = null;
        try {
            con=DatabaseHandler.getDbConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT idordertable,cDate,Commande,Quantity FROM ordertable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //try{
              //  tab.add(new OrderTable(rs.getInt("idordertable"),rs.getString("cDate")
                //        ,rs.getString("Commande"),rs.getString("Quantity")));
            //} catch (SQLException throwables) {
              //  throwables.printStackTrace();
            //}
            NOrder.setCellValueFactory(new PropertyValueFactory<>("idordertable"));
            DateOrder.setCellValueFactory(new PropertyValueFactory<>("cDate"));
            Order.setCellValueFactory(new PropertyValueFactory<>("Commande"));
            QuantiteOrder.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

            //OrderTable.setItems(null);
            OrderTable.setItems(tab);

        }
    }


    private ObservableList<MenuTable> showMenu(){
        Connection con = null;
        try {
            con=DatabaseHandler.getDbConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT idpatientstable,Menu FROM patientstable");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //try{
              //  tableau.add(new MenuTable(rs.getInt("idpatientstable"),rs.getString("")));
            //} catch (SQLException throwables) {
               // throwables.printStackTrace();
            //}


        }
        PatientId.setCellValueFactory(new PropertyValueFactory<>("idpatientstable"));
        PatientMenu.setCellValueFactory(new PropertyValueFactory<>("Menu"));


        MenuTable.setItems(tableau);
        return tableau;
    }
    public void UpdateTable(){
        PatientId.setCellValueFactory(new PropertyValueFactory<MenuTable,Integer>("idpatientstable"));
        PatientMenu.setCellValueFactory(new PropertyValueFactory<MenuTable,String>("Menu"));

        tableau = showMenu();
        MenuTable.setItems(tableau);
    }


}