package org.example.minitestjdbc.controller;

import org.example.minitestjdbc.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDAO {
    public void insertBook(Book book) throws SQLException;
    public Book selectBook(int id);
    public List<Book> selectAllBooks();
    public void deleteBook(int id) throws SQLException;
    public void updateBook(Book book) throws SQLException;
}
