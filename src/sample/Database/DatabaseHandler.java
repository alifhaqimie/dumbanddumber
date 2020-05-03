package sample.Database;

import sample.Model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost + ":"
                +dbPort + "/"
                +dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

        return dbConnection;
    }
    public void signUpUser(User user){
        String insert = "INSERT INTO "  + Const.Users_Table + "("+Const.Users_FIRSTNAME+","+Const.Users_LASTNAME+","+Const.Users_USERNAME+","+Const.Users_PASSWORD+","+Const.Users_TYPE+")" +"Values(?,?,?,?,?)" ;
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString( 1,user.getFirstname());
            preparedStatement.setString( 2,user.getLastname());
            preparedStatement.setString( 3,user.getUsername());
            preparedStatement.setString( 4,user.getPassword());
            preparedStatement.setString( 5,user.getType());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        if(!user.getUsername().equals("")||!user.getPassword().equals("")){
            String query = "SELECT * FROM " + Const.Users_Table + " " + "WHERE" +" " +  Const.Users_USERNAME + "=?" + " "
                    + "AND" +" " + Const.Users_PASSWORD + "=?" + " " + "AND" + " " +Const.Users_TYPE + "=?";
            try{
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setString(2,user.getPassword());
                preparedStatement.setString(3,user.getType());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("please enter your information");

        }


        return resultSet;

    }

}
