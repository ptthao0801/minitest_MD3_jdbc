package org.example.minitestjdbc.controller;

import org.example.minitestjdbc.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IBookDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678@ABC";

    private static final String INSERT_BOOK_SQL = "INSERT INTO books (name, author, price, img) VALUES (?, ?, ?, ?);";
    private static final String SELECT_BOOK_BY_ID = "select id,name,author,price from books where id=?";
    private static final String SELECT_ALL_BOOKS = "select * from books";
    private static final String DELETE_BY_ID = "delete from books where id=?;";
    private static final String UPDATE_BOOK_BY_ID = "update books set name =?, author=?, price=?,img=? where id=?;";

    protected Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public void insertBook(Book book) throws SQLException {
        System.out.println(INSERT_BOOK_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setDouble(3,book.getPrice());
            preparedStatement.setString(4,book.getImg());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book selectBook(int id) {
        Book book = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (rs.next()){
                String name = rs.getString("name");
                String author = rs.getString("author");
                Double price = rs.getDouble("price");
                String img = rs.getString("img");
                book = new Book(id,name,author,price,img);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> selectAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                Double price = rs.getDouble("price");
                String img = rs.getString("img");
                books.add(new Book(id,name,author,price,img));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public void deleteBook(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("successfully deleted!");
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_BY_ID)){
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setDouble(3,book.getPrice());
            preparedStatement.setString(4,book.getImg());
            preparedStatement.setInt(5,book.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void printSQLException(SQLException ex){
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
