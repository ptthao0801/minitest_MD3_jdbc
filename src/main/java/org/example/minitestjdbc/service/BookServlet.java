package org.example.minitestjdbc.service;

import org.example.minitestjdbc.controller.BookDAO;
import org.example.minitestjdbc.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;
    public void init(){
        bookDAO = new BookDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                try {
                    insertBook(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    updateBook(req,resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        double price = Double.parseDouble(req.getParameter("price"));
        String img = req.getParameter("img");

        bookDAO.insertBook(new Book(name,author,price,img));
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/create.jsp");
        dispatcher.forward(req,resp);
    }
    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        double price = Double.parseDouble(req.getParameter("price"));
        String img = req.getParameter("img");

        bookDAO.updateBook(new Book(id,name,author,price,img));
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/edit.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showNewForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                try {
                    deleteBook(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                listBook(req, resp);
                break;
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/create.jsp");
        dispatcher.forward(req,resp);
    }
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book existingBook = bookDAO.selectBook(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/edit.jsp");
        req.setAttribute("book",existingBook);
        dispatcher.forward(req,resp);
    }
    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookDAO.deleteBook(id);

        List<Book> listBook = bookDAO.selectAllBooks();
        req.setAttribute("listBook",listBook);
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/list.jsp");
        dispatcher.forward(req,resp);
    }
    private void listBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> listBook = bookDAO.selectAllBooks();
        req.setAttribute("listBook",listBook);
        RequestDispatcher dispatcher = req.getRequestDispatcher("book/create.jsp");
        dispatcher.forward(req,resp);
    }
}
