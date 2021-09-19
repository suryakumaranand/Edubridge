package com.sales.customermanagemt.web;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sales.customermanagemt.bean.Customer;
import com.sales.customermanagemt.dao.CustomerDao;

@WebServlet("/")
public class CustomerServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private CustomerDao customerDao;

  public void init() {
    customerDao = new CustomerDao();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    String action = request.getServletPath();

    try {
      switch (action) {
      case "/new":
        showNewForm(request, response);
        break;
      case "/insert":
        insertCustomer(request, response);
        break;
      case "/delete":
        deleteCustomer(request, response);
        break;
      case "/edit":
        showEditForm(request, response);
        break;
      case "/update":
        updateCustomer(request, response);
        break;
      default:
        listCustomer(request, response);
        break;
      }
    } catch (SQLException ex) {
      throw new ServletException(ex);
    }
  }

  private void listCustomer(HttpServletRequest request, HttpServletResponse response)
  throws SQLException, IOException, ServletException {
    List < Customer > listCustomer = customerDao.selectAllCustomers();
    request.setAttribute("listCustomer", listCustomer);
    RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
    dispatcher.forward(request, response);
  }

  private void showNewForm(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
    dispatcher.forward(request, response);
  }

  private void showEditForm(HttpServletRequest request, HttpServletResponse response)
  throws SQLException, ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Customer existingCustomer = customerDao.selectCustomer(id);
    RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
    request.setAttribute("customer", existingCustomer);
    dispatcher.forward(request, response);

  }

  private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
  throws SQLException, IOException {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    long contact = Long.parseLong(request.getParameter("contact"));
    String product = request.getParameter("product");
    float price = Float.parseFloat(request.getParameter("price"));
    Customer newCustomer = new Customer(name, email, contact, product, price);
    customerDao.insertCustomer(newCustomer);
    response.sendRedirect("list");
  }

  private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
  throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    long contact = Long.parseLong(request.getParameter("contact"));
    String product = request.getParameter("product");
    float price = Float.parseFloat(request.getParameter("price"));

    Customer book = new Customer(id, name, email, contact, product, price);
    customerDao.updateCustomer(book);
    response.sendRedirect("list");
  }

  private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
  throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    customerDao.deleteCustomer(id);
    response.sendRedirect("list");

  }

}