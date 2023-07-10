package com.bank.service;

import com.bank.exception.ServiceException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;

import java.util.List;

public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(String firstName, String lastName) {
        return accountRepository.create(firstName, lastName);
    }

    @Override
    public Account getAccount(String accountId) {
        return accountRepository.getById(accountId);
    }

    @Override
    public void deposit(String accountId, float amount) {
        performTransaction(accountId, amount, Transaction.TransactionType.DEPOSIT);
    }

    @Override
    public void withdraw(String accountId, float amount) {
        performTransaction(accountId, amount, Transaction.TransactionType.WITHDRAWAL);
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        Account account = accountRepository.getById(accountId);
        return account.getTransactionList();
    }


    /**
     * perform transaction of type deposit and withdrawal
     *
     * @param accountId
     * @param transactionAmount
     * @param transactionType
     */
    private void performTransaction(String accountId, float transactionAmount, Transaction.TransactionType transactionType) {

        Account account = accountRepository.getById(accountId);
        if (account == null) {
            throw new ServiceException("Account not found");
        }

        float initialBalance = account.getBalance();
        Transaction transaction = new Transaction(transactionType, transactionAmount, initialBalance);

        // add transaction to account transaction list
        account.getTransactionList().add(transaction);

        // calculate and set new balance
        float newBalance = calculateNewBalance(initialBalance, transactionAmount, transactionType);
        account.setBalance(newBalance);

        // save account
        accountRepository.save(account);
    }

    private float calculateNewBalance(float initialBalance, float transactionAmount, Transaction.TransactionType transactionType) {
        if (Transaction.TransactionType.DEPOSIT.equals(transactionType)) {
            return initialBalance + transactionAmount;
        } else if (Transaction.TransactionType.WITHDRAWAL.equals(transactionType)) {
            if (initialBalance < transactionAmount) {
                throw new ServiceException("WITHDRAWAL not authorised because of insufficient balance");
            }
            return initialBalance - transactionAmount;
        }
        throw new ServiceException("Unsupported transaction type");
    }
}
