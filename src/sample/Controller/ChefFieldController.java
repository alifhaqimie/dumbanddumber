
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
import java.util.function.Predicate;


import javafx.collections.transformation.FilteredList;

import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;

import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;

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

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import sample.Database.DatabaseHandler;

import sample.Model.*;


import javax.swing.*;





public class ChefFieldController

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

    private TextField searchbox;



    @FXML

    private TextField vvpolav;



    @FXML

    private ResourceBundle									resources;

    @FXML

    private RadioMenuItem ZoneA;



    @FXML

    private RadioMenuItem ZoneB;



    @FXML

    private RadioMenuItem autre;



    @FXML

    private MenuButton MenuType;



    @FXML

    private RadioMenuItem TypeHumide;



    @FXML

    private RadioMenuItem TypeFroid;

    @FXML

    private URL															location;



    @FXML

    private TableView<MenuTable> menushown;



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

    private DatePicker pick;



    @FXML

    private DatePicker											ChefReceptionDate;



    @FXML

    private TextField												ChefOrderNumber;



    @FXML

    private TextField												ChefQuantity;



    @FXML

    private Button													ChefSubmit;





    @FXML

    private RadioMenuItem										ChefFraise;



    @FXML

    private RadioMenuItem										ChefPT;



    @FXML

    private RadioMenuItem										viande;







    @FXML

    private Button													ChefClear;

    @FXML

    private TextField ChefOrdern;



    @FXML

    private TableColumn<Storage, String> StrorageIDElement;



    @FXML

    private TableColumn<Storage, String> StrorageElement;



    @FXML

    private TableColumn<Storage, String>								StorageZone;



    @FXML

    private TableColumn<Storage,String>								StorageType;



    @FXML

    private TextField TextidElement;



    @FXML

    private TextField Textelement;



    @FXML

    private TextField Textstoragezone;



    @FXML

    private TextField Textstoragetype;





    @FXML

    private MenuButton											MenuZone;







    @FXML

    private Button													StorageAddElement;



    @FXML

    private Button													StorageModifyButton;

    @FXML

    private TableView<Storage> StorageTable;

    private DatabaseHandler							databaseHandler;

    @FXML

    private RadioMenuItem z1;

    @FXML

    private RadioMenuItem z2;

    @FXML

    private TextField InitialQuantity;



    @FXML

    private TextField ConsumedQuantity;



    @FXML

    private Button RefreshButton;



    @FXML

    private TextField OrderedQuantity;



    @FXML

    private LineChart<Number, Number> ChartStorage;

    @FXML

    private ComboBox<String> MenuButton;

    @FXML

    private ComboBox<String> ChefOrder;

    @FXML

    private NumberAxis StockY;



    @FXML

    private Button Refresh;

    @FXML

    private Button logs;




    @FXML

    private Button StorageDeleteButton;

    Connection	conu	= null;

    ObservableList<MenuTable> tableau = FXCollections.observableArrayList();

    String pattern	= "yyyy-MM-dd";

    DateTimeFormatter dateFormatter	= DateTimeFormatter.ofPattern(pattern);

    ObservableList<Storage> oblist;



    int index = -1;





    @FXML

    void initialize()

    {

        databaseHandler = new DatabaseHandler();

        //showstorage();

        UpdateTabl();

        UpdateComboBox();

        UpdateComboBoxElement();

        UpdateComboBoxOrder();

        UpdateTable();



        FilteredList<MenuTable> filteredData = new FilteredList<>(tableau, event-> true);
        searchbox.setOnKeyPressed(event-> {
            searchbox.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super MenuTable>) table -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (table.getBreakfast().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if(table.getLunch().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(table.getDinner().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<MenuTable> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(menushown.comparatorProperty());
            menushown.setItems(sortedData);

        });

        ContextMenu contextMenu=new ContextMenu();

        MenuItem poisson=new MenuItem("fish");



        MenuItem fraise=new MenuItem("strawberry");

        poisson.setOnAction(event->{

            DrawChartPoisson();

        });

        fraise.setOnAction(event->{

            DrawChartFraise();

        });

        contextMenu.getItems().addAll(poisson,fraise);

        Refresh.setOnContextMenuRequested(event-> {



            contextMenu.show(Refresh,event.getScreenX(),event.getScreenY());



        });





        Refresh.setContextMenu(contextMenu);

        StorageTable.setEditable(true);

        StorageTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        databaseHandler = new DatabaseHandler();

        ChefSubmit.setOnAction(event -> { addOrder();

            reseet();});

        StorageAddElement.setOnAction(event -> {

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

        menunames.setOnAction(event->{

            String query="SELECT * FROM menutable WHERE name=?";

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

                prp=con.prepareStatement(query);

            } catch (SQLException throwables) {

                throwables.printStackTrace();

            }

            try {

                prp.setString(1,(String)menunames.getSelectionModel().getSelectedItem());

                rs = prp.executeQuery();

                while (rs.next()){

                    gras1.setText(rs.getString("gras"));

                    gras2.setText(rs.getString("gras1"));

                    boi.setText(rs.getString("boi"));

                    fr1.setText(rs.getString("fruit1"));

                    fr2.setText(rs.getString("fruit2"));

                    fr3.setText(rs.getString("fruit3"));

                    leg1.setText(rs.getString("leg1"));

                    leg2.setText(rs.getString("leg2"));

                    leg3.setText(rs.getString("leg3"));

                    cereal.setText(rs.getString("cereal1"));

                    cereal1.setText(rs.getString("cereal2"));

                    vvpolav.setText(rs.getString("vvpolav"));

                }

                prp.close();

                rs.close();

            } catch (SQLException throwables) {

                throwables.printStackTrace();

            }



        });

        RefreshButton.setOnAction(event ->{

            AddData();

            clear();

        });
        logs.setOnAction(event -> {
            LoginController.setUserConnectedId(null);
            logs.getScene().getWindow().hide();
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



    private void clear() {

        pick.getEditor().clear();

        InitialQuantity.clear();

        ConsumedQuantity.clear();

        OrderedQuantity.clear();

    }



    private void UpdateComboBoxOrder() {

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

            prp = con.prepareStatement("SELECT element FROM storagetable ");

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

            {   ChefOrder.getItems().addAll(rs.getString("element"));

            }

            catch (SQLException e)

            {

                e.printStackTrace();

            }

        }



    }



    private void DrawChartFraise() {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

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

            prp = con.prepareStatement("SELECT date,quantitepresente FROM quantitytable  WHERE quantitytable.element='fraise'");

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

            {   series.getData().add(new XYChart.Data(rs.getInt(1), rs.getInt(2)));

            }

            catch (SQLException e)

            {

                e.printStackTrace();

            }

            ChartStorage.getData().add(series);



        }

    }



    private void DrawChartPoisson() {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

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

            prp = con.prepareStatement("SELECT SELECT date,quantitepresente FROM quantitytable  WHER quantitytable.element='poisson'");

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

            {   series.getData().add(new XYChart.Data(rs.getInt(1), rs.getInt(2)));

            }

            catch (SQLException e)

            {

                e.printStackTrace();

            }

            ChartStorage.getData().add(series);



        }

    }



    private void UpdateComboBoxElement() {

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

            prp = con.prepareStatement("SELECT element FROM storagetable ");

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

            {   MenuButton.getItems().addAll(rs.getString("element"));

            }

            catch (SQLException e)

            {

                e.printStackTrace();

            }

        }



    }



    private void AddData() {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        LocalDate datee = pick.getValue();

        String elemnt =MenuButton.getValue().toString();

        int initial = Integer.parseInt(InitialQuantity.getText());

        int consumed = Integer.parseInt(ConsumedQuantity.getText());

        int ordered = Integer.parseInt(OrderedQuantity.getText());

        int present = Integer.parseInt(InitialQuantity.getText())+Integer.parseInt(ConsumedQuantity.getText())-Integer.parseInt(OrderedQuantity.getText());



        Quantity quantity = new Quantity(datee,elemnt,initial,consumed,ordered,present);





        databaseHandler.getQuantity(quantity);

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

            {  menunames.getItems().addAll(rs.getString("name"));

            }

            catch (SQLException e)

            {

                e.printStackTrace();

            }

        }



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

            rs = con.createStatement().executeQuery("SELECT idpatientstable,breakfast,lunch,dinner FROM patientstable");

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

                tableau.add(new MenuTable(rs.getInt("idpatientstable"), rs.getString("breakfast"), rs.getString("lunch"), rs.getString("dinner")));

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

        String Commande = ChefOrder.getValue();

        //if (ChefFraise.isSelected())

        //{

            //Commande = "Fraise";

        //}

        String Quantity = ChefQuantity.getText();

        OrderTable order = new OrderTable(ChefOrderDa, ReceptionDa, Commande, Quantity);

        databaseHandler.makeorder(order);

    }

    public void reseet(){

        ChefOrderDate.getEditor().clear();

        ChefReceptionDate.getEditor().clear();

        ChefQuantity.clear();

    }

    private void addelement()

    {



        String elementName = Textelement.getText().trim();

        String Storagezone= " ";

        if(ZoneA.isSelected()){

            Storagezone="zone A";

        }else if(ZoneB.isSelected()){

            Storagezone="zone B";

        }else{

            Storagezone = Textstoragezone.getText().trim();

        }



        String storagetype= " ";

        if(TypeHumide.isSelected()){

            storagetype="humide";

        }else if(TypeFroid.isSelected()){

            storagetype="froid";

        }else{

            storagetype = Textstoragetype.getText().trim();

        }

        String idelement;

        idelement = "";

        if(ZoneA.isSelected() && TypeHumide.isSelected()){

            idelement="AH"+TextidElement.getText().trim();

        }else if(ZoneA.isSelected() && TypeFroid.isSelected()){

            idelement="AF"+TextidElement.getText().trim();

        }else if(ZoneB.isSelected() && TypeHumide.isSelected()){

            idelement="BH"+TextidElement.getText().trim();

        }else if(ZoneB.isSelected() && TypeFroid.isSelected()){
            idelement="BF"+TextidElement.getText().trim();
    }


        Storage storage = new Storage( idelement,elementName, Storagezone, storagetype);



        databaseHandler.addelement(storage);

        UpdateTabl();





        //storage.setDoctorId(LoginController.userConnectedId);



    }

    private ObservableList<Storage> showElement()

    {

        Connection con = null;

        try

        {

            con = DatabaseHandler.getDbConnection();

        }

        catch (ClassNotFoundException e)

        {

            e.printStackTrace();

        }

        catch (SQLException e)

        {

            e.printStackTrace();

        }

        ResultSet rs = null;

        ObservableList<Storage> oblist = FXCollections.observableArrayList();

        try

        {

            PreparedStatement prp =con.prepareStatement("SELECT * FROM storagetable ");

            rs = prp.executeQuery();

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

                oblist.add(new Storage(rs.getString("idelement"),rs.getString("element"), rs.getString("storagezone"), rs.getString("storagetype")));

            }

            catch (SQLException e)

            {

                e.printStackTrace();

            }

        }

        StrorageIDElement.setCellValueFactory(new PropertyValueFactory<Storage, String>("idelement"));

        StrorageElement.setCellValueFactory(new PropertyValueFactory<Storage, String>("element"));

        StorageZone.setCellValueFactory(new PropertyValueFactory<Storage, String>("storagezone"));

        StorageType.setCellValueFactory(new PropertyValueFactory<Storage, String>("storagetype"));





        StorageTable.setItems(oblist);



        return oblist;

    }

    private void UpdateTabl() {

        StrorageIDElement.setCellValueFactory(new PropertyValueFactory<Storage,String>("idelement"));

        StrorageElement.setCellValueFactory(new PropertyValueFactory<Storage,String>("element"));

        StorageZone.setCellValueFactory(new PropertyValueFactory<Storage,String>("storagezone"));

        StorageType.setCellValueFactory(new PropertyValueFactory<Storage,String>("storagetype"));





        oblist = showElement();

        StorageTable.setItems(oblist);



    }

    public void getSelected(javafx.scene.input.MouseEvent event)

    {

        index = StorageTable.getSelectionModel().getSelectedIndex();

        if (index <= -1)

        {

            return;

        }

        TextidElement.setText(StrorageIDElement.getCellData(index).toString());

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





}
