package com.MonsterShop.MS.exceptions;

public class EmptyListException extends AppException{
    public EmptyListException(){
        super("The list is empty");
    }
}
