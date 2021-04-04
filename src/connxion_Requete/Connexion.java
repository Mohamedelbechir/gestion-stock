package connxion_Requete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private static  String url = "jdbc:mysql://localhost/bechir";
    private static  String driverName = "com.mysql.jdbc.Driver";
    private static  String username = "root";
    private static  String password = "";
    private static  Connection connexion;


    public  static Connection getConnection() {
    	if(connexion==null){
    		try {
                Class.forName(driverName);
                try {
                    connexion = DriverManager.getConnection(url, username, password);
                    System.out.println("connection Ok !!!");
                } catch (SQLException ex) {

                    System.out.println("Failed to create the database connection.");
                }
            } catch (ClassNotFoundException ex) {

                System.out.println("Driver not found.");
            }
    	}

        return connexion;
    }


}
