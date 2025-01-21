package org.example;
import java.sql.*;



public class Conexion {

    private static final String SQL_QUERY="SELECT *FROM Animales.animales";
    private static final String URL="";
    private static final String USER="";
    private static final String PASS="";

    public static void main (String[] args) {
        Connection conn=null;
        Statement stm=null;
        ResultSet rs=null;

        try{
            //1. Crear conexion
            conn= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/Animales"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

