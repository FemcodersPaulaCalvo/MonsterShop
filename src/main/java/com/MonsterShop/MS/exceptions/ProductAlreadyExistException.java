package com.MonsterShop.MS.exceptions;

public class ProductAlreadyExistException extends AppException {
  public ProductAlreadyExistException(String name, double price, Long id) {
    super("This product already exist with id " + id + ". Name: " + name + ", Price: " + price + ".");
  }
}
