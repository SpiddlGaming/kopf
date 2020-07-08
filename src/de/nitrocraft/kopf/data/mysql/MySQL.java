package de.nitrocraft.kopf.data.mysql;

import de.nitrocraft.kopf.Main;
import de.nitrocraft.kopf.data.Data;

import java.sql.*;

public class MySQL {

    public static Connection connection;

    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + Data.MySQLHost + ":" + Data.MySQLPort + "/" + Data.MySQLDatabase + "?autoReconnect=true", Data.MySQLUser, Data.MySQLPassword);
                Main.log("§aDie Verbindung mit der MySql Datenbank wurde aufgebaut!");
            } catch (SQLException var1) {
                Main.log("§4Es gab einen fehler bei der Verbindung mit der MySql Datenbank!");
            }
        } else {
            System.out.println("Die Verbindung mit der MySql Datenbank besteht bereits!");
        }

    }

    public static ResultSet getResult(String sql) {
        if (isConnected()) {
            try {
                return connection.createStatement().executeQuery(sql);
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

        return null;
    }

    public static void close() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException var1) {
                var1.printStackTrace();
            }
        }

    }

    public static boolean isConnected() {
        return connection != null;
    }

    public static void update(String qry) {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

}
