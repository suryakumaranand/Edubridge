package com.sales.customermanagemt.bean;

public class Customer {

  protected int id;
  protected String name;
  protected String email;
  protected long contact;
  protected String product;
  protected float price;

  public Customer() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getContact() {
    return contact;
  }

  public void setContact(long contact) {
    this.contact = contact;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public Customer(int id, String name, String email, long contact, String product, float price) {
    super();
    this.id = id;
    this.name = name;
    this.email = email;
    this.contact = contact;
    this.product = product;
    this.price = price;
  }

  public Customer(String name, String email, long contact, String product, float price) {
    super();
    this.name = name;
    this.email = email;
    this.contact = contact;
    this.product = product;
    this.price = price;
  }

}