package com.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.demo.database.DatabaseConstants.*;

public class CreateDatabaseAndTables {
    public static void createDatabaseAndTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL_D, USER, PASSWORD)) {
            String query = "CREATE DATABASE IF NOT EXISTS blog";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            if (conn != null) {
                System.out.println("Database Created");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String createTasksTable = "CREATE TABLE IF NOT EXISTS users" +
                    "(    id      int not null auto_increment," +
                    "    name    varchar(255)," +
                    "    login   varchar(255)," +
                    "    password_hash varchar(255)," +
                    "    primary key (id));";
            PreparedStatement preparedStatement = conn.prepareStatement(createTasksTable);
            preparedStatement.executeUpdate();
            if (conn != null) {
                System.out.println("Table Authors Created");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String createUsersTable = "CREATE TABLE IF NOT EXISTS articles " +
                    "(   id       int not null auto_increment," +
                    "    title     varchar(255)," +
                    "    description varchar(255)," +
                    "    article_date date," +
                    "    user_id   int," +
                    "    foreign key (user_id) references users(id) ON DELETE CASCADE," +
                    "    primary key (id));";
            PreparedStatement preparedStatement = conn.prepareStatement(createUsersTable);
            preparedStatement.executeUpdate();
            if (conn != null) {
                System.out.println("Table Books Created");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }
}
