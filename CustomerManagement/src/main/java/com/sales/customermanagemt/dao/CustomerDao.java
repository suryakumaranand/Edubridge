package com.sales.customermanagemt.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sales.customermanagemt.bean.Customer;

public class CustomerDao {
  private String jdbcURL = "jdbc:mysql://localhost:3306/practice";
  private String customername = "root";
  private String password = "Reddy@98";

  private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO customers" + "  (name, email, contact, product, price) VALUES " +
    " (?, ?, ?, ?, ?);";

  private static final String SELECT_CUSTOMER_BY_ID = "select id,name,email,contact,product,price from customers where id =?";
  private static final String SELECT_ALL_CUSTOMER = "select * from customers";
  private static final String DELETE_CUSTOMERS_SQL = "delete from customers where id = ?;";
  private static final String UPDATE_CUSTOMERS_SQL = "update customers set name = ?,email= ?, contact= ?, product= ?, price= ? where id = ?;";

  public CustomerDao() {}

  protected Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(jdbcURL, customername, password);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return connection;
  }

  public void insertCustomer(Customer customer) throws SQLException {
    System.out.println(INSERT_CUSTOMERS_SQL);
    // try-with-resource statement will auto close the connection.
    try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
      preparedStatement.setString(1, customer.getName());
      preparedStatement.setString(2, customer.getEmail());
      preparedStatement.setLong(3, customer.getContact());
      preparedStatement.setString(4, customer.getProduct());
      preparedStatement.setFloat(5, customer.getPrice());
      System.out.println(preparedStatement);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  public Customer selectCustomer(int id) {
    Customer customer = null;
    // Step 1: Establishing a Connection
    try (Connection connection = getConnection();
      // Step 2:Create a statement using connection object
      PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
      preparedStatement.setInt(1, id);
      System.out.println(preparedStatement);
      // Step 3: Execute the query or update query
      ResultSet rs = preparedStatement.executeQuery();

      // Step 4: Process the ResultSet object.
      while (rs.next()) {
        String name = rs.getString("name");
        String email = rs.getString("email");
        long contact = rs.getLong("contact");
        String product = rs.getString("product");
        float price = rs.getFloat("price");
        customer = new Customer(id, name, email, contact, product, price);
      }
    } catch (SQLException e) {
      printSQLException(e);
    }
    return customer;
  }

  public List < Customer > selectAllCustomers() {

    // using try-with-resources to avoid closing resources (boiler plate code)
    List < Customer > customers = new ArrayList < > ();
    // Step 1: Establishing a Connection
    try (Connection connection = getConnection();

      // Step 2:Create a statement using connection object
      PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);) {
      System.out.println(preparedStatement);
      // Step 3: Execute the query or update query
      ResultSet rs = preparedStatement.executeQuery();

      // Step 4: Process the ResultSet object.
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        long contact = rs.getLong("contact");
        String product = rs.getString("product");
        float price = rs.getFloat("price");
        customers.add(new Customer(id, name, email, contact, product, price));
      }
    } catch (SQLException e) {
      printSQLException(e);
    }
    return customers;
  }

  public boolean deleteCustomer(int id) throws SQLException {
    boolean rowDeleted;
    try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMERS_SQL);) {
      statement.setInt(1, id);
      rowDeleted = statement.executeUpdate() > 0;
    }
    return rowDeleted;
  }

  public boolean updateCustomer(Customer customer) throws SQLException {
    boolean rowUpdated;

    try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);) {
      System.out.println("updated USer:" + statement);
      statement.setString(1, customer.getName());
      statement.setString(2, customer.getEmail());
      statement.setLong(3, customer.getContact());
      statement.setString(3, customer.getProduct());
      statement.setFloat(3, customer.getPrice());
      statement.setInt(4, customer.getId());

      rowUpdated = statement.executeUpdate() > 0;
    }
    return rowUpdated;
  }

  private void printSQLException(SQLException ex) {
    for (Throwable e: ex) {
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