package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String id;
    private String firstName;
    private String lastName;

    private float balance;

    List<Transaction> transactionList;

    public Account(String firstName, String lastName) {
    	this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = 0;
        this.transactionList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
    
    
    public boolean equals(Object obj){
    	Account account = (Account) obj;
        return this.id == account.id ? true : false;
    }
     
     
    public int hashCode(){
        return this.id.hashCode();
    }
}
