package com.taco.tacoshop.config;

public class OutOfStockException  extends RuntimeException{

    public OutOfStockException(String message){
        super(message);
    }
}
