package com.bank.repository;

import com.bank.model.Account;

public interface AccountRepository {

    String create(String firstName, String lastName);

    void save(Account account);

    Account getById(String id);
}
