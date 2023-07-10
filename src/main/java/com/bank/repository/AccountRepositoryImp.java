package com.bank.repository;

import com.bank.exception.RepositoryException;
import com.bank.model.Account;

import java.util.Map;
import java.util.UUID;

public class AccountRepositoryImp implements AccountRepository {

    private final Map<String, Account> dummyAccountList;

    public AccountRepositoryImp(Map<String, Account> dummyAccountList) {
        this.dummyAccountList = dummyAccountList;
    }

    @Override
    public String create(String firstName, String lastName) {
        Account account = new Account(firstName, lastName);
        // generate dummy uuid
        String accountId = UUID.randomUUID().toString();
        account.setId(accountId);

        this.dummyAccountList.put(accountId, account);

        return accountId;
    }

    @Override
    public void save(Account account) {
        if (account.getId() == null) {
            throw new RepositoryException("Save account failed. Error: account id should be set");
        }
        this.dummyAccountList.put(account.getId(), account);
    }

    public Account getById(String id) {
        return this.dummyAccountList.get(id);
    }
}
