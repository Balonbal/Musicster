package org.gabeorama.Musicster.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {


    static String sqlDriver = "org.gjt.mm.mysql.Driver";
    static String serverUrl = "jdbc:mysql://localhost";

    public static void createDatabases() {
        try {
            Class.forName(sqlDriver);

            Connection connection = DriverManager.getConnection(serverUrl, "root", "");

            /* CREATE DATABASES */
            //Create Database
            connection.createStatement().execute(
                    "CREATE DATABASE IF NOT EXISTS Musicster"
            );
            //Table for song
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS Musicster.Songs (" +
                            "Song_ID INT PRIMARY KEY AUTO_INCREMENT)");
            //Table for joining songs and song services
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS Musicster.SongServices (" +
                            "SongService_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "Song_ID INT NOT NULL, " +
                            "Service_ID INT NOT NULL)");
            //Table for song services
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS Musicster.Services (" +
                            "Service_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "ServiceName text NOT NULL, " +
                            "ServiceURL text NOT NULL)");
            //Table for categories
            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS Musicster.Categories (" +
                            "Category_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "CategoryName text NOT NULL, " +
                            "CategoryDescription text)"
            );

            connection.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS Musicster.Votes (" +
                            "Vote_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                            "Category_ID INT NOT NULL, " +
                            "Song_ID INT NOT NULL, " +
                            "Vote text NOT NULL)"
            );
            //Add foreign keys (link tables)
            connection.createStatement().execute(
                    "ALTER TABLE Musicster.Votes ADD FOREIGN KEY (Category_ID) REFERENCES Musicster.Categories (Category_ID)"
            );
            connection.createStatement().execute(
                    "ALTER TABLE Musicster.Votes ADD FOREIGN KEY (Song_ID) REFERENCES Musicster.Songs (Song_ID)"
            );
            connection.createStatement().execute(
                    "ALTER TABLE Musicster.SongServices ADD FOREIGN KEY (Song_ID) REFERENCES Musicster.Songs (Song_ID)"
            );
            connection.createStatement().execute(
                    "ALTER TABLE Musicster.SongServices ADD FOREIGN KEY (Service_ID) REFERENCES Musicster.Services (Service_ID)"
            );


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
