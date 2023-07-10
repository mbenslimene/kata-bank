package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Transaction;

import java.util.List;

public interface AccountService {

    String createAccount(String firstName, String lastName);
    Account getAccount(String accountId);

    void deposit(String accountId, float amount);

    void withdraw(String accountId, float amount);

    /**
     * get all transactions
     * @param accountId
     * @return
     */
    List<Transaction> getTransactions(String accountId);

}
