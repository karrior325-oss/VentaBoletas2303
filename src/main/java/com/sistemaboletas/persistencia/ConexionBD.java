package com.sistemaboletas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class ConexionBD {

    private static Connection conexion;

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {

                Properties props = new Properties();
                InputStream input = ConexionBD.class
                        .getClassLoader()
                        .getResourceAsStream("application.properties");

                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");
                String driver = props.getProperty("db.driver");

                Class.forName(driver);
                conexion = DriverManager.getConnection(url, user, pass);

                System.out.println("Conectado a PostgreSQL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}