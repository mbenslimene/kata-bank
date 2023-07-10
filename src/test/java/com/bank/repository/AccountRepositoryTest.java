package com.bank.repository;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Map;

import com.bank.exception.RepositoryException;
import com.bank.model.Account;

public class AccountRepositoryTest {

	private final Map<String, Account> mockAccountList = mock(Map.class);
	private final AccountRepository accountRepository = new AccountRepositoryImp(mockAccountList);

	@Test
	public void testCreate() {

		String accountId = accountRepository.create("firstname", "lastname");
		assertNotNull(accountId);

	}

	@Test(expected = RepositoryException.class)
	public void testSaveThrowRepositoryException() {
		Account account = new Account("firstname", "lastname");
		account.setId(null);
		accountRepository.save(account);
	}

	@Test
	public void testGetById() {

		String accountId = "20";

		Account expectedAccount = new Account("firstname", "lastname");
		expectedAccount.setId(accountId);

		when(mockAccountList.get(accountId)).thenReturn(expectedAccount);

		Account account = accountRepository.getById(accountId);

		assertEquals(account, expectedAccount);

	}

}
