package com.MonsterShop.MS.exceptions;

public class NoIdProductFoundException extends AppException {
    public NoIdProductFoundException(Long id) {
      super("The id: " + id + " does not exist.");
    }
}
