package com.example.employee;
import java.sql.Connection;
import java.sql.DriverManager;

public class Databse {
    static Connection  connect = null ; ;
    private static void toConnect () {
            try {
//                Class.forName("org.mariadb.jdbc.Driver");
//                String url = "jdbc:mariadb://localhost:3306/JDBC";
//                connect = DriverManager.getConnection(url, "root", "");
                Class.forName("org.mariadb.jdbc.Driver");
                System.out.println("Driver charged");
                String url = "jdbc:mariadb://localhost:3306/JDBC";
                connect = DriverManager.getConnection(url,"root","");
                System.out.println("Connexion established");
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            }
    }
    public static Connection getConnect(){
        if(connect == null)
            toConnect();
        return connect ;
    }
}
