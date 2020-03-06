package com.example.demo.service;

import com.example.demo.models.Article;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.database.DatabaseConstants.*;

@Service
public class ArticleService {

    public boolean create(Article article) {
        boolean flag = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "INSERT INTO articles(title, description,article_date) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getDescription());
            preparedStatement.setDate(3, article.getDate());
            preparedStatement.executeUpdate();

            if (conn != null) {
                System.out.println("Connected to the database");
                flag = true;
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
            return flag;
        }
        return flag;
    }

    public boolean addUserToArticle(int articleId, int userId) {
        boolean flag = false;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "UPDATE articles SET user_id = ? WHERE id =?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, articleId);
            flag = preparedStatement.execute();
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        return flag;
    }

    public List<Article> findAllByUser(int userId) {
        List<Article> list = new ArrayList();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM articles WHERE user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setTitle(resultSet.getString("title"));
                article.setDescription(resultSet.getString("description"));
                article.setDate(resultSet.getDate("article_date"));
                list.add(article);
            }
            if (conn != null) {
                System.out.println("Connected to the database");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
        return list;
    }
}
